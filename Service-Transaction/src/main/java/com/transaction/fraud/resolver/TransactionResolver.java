package com.transaction.fraud.resolver;

import com.transaction.fraud.dto.TransactionListResponse;
import com.transaction.fraud.dto.TransactionRequest;
import com.transaction.fraud.dto.TransactionResponse;
import com.transaction.fraud.service.TransactionalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class TransactionResolver {

    @Autowired
    private TransactionalServiceImpl transactionalService;

    private final ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();

    @QueryMapping(name = "transactions")
    public List<TransactionListResponse> getTransactions() {
        return CompletableFuture.supplyAsync(() ->
                transactionalService.getTransactions(), virtualExecutor
        ).join();
    }

    @QueryMapping(name = "transactionById")
    public TransactionResponse getTransactionById(@Argument Integer id) {
        return CompletableFuture.supplyAsync(() ->
                transactionalService.getTransactionById(id), virtualExecutor
        ).join();
    }

    @MutationMapping(name = "createTransaction")
    public TransactionResponse createTransaction(@Argument("request") TransactionRequest transactionRequest) {
        return CompletableFuture.supplyAsync(() ->
                transactionalService.createTransaction(transactionRequest), virtualExecutor
        ).join();
    }

    @MutationMapping(name = "updateTransaction")
    public TransactionResponse updateTransaction(@Argument Integer id, @Argument("request") TransactionRequest transactionRequest) {
        return CompletableFuture.supplyAsync(() ->
                transactionalService.updateTransaction(id, transactionRequest), virtualExecutor
        ).join();
    }

    @MutationMapping(name = "deleteTransaction")
    public String deleteTransaction(@Argument Integer id) {
        return CompletableFuture.supplyAsync(() ->
                transactionalService.deleteTransactionById(id), virtualExecutor
        ).join();
    }
}
