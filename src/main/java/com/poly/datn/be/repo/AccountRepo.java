package com.poly.datn.be.repo;

import com.poly.datn.be.entity.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    @Query("SELECT a.username, a.createDate, a.modifyDate, a.isActive , r.name," +
            " ad.fullname, ad.gender, ad.phone, ad.email, ad.address, ad.birthDate FROM Account a " +
            "left join AccountDetail ad on a.id = ad.account.id " +
            "left join Role r on a.id = r.id")
    List<Object[]> findAllSecond(Pageable pageable);

    @Query("SELECT a.username, a.createDate, a.modifyDate, a.isActive , r.name," +
            " ad.fullname, ad.gender, ad.phone, ad.email, ad.address, ad.birthDate FROM Account a " +
            "left join AccountDetail ad on a.id = ad.account.id " +
            "left join Role r on a.id = r.id where a.id = ?1")
    List<Object[]> findByIdSecond(Long id);

    @Query("SELECT a.username, a.createDate, a.modifyDate, a.isActive , r.name," +
            " ad.fullname, ad.gender, ad.phone, ad.email, ad.address, ad.birthDate FROM Account a " +
            "left join AccountDetail ad on a.id = ad.account.id " +
            "left join Role r on a.id = r.id where a.username = ?1")
    List<Object[]> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update Account a set a.isActive = ?1 where a.id = ?2")
    void deleteOrRestore(Boolean isActive, Long id);

    @Query("SELECT a.username, a.createDate, a.modifyDate, a.isActive , r.name," +
            " ad.fullname, ad.gender, ad.phone, ad.email, ad.address, ad.birthDate FROM Account a " +
            "left join AccountDetail ad on a.id = ad.account.id " +
            "left join Role r on a.id = r.id where a.isActive = ?1")
    List<Object[]> findAccountByIsActiveOrInactive(Boolean isActive, Pageable pageable);

    Account findAccountByUsername(String username);

}
