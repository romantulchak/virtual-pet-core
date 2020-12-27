package com.virtualpet.repository;

import com.virtualpet.model.DressedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DressedItemRepository extends JpaRepository<DressedItem, Long> {
}
