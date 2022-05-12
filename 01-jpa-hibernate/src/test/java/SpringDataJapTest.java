import com.liboshuai001.config.SpringDataJpaConfig;
import com.liboshuai001.constant.enums.CustLevelEnum;
import com.liboshuai001.dao.CustomerRepository;
import com.liboshuai001.entity.CustomerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = "classpath:applicationContext.xml")
@ContextConfiguration(classes = SpringDataJpaConfig.class)
public class SpringDataJapTest {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 保存客户：调用 save(obj) 方法
     */
    @Test
    public void testSave() {
        CustomerEntity build = CustomerEntity.builder()
                .custAddress("HeNan")
                .custName("Spring")
                .custIndustry("Sale")
                .custPhone("12312312312")
                .custLevel(CustLevelEnum.MEDIUM.getCode())
                .custSource("58同城")
                .build();
        customerRepository.save(build);
    }

    /**
     * 修改客户：调用save(obj)方法
     * 对于 save 方法的解释：如果执行此方法是对象中存在id属性，即为更新操作会先根据id查询，再更新
     * 如果执行此方法中对象中不存在id属性，即为保存操作
     */
    @Test
    public void testUpdate() {
        // 根据 id 查询客户
        CustomerEntity customerDaoOne = customerRepository.findOne(3L);
        // 修改客户名称
        customerDaoOne.setCustName("测试修改客户名称");
        // 更新操作
        customerRepository.save(customerDaoOne);
    }

    /**
     * 根据 id 删除：调用 delete(id)方法
     */
    @Test
    public void testDelete() {
        customerRepository.delete(4L);
    }

    /**
     * 根据id查询：调用findOne(id)方法
     */
    @Test
    public void testFindById() {
        CustomerEntity customerEntity = customerRepository.findOne(5L);
        System.out.println(customerEntity);
    }
}
