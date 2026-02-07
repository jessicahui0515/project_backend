package com.FSSE2510_project_jessica.FSSE2510_project.mapper.transactionProduct;

import com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct.TransactionProductEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct.TransactionProductRespData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionProductDataMapper {

    public List<TransactionProductRespData> toRespList (List<TransactionProductEntity> entityList) {
        List<TransactionProductRespData> tranProRespList = new ArrayList<>();
        for (TransactionProductEntity entity : entityList) {
            TransactionProductRespData resp = new TransactionProductRespData();
            resp.setTpid(entity.getTpid());
            resp.setProduct(entity.getProduct());
            resp.setQuantity(entity.getQuantity());
            resp.setSubTotal(entity.getSubTotal());
            tranProRespList.add(resp);

        }
        return  tranProRespList;
    }

}
