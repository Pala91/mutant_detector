package com.mutant.mutant_detector.controller;

import com.mutant.mutant_detector.controler.findMutantController;
import com.mutant.mutant_detector.delegate.IValidateMutantDelegate;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class findMutantControllerTest {

    @InjectMocks
    private findMutantController mutantController;

    @Mock
    private IValidateMutantDelegate mutantDelegate;

    @Mock
    private ValidateMutantServiceImp mutantService;

    @Test
    public void givenDNAMutantSequence_thenReturnHttpStatusOk() {

        Mockito.doNothing().when(mutantDelegate).isMutant(Any.getGenericDnaSequence());
        ResponseEntity response = mutantController.validate(Any.getValidationRq());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void whenCallStatistic_thenReturnStatisticResponse() {

        Mockito.doReturn(Any.getStatisticResponse()).when(mutantDelegate).statistics();
        ResponseEntity<StatisticResponse> response = mutantController.getStats();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(10, response.getBody().getCountHumanDna());
        Assertions.assertEquals(10, response.getBody().getCountMutantDna());
        Assertions.assertEquals(0.5, response.getBody().getRatio());

    }

}
