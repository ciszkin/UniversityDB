package by.tms.UniversityDB.util;

import by.tms.UniversityDB.entity.Person;

import java.util.Comparator;

public class BirthDateComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getBirthDate().compareTo(o2.getBirthDate());
    }
}
