package com.example.testProject.service;

import com.example.testProject.domain.CreditDecision;
import com.example.testProject.domain.CreditDecisionMaker;
import com.example.testProject.repository.CustomerDebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreditDecisionService {

    private final CustomerDebtRepository customerDebtRepository;
    private final CreditDecisionMaker creditDecisionMaker;


    @Autowired
    public CreditDecisionService(CustomerDebtRepository customerDebtRepository, CreditDecisionMaker creditDecisionMaker) {
        this.customerDebtRepository = customerDebtRepository;
        this.creditDecisionMaker = creditDecisionMaker;
    }


    /**
     * Handles the credit decision process.
     *
     * @param customerDebt The credit request with the amount and the customer's details
     * @return The decision
     */
    public CreditDecision handleCreditRequest(CustomerDebt customerDebt) {

        int currentDebt = customerDebtRepository.fetchCustomerDebtForEmail(customerDebt.email());

        CreditDecision creditDecision = creditDecisionMaker.makeCreditDecision(customerDebt.purchaseAmount(), currentDebt);

        if (creditDecision.isAccepted()) {
            customerDebtRepository.addCustomerDebt(customerDebt);
        }

        return creditDecision;
    }

}
