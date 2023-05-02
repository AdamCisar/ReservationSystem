package com.store.onlinestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.onlinestore.entities.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{

	Privilege findByName(String name);

}
