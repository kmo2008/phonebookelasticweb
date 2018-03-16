package pl.kmo2008.phonebookelastic.service;

import pl.kmo2008.phonebookelastic.model.Person;

import java.util.stream.Stream;

/**
 * Service for database
 */
public interface PersonService {

    Person save(Person person);

    void delete(Person person);


    Iterable<Person> findAll();

    Stream<Person> findDistinctByNameContains(String name);

    Stream<Person> findDistinctByNameEquals(String name);
}
