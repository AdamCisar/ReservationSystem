package com.store.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.onlinestore.entity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{

	Privilege findByName(String name);

}
