package io.bettoni.contactlist.person.domain;

import java.util.Objects;

public class PersonViewModel {

    private final String name;
    private final String photoUrl;

    public PersonViewModel(String name, String photoUrl) {
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonViewModel that = (PersonViewModel) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(photoUrl, that.photoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, photoUrl);
    }
}
