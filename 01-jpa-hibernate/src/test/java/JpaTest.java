import com.liboshuai001.constant.enums.CustLevelEnum;
import com.liboshuai001.entity.CustomerEntity;
import com.liboshuai001.utils.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpaTest {

    /**
     * 保存一个实体
     */
    @Test
    public void testAdd() {
        // 定义实体类对象
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustName("Jason");
        customerEntity.setCustAddress("ChangSha");
        customerEntity.setCustIndustry("IT");
        customerEntity.setCustLevel(CustLevelEnum.LOW.getCode());
        customerEntity.setCustPhone("423423890123");
        customerEntity.setCustSource("智联招聘");
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            // 获取实体类管理对象
            entityManager = JPAUtil.getEntityManager();
            // 获取事务管理对象
            transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            // 执行保存数据操作
            entityManager.persist(customerEntity);
            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 执行数据保存过程中，出现异常则需要回滚事务
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }

    /**
     * 删除一条数据
     */
    @Test
    public void testRemove() {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            // 获取实体管理对象
            entityManager = JPAUtil.getEntityManager();
            // 获取事务对象
            transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            // 执行删除数据操作
            CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, 1L);
            entityManager.remove(customerEntity);
            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 当删除数据异常时，执行回滚操作
            transaction.rollback();
        } finally {
            // 最后需要释放资源
            entityManager.close();
        }

    }

    /**
     * 根据id查询
     */
    @Test
    public void testGetOne() {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            // 执行查询操作
            CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, 2L);
            // 提交事务
            transaction.commit();
            // 打印查询的对象
            System.out.println("customerEntity = " + customerEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }

    /**
     * 使用JPQL查询全部
     */
    @Test
    public void testJPQLFindAll() {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            // 获取实体类管理对象
            entityManager = JPAUtil.getEntityManager();
            // 获取事务对象
            transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            // 创建 query 对象
            String jpql = "from CustomerEntity";
            Query query = entityManager.createQuery(jpql);
            // 查询并得到返回结果
            query.getResultList().forEach(System.out::println);
            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }

    /**
     * 使用JPQL分页查询
     */
    @Test
    public void testJPQLFindPaged() {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            // 获取实体类管理对象
            entityManager = JPAUtil.getEntityManager();
            // 获取事务对象
            transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            // 创建 query 对象
            String jpql = "from CustomerEntity";
            Query query = entityManager.createQuery(jpql);
            // 设置起始索引
            query.setFirstResult(0);
            // 设置每页显示的条数
            query.setMaxResults(2);
            // 查询并返回结果
            query.getResultList().forEach(System.out::println);
            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            // 回滚事务
            e.printStackTrace();
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }

    /**
     * 使用JPQL条件查询
     */
    @Test
    public void testJPQLFindCondition() {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            // 获取实体类管理对象
            entityManager = JPAUtil.getEntityManager();
            // 获取事务对象
            transaction = entityManager.getTransaction();
            // 事务开启
            transaction.begin();
            // 创建 query 对象
            String jpql = "from CustomerEntity where custName like ?1";
            Query query = entityManager.createQuery(jpql);
            // 对占位符赋值，从1开始
            query.setParameter(1,"B%");
            // 查询并得到返回结果
            Object singleResult = query.getSingleResult(); // 得到唯一的结果集对象
            System.out.println("singleResult = " + singleResult);
            // 提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }

    /**
     * 使用JPQL排序查询
     */
    @Test
    public void testJPQLFindByOrder() {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            // 获取实体类管理对象
            entityManager = JPAUtil.getEntityManager();
            // 获取事务对象
            transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            // 创建 query 对象
            String jpql = "from CustomerEntity order by id desc";
            Query query = entityManager.createQuery(jpql);
            // 查询并得到返回结果集
            query.getResultList().forEach(System.out::println);
            // 提交事务
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            // 关闭资源
            entityManager.close();
        }
    }

    /**
     * 使用JPQL统计查询
     */
    @Test
    public void testJPQLFindCount() {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            // 获取实体类对象
            entityManager = JPAUtil.getEntityManager();
            // 获取事务对象
            transaction = entityManager.getTransaction();
            // 开启事务
            transaction.begin();
            // 创建 query 对象
            String jpql = "select count(id) from CustomerEntity";
            Query query = entityManager.createQuery(jpql);
            // 查询并得到返回结果
            Object singleResult = query.getSingleResult();
            // 提交事务
            transaction.commit();
            // 打印输出数据
            System.out.println(singleResult);
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            // 释放资源
            entityManager.close();
        }
    }
}
