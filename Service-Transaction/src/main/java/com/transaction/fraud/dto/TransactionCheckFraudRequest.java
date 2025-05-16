package com.transaction.fraud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCheckFraudRequest {
    private Integer userId;
    private Integer transactionId;
    private String transactionalCode;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
    private String status;
    private Long createdAt;
    private Long updatedAt;
    private Boolean deleted;

    private String channel;
    private String userIP;
    private String deviceName;
    private String location;
}