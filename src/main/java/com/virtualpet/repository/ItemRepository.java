package com.virtualpet.repository;

import com.virtualpet.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByInventorySubIdAndHasDressed(long subId, boolean dressed);

}
