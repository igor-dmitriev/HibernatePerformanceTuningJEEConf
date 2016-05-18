package com.jeeconf.hibernate.performancetuning.subselect;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jeeconf.hibernate.performancetuning.BaseTest;
import com.jeeconf.hibernate.performancetuning.subselect.entity.Client;
import org.junit.Test;

import java.util.List;

/**
 * Created by Igor Dmitriev on 5/18/16
 */
@DatabaseSetup("/nplusone.xml")
public class SubselectTest extends BaseTest {
    @Test
    public void subSelect() {
        List<Client> clients = em.createQuery("select c from Client c " +
                "where c.age > :age", Client.class)
                .setParameter("age", 18)
                .getResultList();
        clients.forEach(c -> c.getAccounts().size());
    }
}
