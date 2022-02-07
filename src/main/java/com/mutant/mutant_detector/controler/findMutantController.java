package com.mutant.mutant_detector.controler;

import com.mutant.mutant_detector.constants.Endpoint;
import com.mutant.mutant_detector.delegate.IValidateMutantDelegate;
import com.mutant.mutant_detector.dto.StatisticResponse;
import com.mutant.mutant_detector.dto.ValidationRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class findMutantController {

    private final IValidateMutantDelegate mutantDelegate;

    @Autowired
    public findMutantController(IValidateMutantDelegate mutantDelegate) {
        this.mutantDelegate = mutantDelegate;
    }

    @PostMapping(Endpoint.IS_MUTANT)
    public ResponseEntity validate(@RequestBody ValidationRq request) {

        mutantDelegate.isMutant(request.getDna());
        return ResponseEntity.ok().build();
    }

    @GetMapping(Endpoint.STATS)
    public ResponseEntity<StatisticResponse> getStats() {
        return ResponseEntity.ok(mutantDelegate.statistics());
    }

}
