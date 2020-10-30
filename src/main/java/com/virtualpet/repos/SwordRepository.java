package com.virtualpet.repos;

import com.virtualpet.models.Items.Sword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwordRepository extends JpaRepository<Sword, Long> {
}
