package com.FSSE2510_project_jessica.FSSE2510_project.service;

import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionStatusResponse;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.FirebaseUserReqData;
import com.stripe.exception.StripeException;

import java.util.List;

public interface TransactionService {

    TransactionRespData creatNewTransaction(FirebaseUserReqData userData);

    TransactionRespData getTransactionDetail(FirebaseUserReqData userData, Integer tid);

    List<TransactionRespData> getSucceedTransactionDetailList(FirebaseUserReqData userData);

    List<TransactionRespData> getProcessingTransactionDetailList(FirebaseUserReqData userData);

    TransactionStatusResponse updateTransactionStatusToProcessing(FirebaseUserReqData userData, Integer tid);

    TransactionRespData updateTransactionStatusToSuccess(FirebaseUserReqData userData, Integer tid, String sessionId)throws StripeException, IllegalAccessException ;
}
