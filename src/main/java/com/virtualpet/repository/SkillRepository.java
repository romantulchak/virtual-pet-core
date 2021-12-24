package com.virtualpet.repository;

import com.virtualpet.model.skills.SkillAbstract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SkillRepository extends JpaRepository<SkillAbstract, Long> {

    boolean existsByName(String name);

    boolean existsByReferenceAndSubId(UUID reference, long subId);

    Page<SkillAbstract> findAllBySubIsNull(Pageable pageable);

    Optional<SkillAbstract> findByIdAndShopNotNull(long id);

    Page<SkillAbstract> findAllBySubIdIsNullAndReferenceNotIn(Collection<UUID> reference, Pageable pageable);

    Page<SkillAbstract> findAllBySubIdIsNullAndReferenceNotInOrReferenceIsNull(Collection<UUID> reference, Pageable pageable);

    @Query(value = "SELECT s.reference FROM SkillAbstract s WHERE s.sub.id = :id")
    List<UUID> findSubSkillReferences(@Param("id") long id);
}
