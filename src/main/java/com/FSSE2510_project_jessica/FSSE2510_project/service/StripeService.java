package com.FSSE2510_project_jessica.FSSE2510_project.service;

import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.CheckoutSessionResponse;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionEntity;
import com.stripe.exception.StripeException;

public interface StripeService {
    CheckoutSessionResponse createCheckSession(TransactionEntity transactionEntity) throws StripeException;
}
