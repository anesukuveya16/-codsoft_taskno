package org.example;

public interface BankAccount {
  // check balance
  int getAccountBalance();

  // withdraw
  void makeWithdrawal(int amountToBeWithdrawn);

  // make a deposit
  void makeDeposit(int amountToDeposit);

  boolean isAccountPinBlocked ();
  int getAccountPin();

  void blockAccountPin();
}
