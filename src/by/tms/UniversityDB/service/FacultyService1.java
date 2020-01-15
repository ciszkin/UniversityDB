package by.tms.UniversityDB.service;

import by.tms.UniversityDB.entity.Faculty;

import java.util.HashMap;
import java.util.Map;

public class FacultyService1 implements FacultyService {
    private static Map<String, Faculty> faculties = new HashMap<>();

    //private Faculty currentFaculty = null;

    public FacultyService1() {
    }

    //creates FacultyService on basis of saved file, which contains the set of faculties
    public FacultyService1(String fileName) {
        //
    }

    public void addFaculty(String name) {

        if (faculties.containsKey(name)) {
            return;
        }
        faculties.put(name, new Faculty(name));
    }

    public Faculty getFaculty(String name) {
        return faculties.get(name);
    }

    public String[] getFaculties() {
        return faculties.keySet().toArray(new String[0]);
    }


}
