package com.jeeconf.hibernate.performancetuning.readonly.service;

import com.jeeconf.hibernate.performancetuning.readonly.entity.Client;
import com.jeeconf.hibernate.performancetuning.readonly.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Igor Dmitriev on 4/30/16
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Client findClient(Integer id) {
        return clientRepository.findOne(id);
    }
}
