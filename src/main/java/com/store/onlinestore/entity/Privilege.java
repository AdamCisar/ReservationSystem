package com.store.onlinestore.entity;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Privilege {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

	@ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
	
	public Privilege(String name) {
		this.name = name;
	}
}