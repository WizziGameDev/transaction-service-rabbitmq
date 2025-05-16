package com.transaction.fraud.repository;

import com.transaction.fraud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByDeletedFalse();

    Optional<User> findFirstByIdAndDeleted(Integer id, Boolean deleted);
}
