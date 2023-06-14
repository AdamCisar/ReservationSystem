package com.store.onlinestore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.onlinestore.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	User findByEmail(String email);

}
