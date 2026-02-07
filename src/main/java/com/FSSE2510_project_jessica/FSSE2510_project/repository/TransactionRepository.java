package com.FSSE2510_project_jessica.FSSE2510_project.repository;

import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    Optional<TransactionEntity> findTransactionEntityByUserAndTid(UserEntity user, Integer tid);
    List<TransactionEntity> findAllByUserAndStatus(UserEntity user, String status);

}
