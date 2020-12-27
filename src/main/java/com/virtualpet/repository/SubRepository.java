package com.virtualpet.repository;

import com.virtualpet.model.Sub;
import com.virtualpet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubRepository extends JpaRepository<Sub, Long> {
    boolean existsByName(String name);

    int countSubByUser(User user);

    @Query(value = "SELECT s FROM Sub s left join s.user as u where u.id = ?1")
    List<Sub> getAllSubsForUser(Long id);
}
