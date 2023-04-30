package com.store.onlinestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.onlinestore.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
