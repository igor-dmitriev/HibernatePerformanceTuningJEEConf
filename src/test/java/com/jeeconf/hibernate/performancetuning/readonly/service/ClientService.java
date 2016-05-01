package com.jeeconf.hibernate.performancetuning.readonly.service;

import com.jeeconf.hibernate.performancetuning.readonly.entity.Client;

/**
 * Created by Igor Dmitriev on 4/30/16
 */
public interface ClientService {
    Client findClient(Integer id);
}
