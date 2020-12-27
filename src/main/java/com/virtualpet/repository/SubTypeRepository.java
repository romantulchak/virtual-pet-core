package com.virtualpet.repository;

import com.virtualpet.model.SubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTypeRepository extends JpaRepository<SubType, Long> {
}
