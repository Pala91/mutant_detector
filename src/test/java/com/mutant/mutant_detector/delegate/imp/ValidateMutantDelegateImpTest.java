package com.mutant.mutant_detector.delegate.imp;

import com.mutant.mutant_detector.dto.StatisticResponse;
import com.mutant.mutant_detector.exception.NoMutantException;
import com.mutant.mutant_detector.persistence.entity.Mutant;
import com.mutant.mutant_detector.service.ISqlMutantDetector;
import com.mutant.mutant_detector.service.imp.ValidateMutantServiceImp;
import com.mutant.mutant_detector.util.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidateMutantDelegateImpTest {

    @InjectMocks
    private ValidateMutantDelegateImp mutantDelegateImp;

    @Mock
    private ISqlMutantDetector sqlMutantDetector;

    @Mock
    private ValidateMutantServiceImp mutantService;

    @Test
    public void givenDNAMutantSequence_thenReturnTrue() {

        Mockito.doReturn(true).when(mutantService).isMutant(Any.getGenericDnaSequence());
        Mockito.doReturn(Mutant.builder().build()).when(sqlMutantDetector).saveSequence(Any.getGenericDnaSequence(), true);

        mutantDelegateImp.isMutant(Any.getGenericDnaSequence());

        Mockito.verify(mutantService).isMutant(Any.getGenericDnaSequence());
        Mockito.verify(sqlMutantDetector).saveSequence(Any.getGenericDnaSequence(), true);
    }

    @Test
    public void givenNoDNAMutantSequence_thenReturnTrue() {

        Mockito.doReturn(false).when(mutantService).isMutant(Any.getGenericDnaSequence());
        Mockito.doReturn(Mutant.builder().build()).when(sqlMutantDetector).saveSequence(Any.getGenericDnaSequence(), false);

        Assertions.assertThrows(NoMutantException.class, () -> {
            mutantDelegateImp.isMutant(Any.getGenericDnaSequence());
        });
    }

    @Test
    public void whenCallStatistic_thenReturnStatisticResponse() {

        Mockito.doReturn(Any.getStatisticResponse()).when(sqlMutantDetector).getStatistics();

        StatisticResponse statistic = mutantDelegateImp.statistics();

        Assertions.assertEquals(10, statistic.getCountHumanDna());
        Assertions.assertEquals(10, statistic.getCountMutantDna());
        Assertions.assertEquals(0.5, statistic.getRatio());

    }

}
