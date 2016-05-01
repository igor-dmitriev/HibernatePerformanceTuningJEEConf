package com.jeeconf.hibernate.performancetuning.nplusone;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jeeconf.hibernate.performancetuning.BaseTest;
import com.jeeconf.hibernate.performancetuning.nplusone.entity.Account;
import com.jeeconf.hibernate.performancetuning.nplusone.entity.Client;
import org.junit.Test;

import java.util.List;

/**
 * Created by Igor Dmitriev on 4/30/16
 */
@DatabaseSetup("/nplusone.xml")
public class NplusOneTest extends BaseTest {
    @Test
    public void npo() {
        List<Account> clients = em.createQuery("select a from Account a", Account.class)
                .getResultList();
        clients.forEach(a -> a.getClient().getAge());
    }

    @Test
    public void batchSizeCache() {
        Account account1 = em.find(Account.class, 1);
        Account account2 = em.find(Account.class, 4);
        account1.getClient().getName();
    }

    @Test
    public void batchSizeFetchPartial() {
        List<Client> clients = em.createQuery("select c from Client c", Client.class)
                .getResultList();
        clients.get(0).getAccounts().size();
    }
}
