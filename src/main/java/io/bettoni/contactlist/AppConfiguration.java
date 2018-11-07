package io.bettoni.contactlist;

import io.bettoni.contactlist.person.repository.PersonCsvRepository;
import io.bettoni.contactlist.person.repository.PersonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public PersonRepository personRepository() {
        return new PersonCsvRepository();
    }
}
