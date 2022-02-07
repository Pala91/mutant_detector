package com.mutant.mutant_detector.controller.advisor;

import com.mutant.mutant_detector.controler.advisor.ExceptionAdvice;
import com.mutant.mutant_detector.controler.findMutantController;
import com.mutant.mutant_detector.delegate.IValidateMutantDelegate;
import com.mutant.mutant_detector.dto.StatisticResponse;
import com.mutant.mutant_detector.exception.NoMutantException;
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
public class ExceptionAdviceTest {

    @InjectMocks
    private ExceptionAdvice exceptionAdvice;

    @Test
    public void givenNoMutantException_thenReturnHttpStatusForbidden() {

        ResponseEntity response = exceptionAdvice.handleNoMutantException(new NoMutantException());

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

}
