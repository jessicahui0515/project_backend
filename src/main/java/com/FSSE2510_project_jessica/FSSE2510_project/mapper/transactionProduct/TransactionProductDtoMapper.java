package com.FSSE2510_project_jessica.FSSE2510_project.mapper.transactionProduct;

import com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct.TransactionProductRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct.TransactionProductRespDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionProductDtoMapper {
    public List<TransactionProductRespDto> toRespList (List<TransactionProductRespData> dataList) {
        List<TransactionProductRespDto> tranProRespList = new ArrayList<>();
        for (TransactionProductRespData respData: dataList) {
            TransactionProductRespDto resp = new TransactionProductRespDto();
            resp.setTpid(respData.getTpid());
            resp.setProduct(respData.getProduct());
            resp.setQuantity(respData.getQuantity());
            resp.setSubTotal(respData.getSubTotal());
            tranProRespList.add(resp);
        }
        return  tranProRespList;
    }
}
