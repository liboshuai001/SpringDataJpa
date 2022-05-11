package com.liboshuai001.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtil {
    // JPA 的实体管理器工厂，相当于 Hibernate 的 SessionFactory.
    private static EntityManagerFactory em;

    // 使用静态代码快赋值
    static {
        // 注意：该方法参数必须和persistence.xml 中 persistence-unit 标签 name 属性取值一致
        em = Persistence.createEntityManagerFactory("myJpa");
    }

    /**
     * 使用管理器工厂生产一个管理器对象
     * @return 管理器对象
     */
    public static EntityManager getEntityManager() {
        return em.createEntityManager();
    }
}
