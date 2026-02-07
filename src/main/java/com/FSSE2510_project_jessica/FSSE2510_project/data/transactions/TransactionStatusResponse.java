package com.FSSE2510_project_jessica.FSSE2510_project.data.transactions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStatusResponse {
    private Integer tid;
    private String status;
    private String sessionId;
    private String checkoutUrl;
}

