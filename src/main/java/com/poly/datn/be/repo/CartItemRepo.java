package com.poly.datn.be.repo;

import com.poly.datn.be.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    @Query("SELECT c.attribute.id, i.imageLink, p.name, c.attribute.size, c.attribute.price, c.quantity FROM CartItem c INNER JOIN Product p on c.attribute.product.id = p.id INNER JOIN Image i on p.id = i.product.id WHERE c.account.id = :id and i.name = :name")
    List<Object[]> getCartItemByAccountId(@Param("id") Long id, @Param("name") String name);
}