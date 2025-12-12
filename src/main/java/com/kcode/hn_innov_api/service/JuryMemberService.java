package com.kcode.hn_innov_api.service;

import com.kcode.hn_innov_api.entity.JuryMemberEntity;

import java.util.List;

public interface JuryMemberService {

    List<JuryMemberEntity> getAll();

    List<String> getAllEmail();

    JuryMemberEntity getRandomJuryMember();

    void deleteAll();
}
