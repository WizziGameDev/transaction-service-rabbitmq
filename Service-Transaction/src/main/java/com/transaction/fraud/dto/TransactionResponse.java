package com.transaction.fraud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private Integer id;

    private String name;

    private String email;

    private String transactionalCode;

    private String productName;

    private Integer quantity;

    private Double price;

    private Double totalPrice;

    private String status;
}
