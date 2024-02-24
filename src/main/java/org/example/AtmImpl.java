package org.example;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class AtmImpl implements Atm {
  private static final int MAXIMUM_NUMBER_OF_PIN_ATTEMPTS = 3;
  private static final int MAXIMUM_NUMBER_OF_ATM_OPERATION_CHOICE_ATTEMPTS = 3;

  private final BankAccount bankAccount;
  private final Scanner scanner;

  @Override
  public void beginAtmOperations() {
    try(scanner) {
      System.out.println("Please insert bank card to start.");
      System.out.println("Enter 4 digit PIN:");
      int givenPin = scanner.nextInt();
      int numberOfPinAttempts = 1;

      while (givenPin != bankAccount.getAccountPin() && numberOfPinAttempts < MAXIMUM_NUMBER_OF_PIN_ATTEMPTS) {
        System.out.println("Incorrect PIN. Please try again.");
        System.out.println(
            "Number of pin attempts left: "
                + (MAXIMUM_NUMBER_OF_PIN_ATTEMPTS - numberOfPinAttempts));
        givenPin = scanner.nextInt();
        numberOfPinAttempts++;
      }

      if (givenPin != bankAccount.getAccountPin()) {
        System.out.println("Account blocked. Too many incorrect pin attempts");
        bankAccount.blockAccountPin();
        return;
      }

      System.out.println("Select an ATM operation you would like to perform.");
      displayAtmOperations();

      int atmOperationChoiceAttempts = 0;

      while (!scanner.hasNextInt()
          && atmOperationChoiceAttempts < MAXIMUM_NUMBER_OF_ATM_OPERATION_CHOICE_ATTEMPTS) {
        System.out.println("Only numeric options between 1-4 are allowed. Try again.");
        atmOperationChoiceAttempts++;
      }

      if (!scanner.hasNextInt()) {
        System.out.println(
            "Too many consecutive invalid ATM operations. Please remove your card and try again later.");
        return;
      }

      int atmOperation = scanner.nextInt();
      atmOperationChoiceAttempts = 0;

      while (isInValidAtmOperation(atmOperation)
          && atmOperationChoiceAttempts < MAXIMUM_NUMBER_OF_ATM_OPERATION_CHOICE_ATTEMPTS) {
        System.out.println("Invalid ATM operation. Try again.");
        atmOperation = scanner.nextInt();
        atmOperationChoiceAttempts++;
      }

      if (isInValidAtmOperation(atmOperation)) {
        System.out.println(
            "Too many consecutive invalid ATM operations. Please remove your card and try again later.");
        return;
      }

      switch (atmOperation) {
        case 1:
          int accountBalance = bankAccount.getAccountBalance();
          System.out.println("Your current balance is " + accountBalance);
          break;
        case 2:
          makeWithdrawal(scanner);
          break;
        case 3:
          makeDeposit(scanner);
          break;
        case 4:
          System.out.println("Transaction is complete. Please remove your card.");
      }

    } catch (Exception e) {
      System.out.println("An error occurred: " + e.getMessage());
    }
  }

  @Override
  public void displayAtmOperations() {
    System.out.println("Option 1: Check Balance.");
    System.out.println("Option 2: Withdraw");
    System.out.println("Option 3: Deposit");
    System.out.println("Option 4: Exit");
  }

  private static boolean isInValidAtmOperation(int atmOperation) {
    return atmOperation != 1 && atmOperation != 2 && atmOperation != 3 && atmOperation != 4;
  }
    private void makeWithdrawal(Scanner scanner) {
        System.out.println("Enter amount for withdrawal:");
        int amount = scanner.nextInt();
        bankAccount.makeWithdrawal(amount);
    }

    private void makeDeposit(Scanner scanner) {
        System.out.println("Enter amount for deposit:");
        int amount = scanner.nextInt();
        bankAccount.makeDeposit(amount);
    }
}
