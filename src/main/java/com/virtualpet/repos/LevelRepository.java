package com.virtualpet.repos;

import com.virtualpet.models.Level;
import com.virtualpet.models.Sub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LevelRepository extends JpaRepository<Level, Long> {



    @Query(value = "SELECT l from Level l where l.subs in :sub")
    Level findLevelForSub(@Param("sub")Sub sub);

}
