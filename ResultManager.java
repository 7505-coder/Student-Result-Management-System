// ResultManager.java
import java.util.InputMismatchException;
import java.util.Scanner;

public class ResultManager {
    private Student[] students;
    private int studentCount;
    private Scanner scanner;

    public ResultManager(int capacity) {
        students = new Student[capacity];
        studentCount = 0;
        scanner = new Scanner(System.in);
    }

    public void mainMenu() {
        try {
            while (true) {
                System.out.println("\n===== Student Result Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. Show Student Details");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice;
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                } catch (InputMismatchException ime) {
                    scanner.nextLine();
                    System.out.println("Invalid input. Please enter a number for choice.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        try {
                            addStudent();
                        } catch (InvalidMarksException ime) {
                            System.out.println("Error: " + ime.getMessage());
                        } catch (Exception e) {
                            System.out.println("An unexpected error occurred while adding student: " + e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            showStudentDetails();
                        } catch (Exception e) {
                            System.out.println("Error while showing details: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Exiting program. Thank you!");
                        return;
                    default:
                        System.out.println("Please choose 1, 2 or 3.");
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            System.out.println("Scanner closed. Program terminated.");
        }
    }

    public void addStudent() throws InvalidMarksException {
        if (studentCount >= students.length) {
            System.out.println("Student storage full. Cannot add more students.");
            return;
        }

        try {
            System.out.print("Enter Roll Number: ");
            int roll = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();

            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marks[i] = scanner.nextInt();
            }
            scanner.nextLine();

            Student s = new Student(roll, name, marks);
            students[studentCount++] = s;
            System.out.println("Student added successfully. Returning to main menu...");
        } catch (InputMismatchException ime) {
            scanner.nextLine();
            System.out.println("Input type mismatch. Please enter integers for roll and marks.");
        }
    }

    public void showStudentDetails() {
        System.out.print("Enter Roll Number to search: ");
        try {
            int roll = scanner.nextInt();
            scanner.nextLine();
            Student found = null;
            for (int i = 0; i < studentCount; i++) {
                if (students[i] != null && students[i].getRollNumber() == roll) {
                    found = students[i];
                    break;
                }
            }
            if (found != null) {
                found.displayResult();
                System.out.println("Search completed.");
            } else {
                System.out.println("Student with roll number " + roll + " not found.");
            }
        } catch (InputMismatchException ime) {
            scanner.nextLine();
            System.out.println("Please enter a valid integer for roll number.");
        }
    }

    public static void main(String[] args) {
        ResultManager manager = new ResultManager(100);
        manager.mainMenu();
    }
}
