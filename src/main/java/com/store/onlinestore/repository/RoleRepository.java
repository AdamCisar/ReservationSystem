package com.store.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.onlinestore.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

	Role findByName(String name);

}
