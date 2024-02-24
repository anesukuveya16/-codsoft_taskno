package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private int openingBalance = 200;
    private final int accountPin= 1234;
    BankAccount bankAccount = new BankAccountImpl(openingBalance, accountPin);

    @Test
    void makeWithdrawalShouldDeductMoneyFromAccountBalanceGivenSufficientFunds (){
        bankAccount.makeWithdrawal(199);
        assertThat(bankAccount.getAccountBalance()).isEqualTo(1);
    }

    @Test
    void makeDepositShouldAddMoneyToTheAccountBalance () {
        bankAccount.makeDeposit(500);
        assertThat(bankAccount.getAccountBalance()).isEqualTo(700);
    }

}

