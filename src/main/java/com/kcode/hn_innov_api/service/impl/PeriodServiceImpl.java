package com.kcode.hn_innov_api.service.impl;

import com.kcode.hn_innov_api.entity.PeriodEntity;
import com.kcode.hn_innov_api.repository.PeriodRepository;
import com.kcode.hn_innov_api.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodRepository repository;

    @Override
    public PeriodEntity getClosestPeriodFromNow() {

        return repository.findFirstByStartDateAfterOrderByStartDateAsc(LocalDateTime.now());
    }
}
