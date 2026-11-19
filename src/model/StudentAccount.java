package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pattern Builder - role: Product
 * Constructeur prive: creation uniquement via Builder.
 */
public class StudentAccount {

    private final String id;
    private final String fullName;
    private final String email;
    private final String phone;
    private final String level;
    private final String major;
    private final boolean scholarship;
    private final List<String> courses;

    private StudentAccount(Builder b) {
        this.id = b.id;
        this.fullName = b.fullName;
        this.email = b.email;
        this.phone = b.phone;
        this.level = b.level;
        this.major = b.major;
        this.scholarship = b.scholarship;
        this.courses = new ArrayList<>(b.courses);
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLevel() { return level; }
    public String getMajor() { return major; }
    public boolean hasScholarship() { return scholarship; }
    public List<String> getCourses() { return new ArrayList<>(courses); }

    @Override
    public String toString() {
        return "StudentAccount{id=" + id + ", name=" + fullName
                + ", email=" + email + ", phone=" + phone
                + ", level=" + level + ", major=" + major
                + ", scholarship=" + scholarship
                + ", courses=" + courses + "}";
    }// Pattern Prototype - role: ConcretePrototype
    // Copie ce compte (modele) en changeant seulement l'identite.
    public StudentAccount cloneWith(String newId, String newFullName, String newEmail) {
        return new Builder(newId, newFullName, newEmail)
                .phone(this.phone)
                .level(this.level)
                .major(this.major)
                .scholarship(this.scholarship)
                .courses(this.courses)   // le Builder fait une NOUVELLE liste
                .build();
    }


    // Pattern Builder - role: ConcreteBuilder
    public static class Builder {

        private static final List<String> LEVELS =
                Arrays.asList("L1", "L2", "L3", "M1", "M2");

        private final String id;
        private final String fullName;
        private final String email;

        private String phone = null;
        private String level = "L1";
        private String major = null;
        private boolean scholarship = false;
        private List<String> courses = new ArrayList<>();

        public Builder(String id, String fullName, String email) {
            this.id = id;
            this.fullName = fullName;
            this.email = email;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder level(String level) {
            this.level = level;
            return this;
        }

        public Builder major(String major) {
            this.major = major;
            return this;
        }

        public Builder scholarship(boolean scholarship) {
            this.scholarship = scholarship;
            return this;
        }

        public Builder courses(List<String> courses) {
            this.courses = new ArrayList<>(courses);
            return this;
        }

        public Builder addCourse(String course) {
            this.courses.add(course);
            return this;
        }

        public StudentAccount build() {
            if (id == null || id.trim().isEmpty())
                throw new IllegalArgumentException("id obligatoire et non vide");
            if (fullName == null || fullName.trim().isEmpty())
                throw new IllegalArgumentException("fullName obligatoire");
            if (email == null || !email.contains("@"))
                throw new IllegalArgumentException("email invalide (doit contenir @)");
            if (!LEVELS.contains(level))
                throw new IllegalArgumentException("level invalide : " + level);
            return new StudentAccount(this);
        }
    }
}