package com.FSSE2510_project_jessica.FSSE2510_project.data.transactions;

import com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct.TransactionProductRespData;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class TransactionRespData {
    private Integer tid;
    private Integer buyerUid;
    private LocalDateTime dateTime;
    private String status;
    private BigDecimal total;
    private List<TransactionProductRespData> productRespDataList;
}
