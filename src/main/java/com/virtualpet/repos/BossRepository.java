package com.virtualpet.repos;

import com.virtualpet.models.Boss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BossRepository extends JpaRepository<Boss, Long> {


}
