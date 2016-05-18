package com.jeeconf.hibernate.performancetuning.batchfetching;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jeeconf.hibernate.performancetuning.BaseTest;
import com.jeeconf.hibernate.performancetuning.batchfetching.entity.Account;
import com.jeeconf.hibernate.performancetuning.batchfetching.entity.Client;
import org.junit.Test;

import java.util.List;

/**
 * Created by Igor Dmitriev on 5/18/16
 */
@DatabaseSetup("/nplusone.xml")
public class BatchFetchingTest extends BaseTest {

    @Test
    public void batchFetching() {
        List<Client> clients = em.createQuery("select c from com.jeeconf.hibernate.performancetuning.batchfetching.entity.Client c " +
                "where c.age > :age", Client.class)
                .setParameter("age", 18)
                .getResultList();
        clients.forEach(c -> c.getAccounts().size());
    }

    @Test
    public void batchSizeCache() {
        Account account1 = em.find(Account.class, 1);
        Account account2 = em.find(Account.class, 4);
        account1.getClient().getName();
    }

}
