package com.store.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.onlinestore.entity.Role;
import com.store.onlinestore.entity.User;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

	Role findByName(String name);

}
