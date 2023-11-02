package edu.syr.oodproject.trelloclonesu.models.status;

public enum TaskStatus {
    TODO ("TODO"),
    DOING ("DOING"),
    DONE ("DONE");

    private final String name;
    TaskStatus(String s) {
        name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
