package com.poly.datn.be.repo;

import com.poly.datn.be.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Query("select e FROM Category e")
    Page<Category> categoryList(Pageable pageable);
}
