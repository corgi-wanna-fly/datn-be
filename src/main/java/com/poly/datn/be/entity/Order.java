package com.poly.datn.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "fullname", length = 50, nullable = false)
    private String fullname;
    @Column(name = "phone", length = 11, nullable = false)
    private String phone;
    @Column(name = "address", length = 255, nullable = false)
    private String address;
    @Column(name = "email", length = 255, nullable = false)
    private String email;
    @Column(name = "total", nullable = false)
    private Double total;
    @Column(name = "note", length = 1000, nullable = true)
    private String note;
    @Column(name = "ship_date", nullable = true)
    private Date shipDate;
    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;
    @Column(name = "modify_date", nullable = false)
    private LocalDate modifyDate;
    @Column(name = "is_pending", nullable = false)
    private Boolean isPending;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<OrderDetail>  orderDetails;
    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
}
