package com.poly.datn.be.repo;

import com.poly.datn.be.domain.dto.RespProductDto;
import com.poly.datn.be.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("SELECT p.id, p.name, p.code, p.description, p.view, a.price, i.imageLink FROM Product p " +
            "inner join Attribute a on p.id = a.product.id " +
            "inner join Image i on p.id = i.product.id where a.size = :size and i.name = :name")
    List<Object[]> getAllProducts(@Param("size") Integer size, @Param("name") String name, Pageable pageable);
}
