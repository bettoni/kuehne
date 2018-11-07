package io.bettoni.contactlist.person.domain;

import java.util.Objects;

public class Person {
    private final String name;
    private final String photoUrl;

    public Person(String name, String photoUrl) {
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(photoUrl, person.photoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, photoUrl);
    }
}
