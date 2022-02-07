package com.mutant.mutant_detector.util;

import com.mutant.mutant_detector.dto.StatisticResponse;
import com.mutant.mutant_detector.dto.ValidationRq;
import com.mutant.mutant_detector.persistence.entity.Mutant;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Any {

    public static String[] getGenericDnaSequence() {

        return new String[]{"ATGCAA", "CAGTGC", "TTTTGT", "AGAAGG", "CCCCTA", "TCACTG"};
    }

    public static StatisticResponse getStatisticResponse() {
        return StatisticResponse.builder().countHumanDna(10).countMutantDna(10).ratio(0.5).build();
    }

    public static Mutant getMutant() {
        return Mutant.builder().isMutant(true).
                creationDatetime(new Date()).sequence(Arrays.toString(getGenericDnaSequence())).id(1L).build();
    }

    public static List<Mutant> getMutantsArray(){

        return Arrays.asList(getMutant(),getMutant(),getMutant());
    }

    public static ValidationRq getValidationRq(){
        ValidationRq request = new ValidationRq();
        request.setDna(getGenericDnaSequence());
        return request;
    }

}
