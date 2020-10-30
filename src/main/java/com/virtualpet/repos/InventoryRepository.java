package com.virtualpet.repos;

import com.virtualpet.models.Inventory;
import com.virtualpet.models.Items.Sword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {



}
