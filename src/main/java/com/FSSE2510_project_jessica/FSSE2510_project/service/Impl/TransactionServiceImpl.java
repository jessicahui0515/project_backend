package com.FSSE2510_project_jessica.FSSE2510_project.service.Impl;

import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.entity.CartItemEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct.TransactionProductEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.CheckoutSessionResponse;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionStatusResponse;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.FirebaseUserReqData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.exception.*;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.transaction.TransactionDataMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.transaction.TransactionEntityMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.transactionProduct.TransactionProductEntityMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.repository.CartItemRepository;
import com.FSSE2510_project_jessica.FSSE2510_project.repository.ProductRepository;
import com.FSSE2510_project_jessica.FSSE2510_project.repository.TransactionProductRepository;
import com.FSSE2510_project_jessica.FSSE2510_project.repository.TransactionRepository;
import com.FSSE2510_project_jessica.FSSE2510_project.service.ProductService;
import com.FSSE2510_project_jessica.FSSE2510_project.service.StripeService;
import com.FSSE2510_project_jessica.FSSE2510_project.service.TransactionService;
import com.FSSE2510_project_jessica.FSSE2510_project.service.UserService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final TransactionEntityMapper transactionEntityMapper;
    private final TransactionProductEntityMapper transactionProductEntityMapper;
    private final TransactionRepository transactionRepository;
    private final TransactionDataMapper transactionDataMapper;

    private final ProductService productService;
    private final StripeService stripeService;

    public TransactionServiceImpl(CartItemRepository cartItemRepository, UserService userService, TransactionEntityMapper transactionEntityMapper, TransactionProductEntityMapper transactionProductEntityMapper, TransactionRepository transactionRepository, TransactionDataMapper transactionDataMapper, TransactionProductRepository transactionProductRepository, ProductRepository productRepository, ProductService productService, StripeService stripeService, StripeService stripeService1) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.transactionEntityMapper = transactionEntityMapper;
        this.transactionProductEntityMapper = transactionProductEntityMapper;
        this.transactionRepository = transactionRepository;
        this.transactionDataMapper = transactionDataMapper;
        this.productService = productService;
        this.stripeService = stripeService1;
    }

    @Override
    @Transactional
    public TransactionRespData creatNewTransaction(FirebaseUserReqData userData){
        try {
            UserEntity userEntity = userService.getUserEntityByEmail(userData);

            List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUser(userEntity);
            if (cartItemEntityList.isEmpty()) {
                throw new CartHasNoItemException();
            }
            TransactionEntity transactionEntity = transactionEntityMapper.toTransactionEntity(userEntity);

            BigDecimal total = BigDecimal.ZERO;
            List<TransactionProductEntity> tPEList = transactionProductEntityMapper.toTransactionProductEntity(cartItemEntityList, transactionEntity);
            for (TransactionProductEntity tpe : tPEList) {
                total = total.add(tpe.getSubTotal());
            }
            transactionEntity.setTotal(total);
            transactionEntity.setProductEntityList(tPEList);

            TransactionEntity saved = transactionRepository.save(transactionEntity);
//            cartItemRepository.deleteAll(cartItemEntityList);

            return transactionDataMapper.toRespData(saved);
        } catch (Exception e) {
            logger.warn("Create New Transaction failed: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public TransactionRespData getTransactionDetail(FirebaseUserReqData userData, Integer tid){
        try {
            TransactionEntity transactionEntity = getTranEntityByUserAndTid(userData, tid);
            return transactionDataMapper.toRespData(transactionEntity);
        } catch (Exception e) {
            logger.warn("Get Transaction Detail failed: {}", e.getMessage());
            throw e;
        }
    }
    @Override
    public List<TransactionRespData> getSucceedTransactionDetailList(FirebaseUserReqData userData){
        try{
            UserEntity userEntity = userService.getUserEntityByEmail(userData);
            List<TransactionEntity> transactionEntityList = transactionRepository.findAllByUserAndStatus(userEntity,"SUCCESS");
            return transactionDataMapper.toRespDataList(transactionEntityList);
        }catch (Exception e) {
            logger.warn("Get Transaction Detail failed: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<TransactionRespData> getProcessingTransactionDetailList(FirebaseUserReqData userData){
        try{
            UserEntity userEntity = userService.getUserEntityByEmail(userData);
            List<TransactionEntity> transactionEntityList = transactionRepository.findAllByUserAndStatus(userEntity,"PROCESSING");
            return transactionDataMapper.toRespDataList(transactionEntityList);
        }catch (Exception e) {
            logger.warn("Get Transaction Detail failed: {}", e.getMessage());
            throw e;
        }
    }


    @Override
    @Transactional
    public TransactionStatusResponse updateTransactionStatusToProcessing(FirebaseUserReqData userData, Integer tid) {
        try {
            TransactionEntity transactionEntity = getTranEntityByUserAndTid(userData, tid);
            if(transactionEntity.getStatus().equals("SUCCESS")){
                throw new TransactionAlreadySuccessException(transactionEntity.getStatus());
            }
            if(!transactionEntity.getStatus().equals("PROCESSING")){
                transactionEntity.setStatus("PROCESSING");
            }

            CheckoutSessionResponse checkoutUrl = stripeService.createCheckSession(transactionEntity);
            return new TransactionStatusResponse(
                    transactionEntity.getTid(),
                    transactionEntity.getStatus(),
                    checkoutUrl.getSessionId(),
                    checkoutUrl.getCheckoutUrl()
            );
        } catch (StripeException se) {
            logger.error("Stripe error: {}", se.getMessage());
            throw new RuntimeException("Payment service failed", se);
        } catch (Exception e) {
            logger.warn("Update Transaction Status To Processing failed: {}", e.getMessage());
            throw e;
        }
    }
    @Override
    @Transactional
    public TransactionRespData updateTransactionStatusToSuccess(FirebaseUserReqData userData, Integer tid, String sessionId) throws StripeException, IllegalAccessException {
        try{
            List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUser(userService.getUserEntityByEmail(userData));
            TransactionEntity transactionEntity = getTranEntityByUserAndTid(userData, tid);
            if(transactionEntity.getStatus().equals("SUCCESS")){
                throw new TransactionStatusAlreadyUpdatedException(transactionEntity.getStatus());
            }
            if(transactionEntity.getStatus().equals("PREPARE")){
                throw new TransactionNotYetPaidException(transactionEntity.getStatus());
            }
            if(sessionId == null){
                throw new IllegalAccessException("sessionId is null");
            }

            Session session = Session.retrieve(sessionId);
            if (!"paid".equals(session.getPaymentStatus())) {
                throw new IllegalAccessException("session payment status is not paid");
            }

            for(TransactionProductEntity tranProEntity : transactionEntity.getProductEntityList()){
                productService.reduceStock(tranProEntity.getProduct().getPid(), tranProEntity.getQuantity());
            }
            transactionEntity.setStatus("SUCCESS");
            cartItemRepository.deleteAll(cartItemEntityList);

            return transactionDataMapper.toRespData(transactionEntity);
        } catch (Exception e) {
        logger.warn("Update Transaction Status To Success failed: {}", e.getMessage());
        throw e;
        }
    }

    public TransactionEntity getTranEntityByUserAndTid(FirebaseUserReqData userData, Integer tid){
        UserEntity userEntity = userService.getUserEntityByEmail(userData);
        Optional<TransactionEntity> optionalTransactionEntity = transactionRepository.findTransactionEntityByUserAndTid(userEntity, tid);
        if (optionalTransactionEntity.isEmpty()){
            throw new TransactionNotFoundException(tid);
        }
        return optionalTransactionEntity.get();
    }

}
