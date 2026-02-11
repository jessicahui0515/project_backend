package com.FSSE2510_project_jessica.FSSE2510_project.service.Impl;

import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.ProductEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct.TransactionProductEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.CheckoutSessionResponse;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.service.StripeService;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.FSSE2510_project_jessica.FSSE2510_project.config.EnvConfig.DEV_BASE_URL;
import static com.FSSE2510_project_jessica.FSSE2510_project.config.EnvConfig.PROD_BASE_URL;

@Service
public class StripeServiceImpl implements StripeService {
    @Override
    public CheckoutSessionResponse createCheckSession(TransactionEntity transactionEntity) {
        try{
        Stripe.apiKey = System.getenv("STRIPE_SECRET_KEY");

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        for (TransactionProductEntity tpe : transactionEntity.getProductEntityList()) {
            ProductEntity product = tpe.getProduct();

            SessionCreateParams.LineItem lineItem =
                    SessionCreateParams.LineItem.builder() .
                            setQuantity(tpe.getQuantity().longValue())
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("hkd")
                                    .setUnitAmount(product.getPrice()
                                            .multiply(BigDecimal.valueOf(100))
                                            .longValue())
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(product.getName())
                                                    .addImage(product.getImageUrl())
                                                    .build()
                                    )
                                    .build()
                            )
                            .build();
            lineItems.add(lineItem);
        }

//        String YOUR_DOMAIN = DEV_BASE_URL;
          String YOUR_DOMAIN = PROD_BASE_URL;


        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(YOUR_DOMAIN + "/checkoutLoading?tid=" + transactionEntity.getTid() + "&session_id={CHECKOUT_SESSION_ID}")
                        .setCancelUrl(YOUR_DOMAIN + "/checkoutFail?tid=" + transactionEntity.getTid())
                        .addAllLineItem(lineItems)
                        .build();
        Session session = Session.create(params);

        return new CheckoutSessionResponse(session.getId(), session.getUrl());
        }catch (Exception e){
            return new CheckoutSessionResponse(null, DEV_BASE_URL + "/error");
        }
    }
}
