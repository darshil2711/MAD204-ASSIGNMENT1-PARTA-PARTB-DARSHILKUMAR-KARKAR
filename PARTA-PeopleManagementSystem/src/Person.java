/**
 * MAD204 - Assignment 1 Part A
 * Author: Darshilkumar Karkar (A00203357)
 * Date: 5th October 2025
 * Description: Abstract Person class for People Management System.
 */

public abstract class Person {
    protected int id;
    protected String name;
    protected int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public abstract String introduce();

    public void celebrateBirthday() {
        this.age += 1;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, name=%s, age=%d]", this.getClass().getSimpleName(), id, name, age);
    }

    // getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
}
