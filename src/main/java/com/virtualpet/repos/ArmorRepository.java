package com.virtualpet.repos;

import com.virtualpet.models.Items.Armor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmorRepository extends JpaRepository<Armor, Long> {
}
