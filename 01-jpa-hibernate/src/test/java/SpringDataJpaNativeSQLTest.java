import com.liboshuai001.config.SpringDataJpaConfig;
import com.liboshuai001.constant.enums.CustLevelEnum;
import com.liboshuai001.dao.CustomerRepository;
import com.liboshuai001.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDataJpaConfig.class)
public class SpringDataJpaNativeSQLTest {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 根据 客户来源 查询 客户信息
     */
    @Test
    public void testFindCustomerBySource() {
        List<CustomerEntity> customerEntities = customerRepository.findCustomerEntitiesByCustSource("Boss");
        customerEntities.forEach(System.out::println);
    }

    /**
     * 设置指定 客户id 的 客户名称
     */
    @Test
    public void testUpdateCustomerEntitiesById() {
        int result = customerRepository.updateCustomerEntitiesById("谢奕欣", 5L);
        System.out.println("更新影响的数据行数: " + result);
    }

    /**
     * 删除指定客户id的客户信息
     */
    @Test
    public void testDeleteCustomerEntityById() {
        int result = customerRepository.deleteCustomerEntityById(8L);
        System.out.println("删除影响的数据行数：" + result);
    }

    /**
     * 插入指定客户信息
     */
    @Test
    public void testInsertCustomerEntity() {
        int result = customerRepository.insertCustomerEntity("简王", "北宋", "宰相",
                CustLevelEnum.HIGH.getCode(), "HeNan", "13580646357");
        System.out.println("插入的数据行数：" + result);
    }

}
