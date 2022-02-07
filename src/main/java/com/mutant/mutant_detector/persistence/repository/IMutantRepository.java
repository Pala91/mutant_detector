package com.mutant.mutant_detector.persistence.repository;

import com.mutant.mutant_detector.dto.StatisticResponse;
import com.mutant.mutant_detector.persistence.entity.Mutant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMutantRepository extends CrudRepository<Mutant, Long> {

    List<Mutant> findAll();
}
