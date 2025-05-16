package com.transaction.fraud.repository;

import com.transaction.fraud.entity.Transaction;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t JOIN FETCH t.user WHERE t.deleted = false ORDER BY t.id ASC")
    List<Transaction> findAllWithUser();


    @Query("SELECT t FROM Transaction t JOIN FETCH t.user WHERE t.id = :id AND t.deleted = false")
    Optional<Transaction> findByIdAndDeletedFalseFetchUser(@Param("id") Integer id);

}
