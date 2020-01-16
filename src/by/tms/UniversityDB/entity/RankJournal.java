package by.tms.UniversityDB.entity;

import java.io.Serializable;
import java.util.*;

public class RankJournal implements Serializable {
    private static final long serialVersionUID = 9134753109378777144L;
    private Map<Subject, List<Integer>> values;

    public RankJournal() {
    }

    public RankJournal(Subject[] subjects) {
        values = new HashMap<>();
        for(Subject s: subjects) values.put(s, new ArrayList<>());
    }

    public Subject[] getSubjects() {
        return values.keySet().toArray(new Subject[0]);
    }

    public void addSubject(Subject subject) {
        if(values.containsKey(subject)) return;
        values.put(subject, new ArrayList<>());
    }

    public int[] getRankHistory(Subject subject) {
        return Arrays.stream(values.get(subject).toArray(new Integer[0])).mapToInt(Integer::intValue).toArray();
    }

    public void setRank(Subject subject, int rank) {
        values.get(subject).add(rank);
    }

    @Override
    public String toString() {
        return "RankJournal{" +
                "values=" + values +
                '}';
    }
}
