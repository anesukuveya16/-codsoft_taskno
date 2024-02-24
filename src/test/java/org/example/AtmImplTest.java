package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtmImplTest {
  private final int inCorretPin = 5678;
  private final int correctPin = 1234;
  @Mock private  BankAccountImpl bankAccountMock;
  @Mock private Scanner scannerMock;
  private Atm atmImpl = new AtmImpl(bankAccountMock, scannerMock);

  @BeforeEach
  void setUp(){
    atmImpl = new AtmImpl(bankAccountMock, scannerMock);
  }
  @Test
  void shouldBlockAccountPinIfTooManyIncorrectPinAttempts () {
    when(scannerMock.nextInt()).thenReturn(inCorretPin);
    when(bankAccountMock.getAccountPin()).thenReturn(correctPin);
    doCallRealMethod().when(bankAccountMock).blockAccountPin();
    atmImpl.beginAtmOperations();
    verify(bankAccountMock).blockAccountPin();
    assertTrue(bankAccountMock.isAccountPinBlocked());
  }

  @Test
  void  shouldExecuteOperationsWhenTwoWrongPinAttemptsThenTheCorrectOneOnTheThirdAttempt(){
    int operationChoiceOne = 1;
    int incorrectPinTwo = 9899;
    when(scannerMock.nextInt()).thenReturn(inCorretPin).thenReturn(incorrectPinTwo).thenReturn(correctPin).thenReturn(operationChoiceOne);
    when(scannerMock.hasNextInt()).thenReturn(true);
    when(bankAccountMock.getAccountPin()).thenReturn(correctPin);
    atmImpl.beginAtmOperations();
    verify(bankAccountMock, times(4)).getAccountPin();
    verify(bankAccountMock, never()).blockAccountPin();
    verify(bankAccountMock).getAccountBalance();
    verifyNoMoreInteractions(bankAccountMock);
  }

  @Test
  void shouldNotPerformAccountOperationsIfUserGivesNonNumericInput(){
    when(scannerMock.nextInt()).thenReturn(correctPin);
    when(bankAccountMock.getAccountPin()).thenReturn(correctPin);
    when(scannerMock.hasNextInt()).thenReturn(false);
    atmImpl.beginAtmOperations();
    verify(bankAccountMock, times(2)).getAccountPin();
    verifyNoMoreInteractions(bankAccountMock);
  }



}