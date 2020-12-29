package com.lvtrai.dacn.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lvtrai.dacn.model.User;



@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {
	
	@EntityGraph(value = "UserComplete", type=EntityGraphType.FETCH)
	User findByUsername(String username);
			
	User findByEmail(String email);
}
