package com.virtualpet.repository;

import com.virtualpet.model.sub.Currency;
import com.virtualpet.model.Sub;
import com.virtualpet.model.User;
import com.virtualpet.model.sub.Money;
import com.virtualpet.projection.SubMoneyCurrencyProjection;
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

    Optional<SubMoneyCurrencyProjection> findByIdAndName(long id, String name);

    @Query(value = "SELECT s.money FROM Sub s WHERE s.id = ?1")
    Optional<SubMoneyProjection> findSubMoney(long id);

    Optional<Sub> findById(long id);

    @Modifying
    @Query("UPDATE Sub s SET s.currency.money = s.currency.money + :money WHERE s.id = :id")
    void updateSubPlusCurrency(@Param("id") long id, @Param("money") long money);

    @Modifying
    @Query("UPDATE Sub s SET s.currency.money = :money WHERE s.id = :id")
    void updateSubCurrency(@Param("id") long id, @Param("money") long money);

    @Modifying
    @Query("UPDATE Sub s SET s.currency = :currency, s.money = :money WHERE s.id = :id")
    void updateSubMoneyCurrency(@Param("id") long id, @Param("currency") Currency currency, @Param("money") Money money);

    @Query(value = "SELECT s.id FROM Sub s WHERE s.user.id = :id")
    List<Long> findUserSubsId(@Param("id") long id);
}
