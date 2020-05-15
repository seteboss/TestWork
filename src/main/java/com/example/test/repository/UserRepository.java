package com.example.test.repository;

import com.example.test.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByStatus(Boolean status);
    List<User> findByTimestampGreaterThan(Timestamp timestamp);
    List<User> findByTimestampGreaterThanAndStatus(Timestamp timestamp, Boolean status);
}
