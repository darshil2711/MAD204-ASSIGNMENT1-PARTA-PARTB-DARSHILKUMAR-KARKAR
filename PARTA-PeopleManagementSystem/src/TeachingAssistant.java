/**
 * MAD204 - Assignment 1 Part A
 * Author: Darshilkumar Karkar (A00203357)
 * Date: 5th October 2025
 * Description: TeachingAssistant subclass extends Student.
 */

public class TeachingAssistant extends Student implements Evaluable {
    private String assignedCourse;

    public TeachingAssistant(int id, String name, int age, String program, int year, double gpa, String assignedCourse) {
        super(id, name, age, program, year, gpa);
        this.assignedCourse = assignedCourse;
    }

    public TeachingAssistant(int id, String name, int age, String program, String assignedCourse) {
        super(id, name, age, program);
        this.assignedCourse = assignedCourse;
    }

    @Override
    public String introduce() {
        return String.format("Hi, I'm %s, a TA for %s. I'm a %s-year student with GPA %.2f.", getName(), assignedCourse, getYear(), getGpa());
    }

    @Override
    public String evaluatePerformance() {
        // mix of GPA and TA duties (we'll simplify: numeric score -> descriptor)
        double gpa = getGpa();
        double score = gpa * 0.7 + 0.3; // small boost for duties
        if (score >= 3.5) return "Excellent TA";
        if (score >= 2.8) return "Good TA";
        if (score >= 2.0) return "Average TA";
        return "Needs Improvement";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" assignedCourse=%s", assignedCourse);
    }

    public String getAssignedCourse() { return assignedCourse; }
}
