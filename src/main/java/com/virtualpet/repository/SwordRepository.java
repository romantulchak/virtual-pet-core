package com.virtualpet.repository;

import com.virtualpet.model.items.Sword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwordRepository extends JpaRepository<Sword, Long> {
}
