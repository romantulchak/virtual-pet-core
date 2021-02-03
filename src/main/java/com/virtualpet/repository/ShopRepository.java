package com.virtualpet.repository;

import com.virtualpet.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findFirstByOrderById();
}
