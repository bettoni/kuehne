package io.bettoni.contactlist.person.repository;

import io.bettoni.contactlist.person.domain.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonCsvRepositoryTest {

    private static final int FIRST_PAGE = 1;
    private static final int SECOND_PAGE = 2;
    private static final int PAGE_SIZE = 10;
    private static final int SIMPSON_FAMILY_SIZE = 5;
    private static final int SMALL_PAGE_SIZE = 2;
    private static final Person HOMER = new Person("Homer Simpson", "url");
    private static final Person MARGE = new Person("Marge Simpson", "url");
    private static final Person BART = new Person("Bart Simpson", "url");
    private static final Person LISA = new Person("Lisa Simpson", "url");


    PersonCsvRepository repository;

    @Before
    public void setUp() {
        repository = new PersonCsvRepository();
    }

    @Test
    public void
    should_return_data_without_filter() {
        List<Person> result = repository.findAll("", FIRST_PAGE, PAGE_SIZE);

        assertThat(result.size()).isEqualTo(SIMPSON_FAMILY_SIZE);
    }

    @Test
    public void
    should_return_data_containing_name_used_as_filter() {
        List<Person> result = repository.findAll(HOMER.getName(), FIRST_PAGE, PAGE_SIZE);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo(HOMER.getName());
    }

    @Test
    public void
    should_return_a_list_equals_page_size() {
        List<Person> result = repository.findAll("", FIRST_PAGE, SMALL_PAGE_SIZE);

        assertThat(result.size()).isEqualTo(SMALL_PAGE_SIZE);
    }

    @Test
    public void
    should_skip_data_based_on_page_numer() {
        List<Person> result = repository.findAll("", FIRST_PAGE, SMALL_PAGE_SIZE);

        assertThat(result.size()).isEqualTo(SMALL_PAGE_SIZE);
        assertThat(result.get(0).getName()).isEqualTo(HOMER.getName());
        assertThat(result.get(1).getName()).isEqualTo(MARGE.getName());

        result = repository.findAll("", SECOND_PAGE, SMALL_PAGE_SIZE);

        assertThat(result.get(0).getName()).isEqualTo(BART.getName());
        assertThat(result.get(1).getName()).isEqualTo(LISA.getName());
    }
}