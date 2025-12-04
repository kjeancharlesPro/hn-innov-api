package com.kcode.hn_innov_api.repository;

import com.kcode.hn_innov_api.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@RepositoryRestResource(path = "teams")
@CrossOrigin(origins = "*")
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
}
