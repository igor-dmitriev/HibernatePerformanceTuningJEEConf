package com.jeeconf.hibernate.performancetuning.dirtychecking;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jeeconf.hibernate.performancetuning.BaseTest;
import org.junit.Test;

/**
 * Created by Igor Dmitriev on 5/18/16
 */
@DatabaseSetup("/readonly.xml")
public class DirtyCheckingTest extends BaseTest {

    @Test
    public void disableDirtyCheckingForQuery() {
        getSession().createQuery("select c from Client c")
                .setReadOnly(true)
                .list();
    }
}
