package com.jeeconf.hibernate.performancetuning.cache;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jeeconf.hibernate.performancetuning.BaseTest;
import com.jeeconf.hibernate.performancetuning.cache.entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import static com.jeeconf.hibernate.performancetuning.sqltracker.AssertSqlCount.assertSelectCount;

/**
 * Created by Igor Dmitriev on 4/30/16
 */
@DatabaseSetup("/cache.xml")
public class EntityCacheTest extends BaseTest {

    @Test
    public void secondLevel() {
        SessionFactory factory = getSessionFactory();
        try (Session s1 = factory.openSession()) {
            City city1 = s1.get(City.class, 1);
        }

        try (Session s2 = factory.openSession()) {
            City city2 = s2.get(City.class, 1);
        }
    }

    @Test
    public void queryCacheWithCacheableEntities() {
        SessionFactory factory = getSessionFactory();
        String query = "select c from City c";

        try (Session s1 = factory.openSession()) {
            s1.createQuery(query).setCacheable(true).list();
        }

        try (Session s2 = factory.openSession()) {
            s2.createQuery(query).setCacheable(true).list();
        }

        assertSelectCount(1);
    }

}
