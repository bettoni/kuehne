package io.bettoni.contactlist.person.repository;

import io.bettoni.contactlist.person.domain.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> findAll(String nameFilter, int page, int pageSize);
}
