package com.transaction.fraud.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    private Integer userId;

    private String productName;

    private Integer quantity;

    private Double price;

    private Double totalPrice;

    private String status;

    private String channel;

    private String userIP;

    private String deviceName;

    private String location;
}
