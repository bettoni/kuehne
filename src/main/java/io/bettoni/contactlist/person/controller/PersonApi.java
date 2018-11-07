package io.bettoni.contactlist.person.controller;

import io.bettoni.contactlist.person.domain.PersonService;
import io.bettoni.contactlist.person.domain.PersonViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PersonApi {

    private PersonService personService;

    @Autowired
    public PersonApi(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/people")
    ResponseEntity<List<PersonViewModel>> getPeople(@RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                                    @RequestParam(value = "page_size", defaultValue = "20") int pageSize) {

        if (page == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(personService.getPeople(name, page, pageSize)
                                                    .stream()
                                                    .map(person -> new PersonViewModel(person.getName(), person.getPhotoUrl()))
                                                    .collect(Collectors.toList()));
    }
}
