package com.transaction.fraud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

    @Column(name = "transactional_code", unique = true)
    private String transactionalCode;

    @Column(name = "product_name")
    private String productName;

    private Integer quantity;

    private Double price;

    @Column(name = "total_price")
    private Double totalPrice;

    private String status;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    private Boolean deleted;

    private String channel;

    @Column(name = "user_ip")
    private String userIP;

    @Column(name = "device_name")
    private String deviceName;

    private String location;
}
