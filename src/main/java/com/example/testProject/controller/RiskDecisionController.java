package com.example.testProject.controller;


import com.example.testProject.service.CreditDecisionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RiskDecisionController {

    private final CreditDecisionService creditDecisionService;


    public RiskDecisionController(CreditDecisionService creditDecisionService) {
        this.creditDecisionService = creditDecisionService;
    }

    @PostMapping(value = "/api/decision", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditDecisionResponse> decision(@RequestBody CustomerDebt customerDebt) {
        return ResponseEntity.ok(creditDecisionService.handleCreditRequest(customerDebt));
    }

}

