package by.tms.UniversityDB.entity;

public enum Subject {
    MATHAN("Mathematical analysis", 0),
    NUCLEAR_PHYSICS("Nuclear physics", 1),
    COMPUTER_SCIENCE("Computer science", 2),
    ASTROLOGY("Astrology", 3),
    PARAPSYCHOLOGY("Parapsychology", 4),
    ALCHEMY("Alchemy", 5);

    private final String name;
    private final int value;
    Subject(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
