package com.virtualpet.repos;

import com.virtualpet.models.SubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTypeRepository extends JpaRepository<SubType, Long> {
}
