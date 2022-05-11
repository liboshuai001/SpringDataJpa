package com.liboshuai001.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cst_customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 主键

    @Column(name = "cust_name")
    private String custName; // 客户名称

    @Column(name = "cust_source")
    private String custSource; // 客户来源

    @Column(name = "cust_industry")
    private String custIndustry; // 客户所属行业

    @Column(name = "cust_level")
    private String custLevel; // 客户级别

    @Column(name = "cust_address")
    private String custAddress; // 客户联系地址

    @Column(name = "cust_phone")
    private String custPhone; // 客户联系电话

}
