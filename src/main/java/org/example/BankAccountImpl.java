package org.example;

import lombok.Setter;

public class BankAccountImpl implements BankAccount {

  private int balance;
  private final int accountPin;
  private boolean isAccountPinBlocked;

  public BankAccountImpl(int openingBalance, int accountPin) {
    this.balance = openingBalance;
    this.accountPin = accountPin;
  }

  @Override
  public int getAccountBalance() {
    return balance;
  }

  @Override
  public void makeWithdrawal(int amountToBeWithdrawn) {
    if (amountToBeWithdrawn > 0 && amountToBeWithdrawn <= balance) {
      balance -= amountToBeWithdrawn;
      System.out.println("You have withdrawn " + amountToBeWithdrawn);
    } else {
      System.out.println("Insufficient funds.");
    }
  }

  @Override
  public void makeDeposit(int amountToDeposit) {
    if (amountToDeposit > 0) {
      balance += amountToDeposit;
      System.out.println("You have deposited " + amountToDeposit + " in your account.");
    } else {
      System.out.println("Deposit amount must be positive.");
    }
  }

  @Override
  public boolean isAccountPinBlocked() {
    return isAccountPinBlocked;
  }

  @Override
  public int getAccountPin() {
    return accountPin;
  }

  @Override
  public void blockAccountPin() {
    isAccountPinBlocked = true;
  }

}

