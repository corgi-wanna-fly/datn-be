package com.poly.datn.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "account_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "fullname", nullable = false, length = 50)
    private String fullname;
    @Column(name = "gender", nullable = false, length = 10)
    private String gender;
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "birthdate", nullable = false)
    private Date birthDate;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
