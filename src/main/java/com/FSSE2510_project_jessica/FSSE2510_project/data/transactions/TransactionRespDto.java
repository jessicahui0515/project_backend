package com.FSSE2510_project_jessica.FSSE2510_project.data.transactions;

import com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct.TransactionProductRespDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class TransactionRespDto {
    private Integer tid;
    private Integer buyerUid;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;
    private String status;
    private BigDecimal total;
    @JsonProperty("items")
    private List<TransactionProductRespDto> productRespDtoList;
}
