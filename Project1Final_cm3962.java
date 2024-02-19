/*
 * Project1.java
 * Drexel GPA Calculator
 * @author Caleb Miller
 * @date 02/08/2024
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Project1Final_cm3962 {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in); // create for user input
        ArrayList<Double> grades = new ArrayList<>();  // create to store grades
        ArrayList<Integer> credits = new ArrayList<>(); // create to store credit hours

        System.out.println("Welcome to the Drexel GPA Calculator Tool");

        while (true) {    // continuously loop 
            System.out.println("Please enter your final letter grade (i.e. A+, A, A-, B+, B, etc.).");
            System.out.println("Enter 'done' to end the program.");
            String gradeInput = myScanner.nextLine(); // collect grade input

            while (!gradeInput.matches("[A-D][+-]?|[a-d][+-]?|F|f|done|INC|AC|W|NCR|CR")) { // if user input is not valid, collect new grade input
                System.out.println("Invalid input. Please enter your final letter (i.e. A+, A, A-, B+, B, etc.) or 'done' to end the program.");
                gradeInput = myScanner.nextLine();
            }

            if (gradeInput.equalsIgnoreCase("done")) { // if user inputs 'done', break out of the loop
                break;
            }

            System.out.println("Please enter the credit hours associated with the class.");

            while (!myScanner.hasNextInt()) { // if user input is not valid, collect new credit hour input
                System.out.println("Invalid input. Please enter the credit hours associated with the class.");
                myScanner.nextLine();
            }

            int creditHours = myScanner.nextInt(); // collect credit hour input
            myScanner.nextLine();

            double gradePoints = convertGradeToPoint(gradeInput); // convert inputted grade to GPA points
            if (gradePoints != -1){ // if the grade is valid, add to grades and credits ArrayLists
                grades.add(gradePoints);
                credits.add(creditHours);
            } else { // if the grade is invalid, tell the user 
                System.out.println("Invalid grade. Please try again.");
            }
        }

        myScanner.close(); // close scanner as we are done with user input

        double gpa = calculateGPA(grades, credits); // calculate GPA
        System.out.printf("Your Cumulative GPA is: %.2f\n", gpa);
    }

    private static double convertGradeToPoint(String grade) { // helper function to convert inputted grade to GPA points
        switch (grade.toUpperCase()) { // make inputted grade uppercase to use switch statement
            case "A+": return 4.0;
            case "A": return 4.0;
            case "A-": return 3.67;
            case "B+": return 3.33;
            case "B": return 3.0;
            case "B-": return 2.67;
            case "C+": return 2.33;
            case "C": return 2.0;
            case "C-": return 1.67;
            case "D+": return 1.33;
            case "D": return 1.0;
            case "F", "AC", "INC", "W", "CR", "NCR": return 0.0;

            default: return -1; //invalid grade
        }
    }

    private static double calculateGPA(ArrayList<Double> grades, ArrayList<Integer> credits) { // helper function to calculate GPA
        double totalPoints = 0.0;
        int totalCredits = 0;

        for (int i = 0; i < grades.size(); i++){ // go through all the grades and credits and perform calculation to get GPA
            totalPoints += grades.get(i) * credits.get(i);
            totalCredits += credits.get(i);
        }

        return totalCredits == 0 ? 0 : totalPoints / totalCredits; // if totalCredits is 0, return GPA of 0. else, return totalPoints / totalCredits
    }
}