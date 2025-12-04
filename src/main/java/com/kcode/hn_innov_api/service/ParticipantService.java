package com.kcode.hn_innov_api.service;

import com.kcode.hn_innov_api.entity.ParticipantEntity;

import java.util.List;

public interface ParticipantService {
    long count();

    List<ParticipantEntity> getAll();
}
