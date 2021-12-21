package com.virtualpet.repository;

import com.virtualpet.model.skills.SkillAbstract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<SkillAbstract, Long> {

    boolean existsByName(String name);

    boolean existsByIdAndSubId(long id, long subId);

    Optional<SkillAbstract> findByIdAndShopNotNull(long id);

    Page<SkillAbstract> findAllBySubIdIsNull(Pageable pageable);
}
