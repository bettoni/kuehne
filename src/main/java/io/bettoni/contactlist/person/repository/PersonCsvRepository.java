package io.bettoni.contactlist.person.repository;

import io.bettoni.contactlist.person.domain.Person;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonCsvRepository implements PersonRepository {

    private static final String PEOPLE_FILE = "/people.csv";
    private static final String CSV_SPLIT_BY = ",";
    private static final List<Person> people;

    static {
        try (BufferedReader csvContent = new BufferedReader(new InputStreamReader(PersonCsvRepository.class.getResourceAsStream(PEOPLE_FILE)))) {

            people = new LinkedList<>();
            csvContent
                    .lines()
                    .skip(1)
                    .map(linha -> linha.split(CSV_SPLIT_BY))
                    .forEach(lineContent -> addPersonToMemory(lineContent));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> findAll(String nameFilter, int page, int pageSize) {
        return people.stream()
                .filter(person -> personContainsName(person, nameFilter))
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    private boolean personContainsName(Person person, String nameFilter) {
        if (nameFilter != null)
            return person.getName().toUpperCase().contains(nameFilter.toUpperCase());

        return true;
    }

    private static void addPersonToMemory(String[] lineContent) {
        String name = lineContent[0];
        String url = lineContent[1];

        people.add(new Person(name, url));
    }

}
