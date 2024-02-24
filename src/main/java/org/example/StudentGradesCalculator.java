package org.example;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class StudentGradesCalculator {
    private static final double MINIMUM_GRADE_FOR_A = 90D;
    private static final double MINIMUM_GRADE_FOR_B = 80D;
    private static final double MINIMUM_GRADE_FOR_C = 70D;
    private static final double MINIMUM_GRADE_FOR_D = 60D;
    private static final double MINIMUM_GRADE_FOR_E = 50D;
    private static final int MIN_GRADE = 0;
    private static final int MAX_GRADE = 100;

    private final Scanner scanner;
    public Grade getStudentFinalGrade() {
        StudentMarks studentMarks = calculateStudentMarks();
        String gradeCategory = getStudentGradeCategory(studentMarks.averagePercentage());
        return new Grade(gradeCategory, studentMarks);
    }

    private String getStudentGradeCategory(double averageGrade) {
        if (averageGrade >= MINIMUM_GRADE_FOR_A && averageGrade <= MAX_GRADE) {
            return "A";
        } else if (averageGrade >= MINIMUM_GRADE_FOR_B) {
            return "B";
        } else if (averageGrade >= MINIMUM_GRADE_FOR_C) {
            return "C";
        } else if (averageGrade >= MINIMUM_GRADE_FOR_D) {
            return "D";
        } else if (averageGrade >= MINIMUM_GRADE_FOR_E) {
            return "E";
        } else {
            return "FAIL";
        }
    }

    private StudentMarks calculateStudentMarks() {
        double totalMarks = 0;
        int numberOfGradesEntered = 0;
        boolean shouldEnterGrades = true;
        while (shouldEnterGrades) {
            double gradeInput = -1;
            while (isInvalidGrade(gradeInput)) {
                gradeInput = getGradeInput(numberOfGradesEntered);
            }
            totalMarks += gradeInput;
            numberOfGradesEntered++;
            shouldEnterGrades = determineIfUserWantsToEnterMoreGrades();
        }
        double averagePercentage = numberOfGradesEntered > 0 ? totalMarks / numberOfGradesEntered : 0;

        return new StudentMarks(totalMarks, averagePercentage);
    }

    private boolean determineIfUserWantsToEnterMoreGrades() {
        System.out.println("Do you want to enter another grade? (yes/no)");
        return scanner.next().equalsIgnoreCase("yes");
    }

    private double getGradeInput(int subject) {
        double gradeInput = -1;
        System.out.println("Enter your grade for subject " + (subject + 1) + ": ");
        if (scanner.hasNextDouble()) {
            gradeInput = scanner.nextDouble();
            if (isInvalidGrade(gradeInput)) {
                System.out.println("Invalid input! Please enter a valid grade BETWEEN " + MIN_GRADE + " and " + MAX_GRADE + ".");
            }
        } else {
            System.out.println("Invalid input! Please enter a valid NUMERIC grade BETWEEN " + MIN_GRADE + " and " + MAX_GRADE + ".");
            scanner.next(); // consume invalid input
        }
        return gradeInput;
    }

    private boolean isInvalidGrade(double gradeInput) {
        return gradeInput < MIN_GRADE || gradeInput > MAX_GRADE;
    }
}


record Grade(String gradeCategory, StudentMarks marksComponent) {}

record StudentMarks(double totalMarks, double averagePercentage) {}
