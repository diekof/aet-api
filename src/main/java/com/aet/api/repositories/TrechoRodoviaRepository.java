package com.aet.api.repositories;

import com.aet.api.model.TrechoRodovia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrechoRodoviaRepository extends JpaRepository<TrechoRodovia, Integer> {

    @Query("SELECT tr FROM TrechoRodovia tr WHERE tr.trechoId = :trechoId")
    List<TrechoRodovia> findByTrechoId(Long trechoId);
}
