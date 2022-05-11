import com.liboshuai001.constant.enums.CustLevelEnum;
import com.liboshuai001.dao.CustomerDao;
import com.liboshuai001.entity.CustomerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataJapTest {

    @Autowired
    private CustomerDao customerDao;

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
        customerDao.save(build);
    }
}
