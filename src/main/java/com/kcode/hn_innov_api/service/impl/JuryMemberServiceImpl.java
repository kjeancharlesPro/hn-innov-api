package com.kcode.hn_innov_api.service.impl;

import com.kcode.hn_innov_api.entity.JuryMemberEntity;
import com.kcode.hn_innov_api.repository.JuryMemberRepository;
import com.kcode.hn_innov_api.service.JuryMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuryMemberServiceImpl implements JuryMemberService {

    @Autowired
    private JuryMemberRepository repository;

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<JuryMemberEntity> getAll() {
        return repository.findAll();
    }

}
