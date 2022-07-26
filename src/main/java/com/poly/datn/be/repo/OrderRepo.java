package com.poly.datn.be.repo;

import com.poly.datn.be.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    Page<Order> findAllByAccount_Id(Long id, Pageable pageable);
    Page<Order> findOrderByAccount_Id(Long id, Pageable pageable);
    Page<Order> findOrderByAccount_IdAndOrderStatus_Id(Long accountId, Long orderStatusId, Pageable pageable);
    Page<Order> findOrderByOrderStatus_Id(Long id, Pageable pageable);
    @Query("SELECT o FROM Order o WHERE o.orderStatus.id = :id and year(o.createDate) = :year and month(o.createDate) = :month")
    Page<Order> findOrderByOrderStatusAndYearAndMonth(@Param("id") Long id, @Param("year") Integer year, @Param("month") Integer month, Pageable pageable);
    @Query("SELECT o FROM Order o WHERE year(o.createDate) = :year and month(o.createDate) = :month")
    Page<Order> findOrderByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month, Pageable pageable);
    @Query("SELECT o FROM Order o WHERE o.orderStatus.id = :id and o.createDate between :from and :to")
    Page<Order> findOrderByOrderStatusBetweenDate(@Param("id") Long id, @Param("from")LocalDate from, @Param("to") LocalDate to, Pageable pageable);
    @Query("SELECT o FROM Order o WHERE o.createDate between :from and :to")
    Page<Order> findOrderBetweenDate(@Param("from")LocalDate from, @Param("to") LocalDate to, Pageable pageable);
}
