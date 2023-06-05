package com.microservices.apiuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.apiuser.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByCpf(String cpf);
//	List<User> queryByNameLike(String name);
}
