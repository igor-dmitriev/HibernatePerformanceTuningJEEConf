package com.jeeconf.hibernate.performancetuning.cache;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jeeconf.hibernate.performancetuning.BaseTest;
import com.jeeconf.hibernate.performancetuning.cache.entity.City;
import org.hibernate.annotations.QueryHints;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Cache;
import javax.persistence.EntityManagerFactory;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Igor Dmitriev / Mikalai Alimenkou on 4/30/16
 */
@DatabaseSetup("/cache.xml")
public class EntityCacheTest extends BaseTest {

    @Autowired
    EntityManagerFactory emf;

    @Test
    public void secondLevelCache() {
        City city = em.find(City.class, 1);
        Cache secondLevelCache = emf.getCache();
        assertTrue(secondLevelCache.contains(City.class, 1));
        //secondLevelCache.evict(City.class, 1);
        em.clear(); // clear first level cache
        City cachedCity = em.find(City.class, 1);
    }

    @Test
    public void queryCache() {
        String query = "select c from City c";
        executeCacheableQuery(query);
        em.clear();
        executeCacheableQuery(query);
    }

    @Test
    public void queryCacheInConjunctionWithSecondLevel() {
        String query = "select c from Client c";
        executeCacheableQuery(query);
        em.clear();
        executeCacheableQuery(query);
    }

    private void executeCacheableQuery(String query) {
        em.createQuery(query).setHint(QueryHints.CACHEABLE, Boolean.TRUE.toString()).getResultList();
    }
}
