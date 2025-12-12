package com.kcode.hn_innov_api.service.impl;

import com.kcode.hn_innov_api.entity.StatusEntity;
import com.kcode.hn_innov_api.repository.StatusRepository;
import com.kcode.hn_innov_api.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository repository;

    @Override
    public StatusEntity getStatus() {
        return repository.findById(1L).orElseThrow();
    }

    @Override
    public void save(StatusEntity status) {
        repository.save(status);
    }
}
