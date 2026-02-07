package com.FSSE2510_project_jessica.FSSE2510_project.mapper.transaction;

import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionEntityMapper {
    public TransactionEntity toTransactionEntity(UserEntity userEntity){
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setUser(userEntity);
        transactionEntity.setStatus("PREPARE");
        transactionEntity.setDateTime(LocalDateTime.now());
        return transactionEntity;
    }
}
