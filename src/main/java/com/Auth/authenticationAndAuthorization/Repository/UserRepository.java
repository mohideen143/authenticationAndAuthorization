package com.Auth.authenticationAndAuthorization.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Auth.authenticationAndAuthorization.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByEmailOrPhone(String email, String phone);

}
