package by.tms.UniversityDB.service;

import by.tms.UniversityDB.entity.Faculty;
import by.tms.UniversityDB.entity.Student;

public interface StudentService {
    void addStudent(Student student, Faculty faculty);
    Student[] getStudents(Faculty faculty);
    void moveStudent(Student student, Faculty from, Faculty to);
    void removeStudent(Student student, Faculty facultyName);

}
