package by.tms.UniversityDB.service;

import by.tms.UniversityDB.entity.Faculty;

public interface FacultyService {
    void addFaculty(String name);
    Faculty getFaculty(String name);
    String[] getFaculties();
}
