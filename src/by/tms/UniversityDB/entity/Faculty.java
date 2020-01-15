package by.tms.UniversityDB.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Faculty implements Serializable {
    private static final long serialVersionUID = 6696354608939911530L;
    private String name;
    //private Set<Group> groups = new HashSet<>();
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
            students.remove((Student) person);
        } else {
            lecturers.remove((Lecturer) person);
        }
    }

    public boolean checkPerson(Person person) {
        return (students.contains(person) || lecturers.contains(person));
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public boolean checkStudent(Student student) {
        return students.contains(student);
    }

    public Student[] getStudents() {
        return students.toArray(new Student[0]);
    }

    public void addLecturer(Lecturer lecturer) {
        lecturers.add(lecturer);
    }

    public void removeLecturer(Lecturer lecturer) {
        lecturers.remove(lecturer);
    }

    public boolean checkLecturer(Lecturer lecturer) {
        return lecturers.contains(lecturer);
    }

    public Lecturer[] getLecturers() {
        return lecturers.toArray(new Lecturer[0]);
    }
}
