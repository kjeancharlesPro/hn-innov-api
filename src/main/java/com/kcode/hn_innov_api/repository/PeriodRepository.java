package com.kcode.hn_innov_api.repository;

import com.kcode.hn_innov_api.entity.PeriodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

@Repository
@RepositoryRestResource(path = "periods")
@CrossOrigin(origins = "*")
public interface PeriodRepository extends JpaRepository<PeriodEntity, Long> {

    PeriodEntity findFirstByStartDateAfterOrderByStartDateAsc(LocalDateTime targetDate);

    PeriodEntity findFirstByEndDateLessThanEqualOrderByEndDateDesc(LocalDateTime targetDate);
}
