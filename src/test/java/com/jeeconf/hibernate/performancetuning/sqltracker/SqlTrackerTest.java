package com.jeeconf.hibernate.performancetuning.sqltracker;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jeeconf.hibernate.performancetuning.BaseTest;
import com.jeeconf.hibernate.performancetuning.sqltracker.entity.Account;
import org.junit.Test;
import org.springframework.test.annotation.Commit;

import static com.jeeconf.hibernate.performancetuning.sqltracker.AssertSqlCount.assertSelectCount;

/**
 * Created by Igor Dmitriev on 5/1/16
 */

@DatabaseSetup("/sqltracker.xml")
public class SqlTrackerTest extends BaseTest {
    @Test
    @Commit
    public void test() {
        Account account1 = em.find(Account.class, 1);
        Account account2 = em.find(Account.class, 2);
        assertSelectCount(2);
    }
}
