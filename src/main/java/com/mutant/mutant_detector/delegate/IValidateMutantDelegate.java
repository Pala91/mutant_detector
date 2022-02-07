package com.mutant.mutant_detector.delegate;

import com.mutant.mutant_detector.dto.StatisticResponse;

public interface IValidateMutantDelegate {

    void isMutant(String[] dna);

    StatisticResponse statistics();

}
