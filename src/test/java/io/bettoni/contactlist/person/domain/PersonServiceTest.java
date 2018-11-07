package io.bettoni.contactlist.person.domain;

import io.bettoni.contactlist.person.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    private static final Person JOHN = new Person("John Lennon", "photo_url");
    private static final Person PAUL = new Person("Paul McCartney", "photo_url");
    private static final Person GEORGE = new Person("George Harrison", "photo_url");
    private static final Person RINGO = new Person("Ringo Starr", "photo_url");
    private static final int PAGE = 1;
    private static final int PAGE_SIZE = 10;

    private PersonService service;

    @Mock
    PersonRepository personRepository;

    @Test
    public void
    should_get_a_list_of_person_from_a_repository() {
        given(personRepository.findAll("", PAGE, PAGE_SIZE)).willReturn(asList(JOHN, PAUL, GEORGE, RINGO));

        service = new PersonService(personRepository);
        List<Person> result = service.getPeople("", PAGE, PAGE_SIZE);

        verify(personRepository).findAll("", PAGE, PAGE_SIZE);
        assertThat(result).containsExactly(JOHN, PAUL, GEORGE, RINGO);
    }

}