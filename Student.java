// Student.java
public class Student {
    private int rollNumber;
    private String studentName;
    private int[] marks; // length 3
    public static final int PASS_MARK = 40;

    public Student(int rollNumber, String studentName, int[] marks) throws InvalidMarksException {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        if (marks == null || marks.length != 3) {
            throw new InvalidMarksException("Marks array is missing or does not contain 3 subjects.");
        }
        this.marks = marks.clone();
        validateMarks();
    }

    public void validateMarks() throws InvalidMarksException {
        for (int i = 0; i < marks.length; i++) {
            int m = marks[i];
            if (m < 0 || m > 100) {
                throw new InvalidMarksException("Invalid marks for subject " + (i+1) + ": " + m + " (must be 0-100)");
            }
        }
    }

    public double calculateAverage() {
        double sum = 0;
        for (int m : marks) sum += m;
        return sum / marks.length;
    }

    public boolean isPass() {
        for (int m : marks) {
            if (m < PASS_MARK) return false;
        }
        return true;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void displayResult() {
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Student Name: " + studentName);
        System.out.print("Marks: ");
        for (int m : marks) System.out.print(m + " ");
        System.out.println();
        System.out.println("Average: " + calculateAverage());
        System.out.println("Result: " + (isPass() ? "Pass" : "Fail"));
    }
}
