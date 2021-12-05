package com.virtualpet.repository;

import com.virtualpet.model.sub.Currency;
import com.virtualpet.model.Sub;
import com.virtualpet.model.User;
import com.virtualpet.model.sub.Money;
import com.virtualpet.projection.SubMoneyProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubRepository extends JpaRepository<Sub, Long> {
    boolean existsByName(String name);

    int countSubByUser(User user);

    @Query(value = "SELECT s FROM Sub s left join s.user as u where u.id = ?1 order by s.id")
    List<Sub> getAllSubsForUser(Long id);

    Optional<SubMoneyProjection> findByIdAndName(long id, String name);

    Optional<Sub> findById(long id);

    @Modifying
    @Query("UPDATE Sub s SET s.currency = :currency, s.money = :money WHERE s.id = :id")
    void updateSubMoney(@Param("id") long id, @Param("currency") Currency currency, @Param("money") Money money);

    @Query(value = "SELECT s.id FROM Sub s WHERE s.user.id = :id")
    List<Long> findUserSubsId(@Param("id") long id);
}
