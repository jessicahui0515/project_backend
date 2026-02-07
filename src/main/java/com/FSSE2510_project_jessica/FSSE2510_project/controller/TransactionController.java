package com.FSSE2510_project_jessica.FSSE2510_project.controller;

import com.FSSE2510_project_jessica.FSSE2510_project.config.EnvConfig;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionRespDto;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionStatusResponse;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.FirebaseUserReqData;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.transaction.TransactionDtoMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.user.UserDataMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.service.TransactionService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PROD_BASE_URL})
public class TransactionController {
    private final UserDataMapper userDataMapper;
    private final TransactionService transactionService;
    private final TransactionDtoMapper transactionDtoMapper;

    public TransactionController(UserDataMapper userDataMapper, TransactionService transactionService, TransactionDtoMapper transactionDtoMapper) {
        this.userDataMapper = userDataMapper;
        this.transactionService = transactionService;
        this.transactionDtoMapper = transactionDtoMapper;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionRespDto createNewTransaction (@AuthenticationPrincipal Jwt jwt){
        FirebaseUserReqData firebaseUserReqData = userDataMapper.toFirebaseUserReqData(jwt);
        TransactionRespData respData = transactionService.creatNewTransaction(firebaseUserReqData);
        return transactionDtoMapper.toRespDto(respData);
    }

    @GetMapping("/{tid}")
    public TransactionRespDto getTransactionDetail (@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid){
        FirebaseUserReqData firebaseUserReqData = userDataMapper.toFirebaseUserReqData(jwt);
        TransactionRespData respData = transactionService.getTransactionDetail(firebaseUserReqData, tid);
        return transactionDtoMapper.toRespDto(respData);
    }
    @GetMapping("/history")
    public List<TransactionRespDto> getAllSucceedTransactions (@AuthenticationPrincipal Jwt jwt){
        FirebaseUserReqData firebaseUserReqData = userDataMapper.toFirebaseUserReqData(jwt);
        List<TransactionRespData> respDataList = transactionService.getSucceedTransactionDetailList(firebaseUserReqData);
        return transactionDtoMapper.toRespDtoList(respDataList);
    }

    @GetMapping("/waitingForPayment")
    public List<TransactionRespDto> getAllProcessingTransactions (@AuthenticationPrincipal Jwt jwt){
        FirebaseUserReqData firebaseUserReqData = userDataMapper.toFirebaseUserReqData(jwt);
        List<TransactionRespData> respDataList = transactionService.getProcessingTransactionDetailList(firebaseUserReqData);
        return transactionDtoMapper.toRespDtoList(respDataList);
    }

    @PatchMapping("/{tid}/payment")
    public TransactionStatusResponse updateTranStatusToProcessing (@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid){
        FirebaseUserReqData firebaseUserReqData = userDataMapper.toFirebaseUserReqData(jwt);
        return transactionService.updateTransactionStatusToProcessing(firebaseUserReqData, tid);
    }

    @PatchMapping("/{tid}/{sessionId}/success")
    public TransactionRespDto updateTranStatusToSuccess(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid, @PathVariable String sessionId) throws StripeException, IllegalAccessException {
        FirebaseUserReqData firebaseUserReqData = userDataMapper.toFirebaseUserReqData(jwt);
        TransactionRespData respData = transactionService.updateTransactionStatusToSuccess(firebaseUserReqData, tid, sessionId);
        return transactionDtoMapper.toRespDto(respData);
    }
}
