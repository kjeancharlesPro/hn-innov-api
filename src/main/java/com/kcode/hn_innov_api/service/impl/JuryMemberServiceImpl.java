package com.kcode.hn_innov_api.service.impl;

import com.kcode.hn_innov_api.entity.JuryMemberEntity;
import com.kcode.hn_innov_api.repository.JuryMemberRepository;
import com.kcode.hn_innov_api.service.JuryMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class JuryMemberServiceImpl implements JuryMemberService {

    @Autowired
    private JuryMemberRepository repository;

    @Override
    public List<JuryMemberEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<String> getAllEmail() {
        return this.getAll().stream().map(JuryMemberEntity::getEmail).toList();
    }

    @Override
    public JuryMemberEntity getRandomJuryMember() {
        List<JuryMemberEntity> juryMembers = this.getAll();

        if (juryMembers == null || juryMembers.isEmpty()) {
            throw new IllegalArgumentException("La liste ne doit pas être vide");
        }

        Random rng = new Random();

        int index = rng.nextInt(juryMembers.size());
        return juryMembers.get(index);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public JuryMemberEntity getByEmail(String email) {
        return repository.findByEmail(email);
    }

}
