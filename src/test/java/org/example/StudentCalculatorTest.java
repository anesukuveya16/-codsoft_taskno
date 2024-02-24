package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentGradesCalculatorTest {

    @Mock
    private Scanner scannerMock;

    @InjectMocks
    private StudentGradesCalculator calculator;

    @Test
    void shouldReturnCorrectGradeForValidInput() {
        // Given
        // Mock grade input
        when(scannerMock.hasNextDouble()).thenReturn(true);
        when(scannerMock.nextDouble())
                .thenReturn(90.0)
                .thenReturn(80.0)
                .thenReturn(70.0)
                .thenReturn(60.0)
                .thenReturn(50.0);

        // Mock the behavior of prompting the user to enter another grade
        when(scannerMock.next())
                .thenReturn("yes")
                .thenReturn("yes")
                .thenReturn("yes")
                .thenReturn("yes")
                .thenReturn("no");

        // When
        Grade finalGrade = calculator.getStudentFinalGrade();

        // Then
        assertThat(finalGrade.gradeCategory()).isEqualTo("C");
    }

    @Test
    void shouldHandleInvalidInputWithRetry() {
        // Given
        // mock grade input
        when(scannerMock.hasNextDouble())
                .thenReturn(false) // invalid string input
                .thenReturn(true);

        when(scannerMock.nextDouble())
                .thenReturn(-5.0)   // Invalid input numeric input
                .thenReturn(75.0)
                .thenReturn(80.0)
                .thenReturn(91.7);

        when(scannerMock.next())
                .thenReturn("yes")
                .thenReturn("yes")
                .thenReturn("yes")
                .thenReturn("no");
        // When
        Grade finalGrade = calculator.getStudentFinalGrade();

        // Then
        assertThat(finalGrade.gradeCategory()).isEqualTo("B");
    }

}

