package io.bettoni.contactlist.person.controller;

import io.bettoni.contactlist.person.domain.Person;
import io.bettoni.contactlist.person.domain.PersonService;
import io.bettoni.contactlist.person.domain.PersonViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PersonApiTest {

    private static final Person JOHN = new Person("John Lennon", "url_john");
    private static final Person PAUL = new Person("Paul McCartney", "url_paul");
    private static final List<Person> PEOPLE = asList(JOHN, PAUL);
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NUMBER = 1;

    PersonApi personApi;

    @Mock
    PersonService personService;

    @Before
    public void setUp() {
        personApi = new PersonApi(personService);
    }

    @Test
    public void
    should_return_a_list_of_people() {
        given(personService.getPeople("", PAGE_NUMBER, PAGE_SIZE)).willReturn(PEOPLE);

        ResponseEntity<List<PersonViewModel>> result = personApi.getPeople("", PAGE_NUMBER, PAGE_SIZE);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(aListOfViewModelFrom(PEOPLE));
    }

    @Test
    public void
    should_not_allow_requiest_without_page_number() {
        ResponseEntity<List<PersonViewModel>> result = personApi.getPeople("", 0, PAGE_SIZE);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private List<PersonViewModel> aListOfViewModelFrom(List<Person> people) {
        return people
                .stream()
                .map(person -> new PersonViewModel(person.getName(), person.getPhotoUrl()))
                .collect(Collectors.toList());

    }
}