package by.tms.UniversityDB.entity;

import java.util.Date;
import java.util.Objects;

public class Student extends Person {
    private static final long serialVersionUID = -3425756374070082581L;
    //private String group;
    private static int firstID = 0;
    private int id = firstID++;
    private int rank;

    public Student(String name,
                   String surname,
                   Date birthDate) {
        super(name, surname, birthDate);
        //this.group = group;
    }

//    public void setGroup(String group) {
//        this.group = group;
//    }
//
//    public String getGroup() {
//        return group;
//    }

    public int getId() {
        return id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    //equals() & hashCode() without group
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return super.toString() + " Student{" +
                "id=" + id +
                '}';
    }
}
