package com.poly.datn.be.repo;

import com.poly.datn.be.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
    @Query(value = "SELECT * FROM store.order_detail where order_id = :id", nativeQuery = true)
    List<OrderDetail> findAllByOrder_Id(@Param("id") Long id);
}
