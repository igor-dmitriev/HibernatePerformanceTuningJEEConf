package com.jeeconf.hibernate.performancetuning.readonly.repository;

import com.jeeconf.hibernate.performancetuning.readonly.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Igor Dmitriev on 4/30/16
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
