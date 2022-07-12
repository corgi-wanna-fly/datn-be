package com.poly.datn.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "vouchers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", length = 12, nullable = false, unique = true)
    private String code;
    @Column(name = "create_date")
    private LocalDate createDate;
    @Future
    @Column(name = "expire_date")
    private LocalDate expireDate;
    @Column(name = "discount")
    private Integer discount;

    @Column(name = "count")
    @Min(1)
    private Integer count;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @OneToMany(mappedBy = "voucher")
    @JsonIgnore
    private Collection<Order> orders;
}
