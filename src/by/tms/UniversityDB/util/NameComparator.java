package by.tms.UniversityDB.util;

import by.tms.UniversityDB.entity.Person;

import java.util.Comparator;

public class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
