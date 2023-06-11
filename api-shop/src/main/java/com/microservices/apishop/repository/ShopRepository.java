package com.microservices.apishop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.apishop.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
	public List<Shop> findAllByUserIdentifier(String userIdentifier);
	public List<Shop> findAllByTotalGreaterThan(Float total);
	List<Shop> findAllByDateGreaterThan(LocalDateTime date);
}
