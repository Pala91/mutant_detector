package com.mutant.mutant_detector.service;

import com.mutant.mutant_detector.dto.StatisticResponse;
import com.mutant.mutant_detector.persistence.entity.Mutant;

public interface ISqlMutantDetector {

    Mutant saveSequence(String [] dna, boolean isMutant);

    StatisticResponse getStatistics();
}
