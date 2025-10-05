
/**
 * MAD204 - Assignment 1 Part A
 * Author: Darshilkumar Karkar (A00203357)
 * Date: 5th October 2025
 * Description: Student subclass.
 */

public class Student extends Person implements Evaluable {
    private String program;
    private int year;
    private double gpa;

    // Constructor
    public Student(int id, String name, int age, String program, int year, double gpa) {
        super(id, name, age);
        this.program = program;
        this.year = year;
        this.gpa = gpa;
    }

    // Overloaded constructor (default year 1, gpa 0.0)
    public Student(int id, String name, int age, String program) {
        this(id, name, age, program, 1, 0.0);
    }

    @Override
    public String introduce() {
        return String.format("Hi, I'm %s, a year %d student in %s (GPA: %.2f).", name, year, program, gpa);
    }

    @Override
    public String evaluatePerformance() {
        // Convert GPA to letter grade
        if (gpa >= 3.7) return "A";
        if (gpa >= 3.0) return "B";
        if (gpa >= 2.0) return "C";
        if (gpa >= 1.0) return "D";
        return "F";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" program=%s, year=%d, gpa=%.2f", program, year, gpa);
    }

    // getters & setters
    public String getProgram() { return program; }
    public int getYear() { return year; }
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
}
