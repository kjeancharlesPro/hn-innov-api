package com.kcode.hn_innov_api.service;

import com.kcode.hn_innov_api.entity.StatusEntity;

public interface StatusService {
    StatusEntity getStatus();

    void save(StatusEntity status);
}
