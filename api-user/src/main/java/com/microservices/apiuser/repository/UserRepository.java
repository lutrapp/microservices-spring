package com.microservices.apiuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.apiuser.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
	UserModel findByCpf(String cpf);
	List<UserModel> queryByNameLike(String name);
}
