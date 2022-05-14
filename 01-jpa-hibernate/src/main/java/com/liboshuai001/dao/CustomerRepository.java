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

    /**
     * 使用 原生SQL 方式，根据 客户来源 查询 客户信息
     *
     * @param custSource 客户来源
     * @return 查询的客户信息
     */
    @Query(value = "SELECT * FROM cst_customer WHERE cust_source = :custSource", nativeQuery = true)
    List<CustomerEntity> findCustomerEntitiesByCustSource(@Param(value = "custSource") String custSource);

    /**
     * 使用 原生SQL 方式，设置指定 客户id 的 客户名称
     *
     * @param custName 客户名称
     * @param id       客户id
     * @return 更新影响的数据行数
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE cst_customer SET cust_name = ?1 WHERE id = ?2", nativeQuery = true)
    int updateCustomerEntitiesById(String custName, Long id);

    /**
     * 使用 原生SQL 方式，删除指定客户id的客户信息
     *
     * @param id 客户id
     * @return 删除影响的数据行数
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cst_customer WHERE id = :id", nativeQuery = true)
    int deleteCustomerEntityById(@Param("id") Long id);

    /**
     * 使用 原生SQL，插入指定客户信息
     *
     * @param custName     客户名称
     * @param custSource   客户来源
     * @param custIndustry 客户所属行业
     * @param custLevel    客户级别
     * @param custAddress  客户联系地址
     * @param custPhone    客户联系电话
     * @return 插入的数据行数
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO cst_customer (cust_name, cust_source, cust_industry, cust_level, cust_address, cust_phone) " +
            "VALUES (:custName, :custSource, :custIndustry, :custLevel, :custAddress, :custPhone);", nativeQuery = true)
    int insertCustomerEntity(@Param(value = "custName") String custName, @Param(value = "custSource") String custSource,
                             @Param(value = "custIndustry") String custIndustry, @Param(value = "custLevel") String custLevel,
                             @Param(value = "custAddress") String custAddress, @Param(value = "custPhone") String custPhone);

    /**
     * 方法命名规则查询方式，根据 客户名称 查询客户信息
     * 关键字：findBy，根据指定条件查询
     *
     * @param custName 客户名称
     * @return 查询到的客户信息
     */
    List<CustomerEntity> findByCustName(String custName);

    /**
     * 方法命名规则查询方式，根据客户名称查询客户信息并去重
     * 关键字：Distinct，去重
     *
     * @param custName 客户名称
     * @return 查询到的客户信息
     */
    List<CustomerEntity> findDistinctByCustName(String custName);

    /**
     * 方法命名规则查询方式，根据客户名称和客户级别查询客户信息
     * 关键字：And，合并查询条件
     *
     * @param custName  客户名称
     * @param custLevel 客户级别
     * @return 查询到的客户信息
     */
    List<CustomerEntity> findByCustNameAndCustLevel(String custName, String custLevel);

    /**
     * 方法命名规则查询方式，根据客户名称或客户级别查询客户信息
     * 关键字：Or，或者查询条件
     *
     * @param custName    客户名称
     * @param custAddress 客户联系地址
     * @return 查询到的客户信息
     */
    List<CustomerEntity> findByCustNameOrCustAddress(String custName, String custAddress);

    /**
     * 方法命名规则查询方式，根据客户名称查询客户信息
     * 关键字：Is，与之间findByCustName一致
     *
     * @param custName 客户名称
     * @return 查询到的客户信息
     */
    List<CustomerEntity> findByCustNameIs(String custName);

    /**
     * 方法命名规则查询方式，根据客户名称查询客户信息
     * 关键字：Equals，与之间findByCustName一致
     *
     * @param custName 客户名称
     * @return 查询到的客户信息
     */
    List<CustomerEntity> findByCustNameEquals(String custName);

    /**
     * 方法命名规则查询方式，根据客户id区间查询客户信息
     * 关键字：Between，值1与值2之间（前闭后开）
     *
     * @param starId 开始id
     * @param endId  结束id
     * @return 查询到的客户信息
     */
    List<CustomerEntity> findByIdBetween(Long starId, Long endId);
}
