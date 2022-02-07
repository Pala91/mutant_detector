package com.mutant.mutant_detector.service.imp;

import com.mutant.mutant_detector.dto.StatisticResponse;
import com.mutant.mutant_detector.persistence.entity.Mutant;
import com.mutant.mutant_detector.persistence.repository.IMutantRepository;
import com.mutant.mutant_detector.service.ISqlMutantDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class SqlMutantDetectorImp implements ISqlMutantDetector {

    private IMutantRepository mutantRepository;

    @Autowired
    public SqlMutantDetectorImp(IMutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    @Override
    public Mutant saveSequence(String[] dna, boolean isMutant) {

        return mutantRepository.save(Mutant.builder().creationDatetime(new Date()).sequence(Arrays.toString(dna)).isMutant(isMutant).build());
    }

    @Override
    public StatisticResponse getStatistics() {
        List<Mutant> mutants = mutantRepository.findAll();

        long total = mutants.size();
        long mutant = mutants.stream().filter(Mutant::getIsMutant).count();
        return StatisticResponse.builder().countMutantDna(mutant).countHumanDna(total - mutant).
                ratio(total == 0 ? 0 : mutant / (double) total).build();

    }
}
