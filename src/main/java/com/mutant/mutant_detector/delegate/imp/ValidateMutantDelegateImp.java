package com.mutant.mutant_detector.delegate.imp;

import com.mutant.mutant_detector.delegate.IValidateMutantDelegate;
import com.mutant.mutant_detector.dto.StatisticResponse;
import com.mutant.mutant_detector.exception.NoMutantException;
import com.mutant.mutant_detector.service.ISqlMutantDetector;
import com.mutant.mutant_detector.service.IValidateMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ValidateMutantDelegateImp implements IValidateMutantDelegate {

    private final IValidateMutantService validateMutantService;
    private final ISqlMutantDetector sqlMutantDetector;

    @Autowired
    public ValidateMutantDelegateImp(IValidateMutantService validateMutantService, ISqlMutantDetector sqlMutantDetector) {
        this.validateMutantService = validateMutantService;
        this.sqlMutantDetector = sqlMutantDetector;
    }

    @Override
    public void isMutant(String[] dna) {

        boolean isMutant = validateMutantService.isMutant(Arrays.stream(dna).
                map(String::toUpperCase).toArray(String[]::new));

        sqlMutantDetector.saveSequence(dna, isMutant);

        if (!isMutant) {
            throw new NoMutantException();
        }
    }

    @Override
    public StatisticResponse statistics() {

        return sqlMutantDetector.getStatistics();
    }
}
