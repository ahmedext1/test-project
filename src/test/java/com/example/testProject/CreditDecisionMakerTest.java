package com.example.testProject;

import com.example.testProject.domain.CreditDecision;
import com.example.testProject.domain.CreditDecisionMaker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreditDecisionMakerTest {

    private CreditDecisionMaker creditDecisionMaker;


    @BeforeEach
    public void before() {
        creditDecisionMaker = new CreditDecisionMakerImpl();
    }


    @AfterEach
    public void after() {
        creditDecisionMaker = null;
    }


    @Test
    public void shouldThrowIllegalArgumentExceptionOnNegativePurchaseAmount() {
        assertThrows(IllegalArgumentException.class, () -> creditDecisionMaker.makeCreditDecision(-42, 0));
    }


    @Test
    public void shouldAcceptCreditRequestsUpToPurchaseAmountLimit() {
        CreditDecision creditDecision = creditDecisionMaker.makeCreditDecision(10, 0);
        assertThat(creditDecision.isAccepted()).isTrue();
        assertThat(creditDecision.getReason()).isEqualTo("ok");
    }

    @Test
    public void shouldRejectCreditRequestsExceedsAmountLimit() {
        CreditDecision creditDecision = creditDecisionMaker.makeCreditDecision(11, 0);
        assertThat(creditDecision.isAccepted()).isFalse();
        assertThat(creditDecision.getReason()).isEqualTo("amount");
    }


    @Test
    public void shouldAcceptCreditRequestsUpToCustomerDebtLimit() {
        CreditDecision creditDecision = creditDecisionMaker.makeCreditDecision(1, 99);
        assertThat(creditDecision.isAccepted()).isTrue();
        assertThat(creditDecision.getReason()).isEqualTo("ok");
    }


    @Test
    public void shouldNotAcceptPurchasesThatWouldResultInExceedingCustomerDebtLimit() {
        CreditDecision creditDecision = creditDecisionMaker.makeCreditDecision(1, 100);
        assertThat(creditDecision.isAccepted()).isFalse();
        assertThat(creditDecision.getReason()).isEqualTo("debt");
    }
}
