package com.transaction.fraud.service;

import com.transaction.fraud.dto.TransactionListResponse;
import com.transaction.fraud.dto.TransactionRequest;
import com.transaction.fraud.dto.TransactionResponse;

import java.util.List;

public interface TransactionalService {

    // Get transactions
    List<TransactionListResponse> getTransactions();

    // Get transaction by id
    TransactionResponse getTransactionById(Integer id);

    // Create transaction
    TransactionResponse createTransaction(TransactionRequest transactionRequest);

    // Update transaction by id
    TransactionResponse updateTransaction(Integer id, TransactionRequest transactionRequest);

    // delete transaction by id
    String deleteTransactionById(Integer id);
}
