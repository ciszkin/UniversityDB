package by.tms.UniversityDB.service;

import by.tms.UniversityDB.entity.Faculty;
import by.tms.UniversityDB.entity.Student;

public class StudentService1 implements StudentService {

    //Student currentStudent = null;

    public StudentService1() {
    }

    @Override
    public void addStudent(Student student, Faculty faculty) {
        faculty.addStudent(student);
    }

    @Override
    public Student[] getStudents(Faculty faculty) {
        return faculty.getStudents();
    }

    @Override
    public void moveStudent(Student student, Faculty from, Faculty to) {
        from.removeStudent(student);
        to.addStudent(student);
    }

    @Override
    public void removeStudent(Student student, Faculty faculty) {
        faculty.removeStudent(student);
    }
}
