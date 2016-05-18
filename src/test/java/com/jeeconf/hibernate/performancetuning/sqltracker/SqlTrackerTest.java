package com.jeeconf.hibernate.performancetuning.sqltracker;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jeeconf.hibernate.performancetuning.BaseTest;
import com.jeeconf.hibernate.performancetuning.sqltracker.entity.Account;
import org.junit.Test;

/**
 * Created by Igor Dmitriev / Mikalai Alimenkou on 5/1/16
 */

@DatabaseSetup("/sqltracker.xml")
public class SqlTrackerTest extends BaseTest {

    @Test
    public void showStatistics() {
        Account account = em.find(Account.class, 1);
    }

    @Test
    public void sqlCountAssertion() {
        AssertSqlCount.reset();
        Account account1 = em.find(Account.class, 1);
        Account account2 = em.find(Account.class, 2);
        AssertSqlCount.assertSelectCount(2);
    }
}
