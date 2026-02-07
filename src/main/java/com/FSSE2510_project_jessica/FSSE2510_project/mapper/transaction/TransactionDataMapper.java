package com.FSSE2510_project_jessica.FSSE2510_project.mapper.transaction;

import com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct.TransactionProductRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.transactionProduct.TransactionProductDataMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionDataMapper {
    private final TransactionProductDataMapper transactionProductDataMapper;

    public TransactionDataMapper(TransactionProductDataMapper transactionProductDataMapper) {
        this.transactionProductDataMapper = transactionProductDataMapper;
    }

    public TransactionRespData toRespData(TransactionEntity entity) {
    List<TransactionProductRespData> tranProRespDataList =  transactionProductDataMapper.toRespList(entity.getProductEntityList());
    TransactionRespData resp = new TransactionRespData();
        resp.setTid(entity.getTid());
        resp.setBuyerUid(entity.getUser().getUid());
        resp.setDateTime(entity.getDateTime());
        resp.setStatus(entity.getStatus());
        resp.setTotal(entity.getTotal());
        resp.setProductRespDataList(tranProRespDataList);

        return resp;
    }

    public List<TransactionRespData> toRespDataList(List<TransactionEntity> TransactionEntityList) {
        List<TransactionRespData> respDataList = new ArrayList<>();
        for (TransactionEntity entity : TransactionEntityList) {
            respDataList.add(toRespData(entity));
        }
        return respDataList;
    }
}
