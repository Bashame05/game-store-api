package com.makar.gamestoreapi.Repository;

import com.makar.gamestoreapi.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String username);
}
