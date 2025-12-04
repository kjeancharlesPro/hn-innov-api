package com.kcode.hn_innov_api.service;

import com.kcode.hn_innov_api.entity.JuryMemberEntity;

import java.util.List;

public interface JuryMemberService {
    long count();

    List<JuryMemberEntity> getAll();
}
