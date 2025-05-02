package com.aet.api.repositories;

import com.aet.api.model.Trecho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrechoRepository extends JpaRepository<Trecho, Long> {

}
