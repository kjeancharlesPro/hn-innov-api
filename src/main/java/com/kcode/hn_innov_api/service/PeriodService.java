package com.kcode.hn_innov_api.service;

import com.kcode.hn_innov_api.entity.PeriodEntity;

public interface PeriodService {
    PeriodEntity getClosestPeriodFromNow();
}
