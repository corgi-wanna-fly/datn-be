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
    @Query("SELECT p.id, p.name, p.code, p.description, p.view, a.price, i.imageLink, p.brand.name, p.sale.discount, p.isActive FROM Product p " +
            "inner join Attribute a on p.id = a.product.id " +
            "inner join Image i on p.id = i.product.id where a.size = :size and i.name = :name and p.isActive = :active")
    List<Object[]> getAllProducts(@Param("size") Integer size,
                                  @Param("name") String name,
                                  @Param("active") Boolean active,
                                  Pageable pageable);

    @Query("SELECT DISTINCT new com.poly.datn.be.domain.dto.RespProductDto(p.id, p.name, p.code, p.description, p.view, a.price, i.imageLink, p.brand.name, p.sale.discount, p.isActive) FROM Product p " +
            "inner join Attribute a on p.id = a.product.id " +
            "inner join Image i on p.id = i.product.id where a.size = :size and i.name = :name and p.name like :keyword or p.brand.name like :keyword")
    List<RespProductDto> searchAllByKeyword(@Param("size") Integer size,
                                            @Param("name") String name,
                                            @Param("keyword") String keyword,
                                            Pageable pageable);

    List<Product> getProductByBrand_Id(Long brandId);
}
