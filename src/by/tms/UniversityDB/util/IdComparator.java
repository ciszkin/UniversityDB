package by.tms.UniversityDB.util;

import by.tms.UniversityDB.entity.Student;

import java.util.Comparator;

public class IdComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getId(), o2.getId());
    }
}
