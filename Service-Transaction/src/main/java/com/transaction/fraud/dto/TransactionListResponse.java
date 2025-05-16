package com.transaction.fraud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionListResponse {

    private Integer id;

    private String transactionalCode;

    private String name;
    
    private String email;

    private String productName;

    private Integer quantity;

    private Double totalPrice;

    private String status;
}
