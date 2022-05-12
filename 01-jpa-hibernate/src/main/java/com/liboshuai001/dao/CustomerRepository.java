package com.liboshuai001.dao;

import com.liboshuai001.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, JpaSpecificationExecutor<CustomerEntity> {

    /**
     * 使用 JPQL 方式，根据 用户行业 和 用户等级 查询用户
     *
     * @param custIndustry 用户行业
     * @param custLevel    用户等级
     * @return 用户列表
     */
    @Query(value = "from CustomerEntity where custIndustry = :custIndustry and custLevel = :custLevel")
    List<CustomerEntity> findCustomerEntitiesByCustIndustryAndCustLevel(@Param("custIndustry") String custIndustry,
                                                                        @Param("custLevel") String custLevel);

    /**
     * 使用 JPQL 方式，根据 用户行业 和 用户地址 查询用户，最后根据用户等级倒序排列
     *
     * @param custIndustry 用户行业
     * @param custAddress  用户地址
     * @return 用户列表
     */
    @Query("from CustomerEntity where custIndustry = ?1 and custAddress = ?2 order by custLevel desc")
    List<CustomerEntity> findCustomerEntitiesByCustIndustryAndCustAddress(String custIndustry, String custAddress);

    /**
     * 使用 JPQL 方式，根据 客户主键id 来更新 客户名称
     *
     * @param id       客户主键id
     * @param custName 客户名称
     * @return 更新结果int数值
     */
    @Transactional
    @Modifying
    @Query("update CustomerEntity set custName = :custName where id = :id")
    int updateCustomerEntityNameById(@Param("id") Long id, @Param("custName") String custName);

    /**
     * 使用 JPQL 方式，根据 客户名称 模糊删除 客户信息
     *
     * @param custName 客户名称
     * @return 删除操作影响的行数
     */
    @Transactional
    @Modifying
    @Query(value = "delete from CustomerEntity where custName like ?1%")
    int deleteCustomerEntitiesByCustNameLike(String custName);

    // JPQL 不支持普通的插入insert，只支持先查询出来，然后插入的方式，而且还是会提示报错
}
