package com.kcode.hn_innov_api.service.impl;

import com.kcode.hn_innov_api.entity.ParticipantEntity;
import com.kcode.hn_innov_api.repository.ParticipantRepository;
import com.kcode.hn_innov_api.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository repository;

    @Override
    public List<ParticipantEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<String> getAllEmail() {
        return repository.findAll().stream().map(ParticipantEntity::getEmail).toList();
    }

    @Override
    public void save(ParticipantEntity p) {
        repository.save(p);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
