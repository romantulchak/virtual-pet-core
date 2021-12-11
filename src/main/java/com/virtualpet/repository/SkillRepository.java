package com.virtualpet.repository;

import com.virtualpet.model.SkillAbstract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<SkillAbstract, Long> {
}
