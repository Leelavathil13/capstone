package com.main.authserver.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.authserver.model.User;


public interface UserDetailsRepository extends JpaRepository<User,Integer> {


    Optional<User> findByUsername(String name);

}
