package com.virtualpet.repository;

import com.virtualpet.model.skills.DefenceSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefenceSkillRepository extends JpaRepository<DefenceSkill, Long> {
}
