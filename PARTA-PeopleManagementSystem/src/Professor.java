/**
 * MAD204 - Assignment 1 Part A
 * Author: Darshilkumar Karkar (A00203357)
 * Date: 5th October 2025
 * Description: Professor subclass.
 */

public class Professor extends Person implements Evaluable {
    private String department;
    private String title;
    private int coursesTaught; // used for performance

    public Professor(int id, String name, int age, String department, String title, int coursesTaught) {
        super(id, name, age);
        this.department = department;
        this.title = title;
        this.coursesTaught = coursesTaught;
    }

    @Override
    public String introduce() {
        return String.format("Hello, I'm %s, %s of %s department. I teach %d course(s).", name, title, department, coursesTaught);
    }

    @Override
    public String evaluatePerformance() {
        if (coursesTaught >= 4) return "Excellent";
        if (coursesTaught >= 2) return "Good";
        if (coursesTaught == 1) return "Average";
        return "Needs Improvement";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" department=%s, title=%s, coursesTaught=%d", department, title, coursesTaught);
    }

    // getters & setters
    public String getDepartment() { return department; }
    public String getTitle() { return title; }
    public int getCoursesTaught() { return coursesTaught; }
    public void setCoursesTaught(int val) { this.coursesTaught = val; }
}
