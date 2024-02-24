package org.example;

import java.util.Random;
import java.util.Scanner;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class NumberGuessingGame {

  private static final int MINIMUM_GUESS_VALUE = 1;
  private static final int MAXIMUM_GUESS_VALUE = 100;
  private static final int MAXIMUM_SCORE = 100;
  private static final int MAXIMUM_ATTEMPTS_ALLOWED = 5;
  private int score = 0;
  private final Scanner scanner;
  private final Random randomNumberGenerator;

  void start(){
    int secretNumber = randomNumberGenerator.nextInt(MAXIMUM_GUESS_VALUE - MINIMUM_GUESS_VALUE) + 1;
    displayWelcomeMessage();
    try (scanner) {
      playGame(secretNumber, scanner);
  }

  }
  private void playGame(int secretNumber, Scanner scanner) {
    int attempts = 0;
    while (attempts < MAXIMUM_ATTEMPTS_ALLOWED) {
      System.out.println("Enter your guess: ");
      int guess = scanner.nextInt();
      attempts++;

      if (guess < secretNumber) {
        System.out.println("Too low! Try again.");
      } else if (guess > secretNumber) {
        System.out.println("Too high! Try again.");
      } else {
        displayScore(attempts);
        return;
      }
  }

    System.out.println("Game over...you failed to guess the number correctly.");
  }

  private void displayScore(int attempts) {
    displayCongratulationsMessage(attempts);
    calculateAndDisplayScore(attempts);
  }

  private void displayWelcomeMessage() {
    System.out.println("Welcome to the Number Guessing Game!");
    System.out.println("Try to guess the number between 1 and 100.");
  }

  private void displayCongratulationsMessage(int attempts) {
    System.out.println("Congratulations! You've guessed the correct number.");
    System.out.println("You guessed the number in " + attempts + " attempts.");
  }

  private void calculateAndDisplayScore(int attempts) {
    score = MAXIMUM_SCORE -(attempts-1)*10;
    if (score<0) {
      score=0;
    }
    System.out.println("Your score: " + score);
  }


}


