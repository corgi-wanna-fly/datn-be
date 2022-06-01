package com.poly.datn.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
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
    @Column(name = "id")
    private Long id;
    @Column(name = "fullname", length = 50)
    private String fullname;
    @Column(name = "phone", length = 11)
    private String phone;
    @Column(name = "address", length = 255)
    private String address;
    @Column(name = "total")
    private Double total;
    @Column(name = "note", length = 1000)
    private String note;
    @Column(name = "ship_date")
    private Date shipDate;
    @Column(name = "create_date")
    private LocalDate createDate;
    @Column(name = "modify_date")
    private LocalDate modifyDate;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Collection<OrderDetail>  orderDetails;
    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;
}
