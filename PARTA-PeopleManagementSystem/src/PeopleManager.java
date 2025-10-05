/**
 * MAD204 - Assignment 1 Part A
 * Author: Darshilkumar Karkar (A00203357)
 *  * Date: 5th October 2025
 * Description: Console menu-driven People Management System.
 */

import java.io.*;
import java.util.*;

public class PeopleManager {
    private static final String DATA_FILE = "people.txt";
    private ArrayList<Person> people;
    private Scanner scanner;

    public PeopleManager() {
        people = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadFromFile();
    }

    public static void main(String[] args) {
        PeopleManager app = new PeopleManager();
        app.runMenu();
    }

    private void runMenu() {
        while (true) {
            System.out.println("\n=== People Management System ===");
            System.out.println("1. Add Person");
            System.out.println("2. List People");
            System.out.println("3. Search Person by ID");
            System.out.println("4. Search Person by Name");
            System.out.println("5. Remove Person");
            System.out.println("6. Celebrate Birthday");
            System.out.println("7. Show Performance Evaluation");
            System.out.println("8. Run Countdown (Recursion Demo)");
            System.out.println("9. Save & Exit");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid choice. Please enter a number 1-9.");
                continue;
            }

            try {
                switch (choice) {
                    case 1: addPerson(); break;
                    case 2: listPeople(); break;
                    case 3: searchByIdMenu(); break;
                    case 4: searchByNameMenu(); break;
                    case 5: removeByIdMenu(); break;
                    case 6: celebrateBirthdayMenu(); break;
                    case 7: showEvaluations(); break;
                    case 8: countdownMenu(); break;
                    case 9: saveToFile(); System.out.println("Saved. Exiting."); return;
                    default: System.out.println("Invalid selection. Choose 1-9."); break;
                }
            } catch (Exception ex) {
                System.out.println("An error occurred: " + ex.getMessage());
            }
        }
    }

    // Add person
    private void addPerson() {
        System.out.println("Choose type: 1.Student 2.Professor 3.TeachingAssistant");
        String typeStr = scanner.nextLine();
        int type;
        try {
            type = Integer.parseInt(typeStr);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid type.");
            return;
        }

        try {
            System.out.print("ID (integer): ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            if (age <= 0) throw new IllegalArgumentException("Age must be > 0");

            if (type == 1) {
                System.out.print("Program: ");
                String program = scanner.nextLine();
                System.out.print("Year (int): ");
                int year = Integer.parseInt(scanner.nextLine());
                System.out.print("GPA (0.0-4.0): ");
                double gpa = Double.parseDouble(scanner.nextLine());
                validateGpa(gpa);
                Student s = new Student(id, name, age, program, year, gpa);
                people.add(s);
                System.out.println("Student added.");
            } else if (type == 2) {
                System.out.print("Department: ");
                String dept = scanner.nextLine();
                System.out.print("Title: ");
                String title = scanner.nextLine();
                System.out.print("Courses taught (int): ");
                int ct = Integer.parseInt(scanner.nextLine());
                Professor p = new Professor(id, name, age, dept, title, ct);
                people.add(p);
                System.out.println("Professor added.");
            } else if (type == 3) {
                System.out.print("Program: ");
                String program = scanner.nextLine();
                System.out.print("Year (int): ");
                int year = Integer.parseInt(scanner.nextLine());
                System.out.print("GPA (0.0-4.0): ");
                double gpa = Double.parseDouble(scanner.nextLine());
                validateGpa(gpa);
                System.out.print("Assigned course: ");
                String course = scanner.nextLine();
                TeachingAssistant ta = new TeachingAssistant(id, name, age, program, year, gpa, course);
                people.add(ta);
                System.out.println("TA added.");
            } else {
                System.out.println("Unknown type.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number input. " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Validation error: " + ex.getMessage());
        }
    }

    private void validateGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
    }

    private void listPeople() {
        if (people.isEmpty()) {
            System.out.println("No people available.");
            return;
        }
        for (Person p : people) {
            System.out.println(p.toString());
            System.out.println("  Intro: " + p.introduce());
            if (p instanceof Evaluable) {
                System.out.println("  Evaluation: " + ((Evaluable) p).evaluatePerformance());
            }
        }
    }

    // Overloaded search by ID
    private Person search(int id) {
        for (Person p : people) if (p.getId() == id) return p;
        return null;
    }

    // Overloaded search by name
    private List<Person> search(String name) {
        List<Person> results = new ArrayList<>();
        for (Person p : people) if (p.getName().equalsIgnoreCase(name)) results.add(p);
        return results;
    }

    private void searchByIdMenu() {
        System.out.print("Enter ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Person p = search(id);
            if (p == null) System.out.println("Not found.");
            else System.out.println(p.toString() + "\nIntro: " + p.introduce());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid ID.");
        }
    }

    private void searchByNameMenu() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        List<Person> found = search(name);
        if (found.isEmpty()) System.out.println("No matches.");
        else {
            for (Person p : found) System.out.println(p.toString() + "\n Intro: " + p.introduce());
        }
    }

    private void removeByIdMenu() {
        System.out.print("Enter ID to remove: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Person p = search(id);
            if (p == null) {
                System.out.println("No person with that ID.");
            } else {
                people.remove(p);
                System.out.println("Removed: " + p.getName());
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid ID.");
        }
    }

    private void celebrateBirthdayMenu() {
        System.out.print("Enter ID for birthday: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Person p = search(id);
            if (p == null) System.out.println("Person not found.");
            else {
                p.celebrateBirthday();
                System.out.println(p.getName() + " is now " + p.getAge());
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid ID.");
        }
    }

    private void showEvaluations() {
        for (Person p : people) {
            System.out.print(p.getName() + ": ");
            if (p instanceof Evaluable) System.out.println(((Evaluable) p).evaluatePerformance());
            else System.out.println("No evaluation available.");
        }
    }

    // Recursion demo: factorial and countdown
    private long factorial(int a) {
        if (a < 0) throw new IllegalArgumentException("a must be non-negative");
        if (a == 0) return 1;
        return a * factorial(a - 1);
    }

    private void countdown(int n) {
        if (n <= 0) {
            System.out.println("Blast off!");
            return;
        }
        System.out.println(n);
        countdown(n - 1);
    }

    private void countdownMenu() {
        System.out.print("Enter start number for countdown (int): ");
        try {
            int n = Integer.parseInt(scanner.nextLine());
            countdown(n);
            // demo factorial for a small number
            System.out.print("Enter a small number to calculate factorial: ");
            int f = Integer.parseInt(scanner.nextLine());
            System.out.println("Factorial(" + f + ") = " + factorial(f));
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    // File I/O: save/load simple CSV-like lines with type as first token.
    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Person p : people) {
                if (p instanceof TeachingAssistant) {
                    TeachingAssistant ta = (TeachingAssistant) p;
                    pw.printf("TA|%d|%s|%d|%s|%d|%.2f|%s%n",
                            ta.getId(), escape(ta.getName()), ta.getAge(),
                            escape(((Student) ta).getProgram()), ((Student) ta).getYear(),
                            ((Student) ta).getGpa(), escape(ta.getAssignedCourse()));
                } else if (p instanceof Student) {
                    Student s = (Student) p;
                    pw.printf("STUDENT|%d|%s|%d|%s|%d|%.2f%n",
                            s.getId(), escape(s.getName()), s.getAge(),
                            escape(s.getProgram()), s.getYear(), s.getGpa());
                } else if (p instanceof Professor) {
                    Professor pr = (Professor) p;
                    pw.printf("PROF|%d|%s|%d|%s|%s|%d%n",
                            pr.getId(), escape(pr.getName()), pr.getAge(),
                            escape(pr.getDepartment()), escape(pr.getTitle()), pr.getCoursesTaught());
                }
            }
            System.out.println("Data saved to " + DATA_FILE);
        } catch (IOException ex) {
            System.out.println("Error saving file: " + ex.getMessage());
        }
    }

    private String escape(String s) {
        return s.replace("|", "/"); // simplistic escaping
    }

    private void loadFromFile() {
        File f = new File(DATA_FILE);
        if (!f.exists()) {
            System.out.println("No existing data file found.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                String type = parts[0];
                if (type.equals("STUDENT") && parts.length >= 7) {
                    int id = Integer.parseInt(parts[1]);
                    String name = unescape(parts[2]);
                    int age = Integer.parseInt(parts[3]);
                    String program = unescape(parts[4]);
                    int year = Integer.parseInt(parts[5]);
                    double gpa = Double.parseDouble(parts[6]);
                    people.add(new Student(id, name, age, program, year, gpa));
                } else if (type.equals("PROF") && parts.length >= 7) {
                    int id = Integer.parseInt(parts[1]);
                    String name = unescape(parts[2]);
                    int age = Integer.parseInt(parts[3]);
                    String dept = unescape(parts[4]);
                    String title = unescape(parts[5]);
                    int courses = Integer.parseInt(parts[6]);
                    people.add(new Professor(id, name, age, dept, title, courses));
                } else if (type.equals("TA") && parts.length >= 8) {
                    int id = Integer.parseInt(parts[1]);
                    String name = unescape(parts[2]);
                    int age = Integer.parseInt(parts[3]);
                    String program = unescape(parts[4]);
                    int year = Integer.parseInt(parts[5]);
                    double gpa = Double.parseDouble(parts[6]);
                    String course = unescape(parts[7]);
                    people.add(new TeachingAssistant(id, name, age, program, year, gpa, course));
                } // ignore malformed lines
            }
            System.out.println("Loaded " + people.size() + " records from " + DATA_FILE);
        } catch (Exception ex) {
            System.out.println("Error loading file: " + ex.getMessage());
        }
    }

    private String unescape(String s) {
        return s.replace("/", "|");
    }
}
