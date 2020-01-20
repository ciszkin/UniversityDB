package by.tms.UniversityDB.entity;

import java.util.*;

public class Student extends Person {
    private static final long serialVersionUID = -3425756374070082581L;

    private RankJournal rankJournal;

    public Student() {
    }

    public Student(String name,
                   String surname,
                   Date birthDate, String pass, Subject[] subjects) {
        super(name, surname, birthDate, pass);
        rankJournal = new RankJournal(subjects);
    }



    public RankJournal getRankJournal() {
        return rankJournal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(rankJournal, student.rankJournal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rankJournal);
    }

    @Override
    public String toString() {
        return super.toString() + " Student{" +
                "rankJournal=" + rankJournal +
                '}';
    }
}
