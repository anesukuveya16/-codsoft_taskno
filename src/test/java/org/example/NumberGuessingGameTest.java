package org.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Random;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NumberGuessingGameTest {
  private static final int correctGuess = 6;
  private static final int incorrectGuess = 3;
  @Mock
  private Random randomMock;
  @Mock
  private Scanner scannerMock;
  @InjectMocks
  private NumberGuessingGame cut;

  @BeforeEach
  void setUp() {
    when(randomMock.nextInt(anyInt())).thenReturn(5);
  }

  @Test
  void shouldReturnHighestScoreIfFirstGuessIsCorrect() {
    // Given
    when(scannerMock.nextInt()).thenReturn(correctGuess);

    // When
    cut.start();

    // Then
    assertThat(cut.getScore()).isEqualTo(100);
  }
  @Test
  void scoreShouldBeZeroIfNumberOfTriesIsExhaustedWithoutMakingTheCorrectGuess() {

    // Given
    when(scannerMock.nextInt()).thenReturn(incorrectGuess);

    // When
    cut.start();

    // Then
    assertThat(cut.getScore()).isZero();
  }
  @Test
  @DisplayName("Get the correct guess on the third attempt")
  void shouldReturnTheCorrectScoreGivenMultipleTries(){
    // Given
    int yetAnotherIncorrectGuess = 5;
    when(scannerMock.nextInt()).thenReturn(incorrectGuess)
            .thenReturn(yetAnotherIncorrectGuess)
            .thenReturn(correctGuess);

    // When
    cut.start();

    // Then
    assertThat(cut.getScore()).isEqualTo(80);
  }
}
