import com.liboshuai001.config.SpringDataJpaConfig;
import com.liboshuai001.dao.CustomerRepository;
import com.liboshuai001.entity.CustomerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDataJpaConfig.class)
public class SpringDataJpaSpecificationsTest {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 使用Specification完成条件查询
     */
    @Test
    public void testSpecifications() {
        // 使用匿名内部类的方式创建一个Specification实现类，并实现toPredicate
        List<CustomerEntity> customerEntities = customerRepository.findAll(new Specification<CustomerEntity>() {
            @Override
            public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // root：从实体CustomerEntity对象中按照custName属性进行查询
                // cb: 构建查询，添加查询方式   like：模糊匹配
                return criteriaBuilder.like(root.get("custName").as(String.class), "B%");
            }
        });
        customerEntities.forEach(System.out::println);
    }

    /**
     * 使用Specification完成条件查询和分页查询
     */
    @Test
    public void testPage() {
        Pageable pageRequest = new PageRequest(0, 2);
        // 使用匿名内部类的方式创建一个Specification实现类，并实现toPredicate
        Page<CustomerEntity> customerPageS = customerRepository.findAll(new Specification<CustomerEntity>() {
            @Override
            public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // root：从实体CustomerEntity对象中按照custName属性进行查询
                // cb: 构建查询，添加查询方式   like：模糊匹配
                return criteriaBuilder.like(root.get("custName").as(String.class), "B%");
            }
        }, pageRequest);
        System.out.println(customerPageS.getContent());
    }
}
