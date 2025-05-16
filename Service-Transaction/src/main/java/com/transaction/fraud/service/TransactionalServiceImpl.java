package com.transaction.fraud.service;

import com.transaction.fraud.dto.*;
import com.transaction.fraud.entity.Transaction;
import com.transaction.fraud.entity.User;
import com.transaction.fraud.exception.TransactionException;
import com.transaction.fraud.publisher.RabbitMQPublisher;
import com.transaction.fraud.repository.TransactionRepository;
import com.transaction.fraud.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransactionalServiceImpl implements TransactionalService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitMQPublisher rabbitMQPublisher;

    @Override
    public List<TransactionListResponse> getTransactions() {

        return transactionRepository.findAllWithUser()
                .stream()
                .map(data -> new TransactionListResponse(
                        data.getId(),
                        data.getTransactionalCode(),
                        data.getUser().getName(),
                        data.getUser().getEmail(),
                        data.getProductName(),
                        data.getQuantity(),
                        data.getTotalPrice(),
                        data.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionResponse getTransactionById(Integer id) {
        Transaction transaction = transactionRepository.findByIdAndDeletedFalseFetchUser(id)
                .orElseThrow(() -> new TransactionException("Transaction Not Found"));

        return TransactionResponse.builder()
                .id(transaction.getId())
                .transactionalCode(transaction.getTransactionalCode())
                .name(transaction.getUser().getName())
                .email(transaction.getUser().getEmail())
                .productName(transaction.getProductName())
                .quantity(transaction.getQuantity())
                .price(transaction.getPrice())
                .totalPrice(transaction.getTotalPrice())
                .status(transaction.getStatus())
                .build();
    }

    @Override
    @Transactional
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {

        User user = userRepository.findFirstByIdAndDeleted(transactionRequest.getUserId(), false)
                .orElseThrow(() -> new TransactionException("User Not Found"));

        Transaction transaction = new Transaction();
        transaction.setTransactionalCode(generateTransactionCode());
        transaction.setProductName(transactionRequest.getProductName());
        transaction.setQuantity(transactionRequest.getQuantity());
        transaction.setPrice(transactionRequest.getPrice());
        transaction.setTotalPrice(transactionRequest.getTotalPrice());
        transaction.setStatus(transactionRequest.getStatus());
        transaction.setCreatedAt(Instant.now().getEpochSecond());
        transaction.setUpdatedAt(0L);
        transaction.setDeleted(false);
        transaction.setChannel(transactionRequest.getChannel());
        transaction.setUserIP(transactionRequest.getUserIP());
        transaction.setDeviceName(transactionRequest.getDeviceName());
        transaction.setLocation(transactionRequest.getLocation());
        transaction.setUser(user);
        Transaction saveTransaction = transactionRepository.save(transaction);

        TransactionCheckFraudRequest fraudRequest = TransactionCheckFraudRequest.builder()
                .userId(user.getId())
                .transactionId(saveTransaction.getId())
                .transactionalCode(saveTransaction.getTransactionalCode())
                .productName(saveTransaction.getProductName())
                .quantity(saveTransaction.getQuantity())
                .price(saveTransaction.getPrice())
                .totalPrice(saveTransaction.getTotalPrice())
                .status(saveTransaction.getStatus())
                .createdAt(saveTransaction.getCreatedAt())
                .updatedAt(saveTransaction.getUpdatedAt())
                .deleted(saveTransaction.getDeleted())
                .channel(saveTransaction.getChannel())
                .userIP(saveTransaction.getUserIP())
                .deviceName(saveTransaction.getDeviceName())
                .location(saveTransaction.getLocation())
                .build();

        rabbitMQPublisher.sendMessage(fraudRequest);
        log.info("Transaction Fraud Created Successfully");

        return TransactionResponse.builder()
                .id(saveTransaction.getId())
                .transactionalCode(saveTransaction.getTransactionalCode())
                .name(saveTransaction.getUser().getName())
                .email(saveTransaction.getUser().getEmail())
                .productName(saveTransaction.getProductName())
                .quantity(saveTransaction.getQuantity())
                .price(saveTransaction.getPrice())
                .totalPrice(saveTransaction.getTotalPrice())
                .status(saveTransaction.getStatus())
                .build();
    }

    @Override
    @Transactional
    public TransactionResponse updateTransaction(Integer id, TransactionRequest transactionRequest) {

        User user = userRepository.findFirstByIdAndDeleted(transactionRequest.getUserId(), false)
                .orElseThrow(() -> new TransactionException("User Not Found"));

        Transaction transaction = transactionRepository.findByIdAndDeletedFalseFetchUser(id)
                .orElseThrow(() -> new TransactionException("Transaction Not Found"));

        transaction.setProductName(transactionRequest.getProductName());
        transaction.setQuantity(transactionRequest.getQuantity());
        transaction.setPrice(transactionRequest.getPrice());
        transaction.setTotalPrice(transactionRequest.getTotalPrice());
        transaction.setStatus(transactionRequest.getStatus());
        transaction.setUpdatedAt(Instant.now().getEpochSecond());
        transaction.setChannel(transactionRequest.getChannel());
        transaction.setUserIP(transactionRequest.getUserIP());
        transaction.setDeviceName(transactionRequest.getDeviceName());
        transaction.setLocation(transactionRequest.getLocation());
        transaction.setUser(user);
        Transaction saveTransaction = transactionRepository.save(transaction);

        return TransactionResponse.builder()
                .id(saveTransaction.getId())
                .transactionalCode(saveTransaction.getTransactionalCode())
                .name(saveTransaction.getUser().getName())
                .email(saveTransaction.getUser().getEmail())
                .productName(saveTransaction.getProductName())
                .quantity(saveTransaction.getQuantity())
                .price(saveTransaction.getPrice())
                .totalPrice(saveTransaction.getTotalPrice())
                .status(saveTransaction.getStatus())
                .build();
    }

    @Override
    @Transactional
    public String deleteTransactionById(Integer id) {
        Transaction transaction = transactionRepository.findByIdAndDeletedFalseFetchUser(id)
                .orElseThrow(() -> new TransactionException("Transaction Not Found"));
        transaction.setDeleted(true);
        transactionRepository.save(transaction);
        return "Transaction Deleted Successfully";
    }

    public String generateTransactionCode() {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomPart = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "TXN-" + datePart + "-" + randomPart;
    }

    public void updateStatusTransaction(FraudValidateResponse response) {
        log.info("Transaction ID: ", response.getTransactionId());

        Transaction dataTransaction = transactionRepository.findByIdAndDeletedFalseFetchUser(response.getTransactionId())
                .orElseThrow(() -> new TransactionException("Transaction Not Found"));

        dataTransaction.setStatus(response.getIsFraud() ? "FRAUD" : "CONFIRMED");
        log.info("Status Transaction is {}", dataTransaction.getStatus());

        transactionRepository.save(dataTransaction);
    }
}
