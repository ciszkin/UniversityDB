package by.tms.UniversityDB.util;

import by.tms.UniversityDB.entity.Student;

import java.util.Comparator;

public class RankComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getRank(), o2.getRank());
    }
}
