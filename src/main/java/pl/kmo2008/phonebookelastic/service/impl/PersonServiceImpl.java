package pl.kmo2008.phonebookelastic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kmo2008.phonebookelastic.model.Person;
import pl.kmo2008.phonebookelastic.repository.PersonRepository;
import pl.kmo2008.phonebookelastic.service.PersonService;

import java.util.stream.Stream;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public void setBookRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Person person) {
    personRepository.delete(person);
    }


    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Stream<Person> findDistinctByNameContains(String name) {
        return personRepository.findDistinctByNameContains(name);
    }

    @Override
    public Stream<Person> findDistinctByNameEquals(String name) {
        return personRepository.findDistinctByNameContains(name);
    }


}
