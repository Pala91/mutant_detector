package com.mutant.mutant_detector.service.imp;

import com.mutant.mutant_detector.dto.StatisticResponse;
import com.mutant.mutant_detector.persistence.entity.Mutant;
import com.mutant.mutant_detector.persistence.repository.IMutantRepository;
import com.mutant.mutant_detector.util.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class SqlMutantDetectorImpTest {

    @InjectMocks
    private SqlMutantDetectorImp sqlMutantDetector;

    @Mock
    private IMutantRepository mutantRepository;

    @Test
    public void givenValidSequence_thenReturnMutantEntity() {

        Mockito.doReturn(Any.getMutant()).when(mutantRepository).save(Mockito.any(Mutant.class));

        Mutant mutant = sqlMutantDetector.saveSequence(Any.getGenericDnaSequence(), Mockito.anyBoolean());

        Assertions.assertEquals(1L, mutant.getId());
        Assertions.assertEquals(Arrays.toString(Any.getGenericDnaSequence()), mutant.getSequence());
        Assertions.assertTrue(mutant.getIsMutant());

    }

    @Test
    public void whenCallGetStatistic_thenReturnStatisticResponse() {

        Mockito.doReturn(Any.getMutantsArray()).when(mutantRepository).findAll();

        StatisticResponse statistic = sqlMutantDetector.getStatistics();

        Assertions.assertEquals(0, statistic.getCountHumanDna());
        Assertions.assertEquals(3, statistic.getCountMutantDna());
        Assertions.assertEquals(1, statistic.getRatio());

    }

}
