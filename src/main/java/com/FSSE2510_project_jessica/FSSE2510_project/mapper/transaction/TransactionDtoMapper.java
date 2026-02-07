package com.FSSE2510_project_jessica.FSSE2510_project.mapper.transaction;

import com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct.TransactionProductRespDto;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionRespDto;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.transactionProduct.TransactionProductDtoMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionDtoMapper {
    private final TransactionProductDtoMapper transactionProductDtoMapper;

    public TransactionDtoMapper(TransactionProductDtoMapper transactionProductDtoMapper) {
        this.transactionProductDtoMapper = transactionProductDtoMapper;
    }

    public TransactionRespDto toRespDto(TransactionRespData respData) {
        List<TransactionProductRespDto> tranProRespDtoList = transactionProductDtoMapper.toRespList(respData.getProductRespDataList());
        TransactionRespDto resp = new TransactionRespDto();
        resp.setTid(respData.getTid());
        resp.setBuyerUid(respData.getBuyerUid());
        resp.setDateTime(respData.getDateTime());
        resp.setStatus(respData.getStatus());
        resp.setTotal(respData.getTotal());
        resp.setProductRespDtoList(tranProRespDtoList);

        return resp;
    }

    public List<TransactionRespDto> toRespDtoList(List<TransactionRespData> TransactionRespDataList) {
        List<TransactionRespDto> transactionRespDtoList = new ArrayList<>();
        for (TransactionRespData transactionRespData : TransactionRespDataList) {
            transactionRespDtoList.add(toRespDto(transactionRespData));
        }
        return transactionRespDtoList;
    }
}
