package com.kcode.hn_innov_api.service;

import com.kcode.hn_innov_api.entity.ParticipantEntity;

import java.util.List;

public interface ParticipantService {

    List<ParticipantEntity> getAll();

    List<String> getAllEmail();

    void save(ParticipantEntity p);

    void deleteAll();

    ParticipantEntity getByEmail(String email);
}
