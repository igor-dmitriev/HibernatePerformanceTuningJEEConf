package com.jeeconf.hibernate.performancetuning.readonly;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jeeconf.hibernate.performancetuning.BaseTest;
import com.jeeconf.hibernate.performancetuning.readonly.dto.ClientSummary;
import com.jeeconf.hibernate.performancetuning.readonly.entity.Account;
import com.jeeconf.hibernate.performancetuning.readonly.entity.Account_;
import com.jeeconf.hibernate.performancetuning.readonly.entity.Client;
import com.jeeconf.hibernate.performancetuning.readonly.entity.Client_;
import org.hibernate.transform.Transformers;
import org.junit.Test;

import javax.persistence.criteria.*;
import java.util.Map;

/**
 * Created by Igor Dmitriev on 4/30/16
 */
@DatabaseSetup("/readonly.xml")
public class ReadOnlyTest extends BaseTest {
    @Test
    public void hqlConstructor() {
        String query = "select new com.jeeconf.hibernate.performancetuning.readonly.dto.ClientSummary(c.id,c.name,sum(a.amount)) " +
                "from Client c " +
                "left join c.accounts a " +
                "where c.id=:id group by a.client";
        //noinspection unchecked
        ClientSummary result = (ClientSummary) getSession().createQuery(query)
                .setParameter("id", 1).uniqueResult();
        System.out.println(result);
    }

    @Test
    public void aliasToBeanResultTransformer() {
        String query = "select c.id as clientId," +
                "c.name as clientName," +
                "sum(a.amount) as totalAmount " +
                "from Client c " +
                "left join c.accounts a where c.id = :id " +
                "group by a.client";
        //noinspection unchecked
        ClientSummary result = (ClientSummary) getSession().createQuery(query)
                .setParameter("id", 1)
                .setResultTransformer(Transformers.aliasToBean(ClientSummary.class))
                .uniqueResult();
        System.out.println(result);
    }

    @Test
    public void criteriaProjection() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ClientSummary> query = cb.createQuery(ClientSummary.class);
        Root<Client> root = query.from(Client.class);
        Join<Client, Account> accountJoin = root.join(Client_.accounts, JoinType.LEFT);
        query.select(cb.construct(ClientSummary.class, root.get(Client_.id), root.get(Client_.name), cb.sum(accountJoin.get(Account_.amount))));
        ClientSummary result = em.createQuery(query.where(cb.equal(root.get(Client_.id), 1)).groupBy(accountJoin.get(Account_.client)))
                .getSingleResult();
        System.out.println(result);
    }

    @Test
    public void mappingQueryAsMap() {
        String query = "select c.id as clientId," +
                "c.name as clientName," +
                "sum(a.amount) as totalAmount " +
                "from Client c " +
                "left join c.accounts a where c.id = :id " +
                "group by a.client";
        Map<String, Object> result = (Map<String, Object>) getSession().createQuery(query)
                .setParameter("id", 1)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .uniqueResult();
        System.out.println(result.get("clientId") + " " + result.get("clientName") + " " + result.get("totalAmount"));
    }
}
