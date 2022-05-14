import com.liboshuai001.config.SpringDataJpaConfig;
import com.liboshuai001.constant.enums.CustLevelEnum;
import com.liboshuai001.dao.CustomerRepository;
import com.liboshuai001.entity.CustomerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDataJpaConfig.class)
public class SpringDataJpaByNameTest {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 关键字：FindBy
     * 根据 客户名称 查询客户信息
     */
    @Test
    public void testFindByCustName() {
        List<CustomerEntity> customerEntities = customerRepository.findByCustName("谢奕欣");
        customerEntities.forEach(System.out::println);
    }

    /**
     * 关键字：Distinct
     * 根据客户名称查询客户信息并去重
     */
    @Test
    public void testFindDistinctByCustName() {
        List<CustomerEntity> customerEntities = customerRepository.findDistinctByCustName("Bernardo");
        customerEntities.forEach(System.out::println);
    }

    /**
     * 关键字：And
     * 根据客户名称和客户级别查询客户信息
     */
    @Test
    public void testFindByCustNameAndCustLevel() {
        List<CustomerEntity> customerEntities = customerRepository
                .findByCustNameAndCustLevel("Bernardo", CustLevelEnum.MEDIUM.getCode());
        customerEntities.forEach(System.out::println);
    }

    /**
     * 关键字：Or
     * 根据客户名称或客户级别查询客户信息
     */
    @Test
    public void testFindByCustNameOrCustAddress() {
        List<CustomerEntity> customerEntities = customerRepository
                .findByCustNameOrCustAddress("Li", "ChangSha");
        customerEntities.forEach(System.out::println);
    }

    /**
     * 关键字：Is
     * 根据客户名称查询客户信息
     */
    @Test
    public void testFindByCustNameIs() {
        List<CustomerEntity> customerEntities = customerRepository.findByCustNameIs("简王");
        customerEntities.forEach(System.out::println);
    }

    /**
     * 关键字：Equals
     * 根据客户名称查询客户信息
     */
    @Test
    public void testFindByCustNameEquals() {
        List<CustomerEntity> customerEntities = customerRepository.findByCustNameEquals("Bernardo");
        customerEntities.forEach(System.out::println);
    }

    /**
     * 关键字：Between
     * 根据客户id区间查询客户信息
     */
    @Test
    public void testFindByIdBetween() {
        List<CustomerEntity> customerEntities = customerRepository.findByIdBetween(5L, 10L);
        customerEntities.forEach(System.out::println);
    }
}
