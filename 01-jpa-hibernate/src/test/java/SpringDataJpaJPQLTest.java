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
public class SpringDataJpaJPQLTest {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 根据 用户行业 和 用户等级 查询用户
     */
    @Test
    public void testFindOne() {
        List<CustomerEntity> entities = customerRepository
                .findCustomerEntitiesByCustIndustryAndCustLevel("IT", CustLevelEnum.HIGH.getCode());
        entities.forEach(System.out::println);
    }

    /**
     * 根据 用户行业 和 用户地址 查询用户，最后根据用户等级倒序排列
     */
    @Test
    public void testFindTwo() {
        List<CustomerEntity> entities = customerRepository
                .findCustomerEntitiesByCustIndustryAndCustAddress("Sale", "HeNan");
        entities.forEach(System.out::println);
    }

    /**
     * 根据 客户主键id 来更新 客户名称
     */
    @Test
    public void testUpdate() {
        int result = customerRepository.updateCustomerEntityNameById(7L, "Cloud");
        System.out.println(result);
    }

    /**
     * 根据 客户名称 模糊删除 客户信息
     */
    @Test
    public void testDelete() {
        int s = customerRepository.deleteCustomerEntitiesByCustNameLike("S");
        System.out.println(s);
    }
}