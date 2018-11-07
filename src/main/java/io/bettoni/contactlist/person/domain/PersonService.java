package io.bettoni.contactlist.person.domain;

import io.bettoni.contactlist.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople(String nameFilter, int page, int pageSize) {
        return personRepository.findAll(nameFilter, page, pageSize);
    }
}
