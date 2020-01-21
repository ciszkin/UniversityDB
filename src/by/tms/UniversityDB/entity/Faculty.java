package by.tms.UniversityDB.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Faculty implements Serializable {
    private static final long serialVersionUID = 6696354608939911530L;
    private String name;
    private Set<Student> students = new HashSet<>();
    private Set<Lecturer> lecturers = new HashSet<>();

    public Faculty() {
    }

    public Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPerson(Person person) {
        if(person instanceof Student) {
            students.add((Student) person);
        } else {
            lecturers.add((Lecturer) person);
        }
    }

    public void removePerson(Person person) {
        if(person instanceof Student) {
            students.remove(person);
        } else {
            lecturers.remove(person);
        }
    }

    public boolean checkPerson(Person person) {
        if(person instanceof Student) return students.contains(person);
        return lecturers.contains(person);
    }

    public Student[] getStudents() {
        return students.toArray(new Student[0]);
    }

    public Lecturer[] getLecturers() {
        return lecturers.toArray(new Lecturer[0]);
    }

    @Override
    public String toString() {
        return "Faculty: " +
                "name: " + name +
                ", students: " + students.size() +
                ", lecturers: " + lecturers.size() +
                '}';
    }
}
