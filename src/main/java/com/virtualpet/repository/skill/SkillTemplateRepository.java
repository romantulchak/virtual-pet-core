package com.virtualpet.repository.skill;

import com.virtualpet.model.skills.template.SkillAbstractTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface SkillTemplateRepository extends JpaRepository<SkillAbstractTemplate, Long> {

    boolean existsByName(String name);

    Page<SkillAbstractTemplate> findAllByReferenceNotInOrReferenceIsNull(Collection<UUID> reference, Pageable pageable);

}
