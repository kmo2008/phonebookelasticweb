package pl.kmo2008.phonebookelastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.kmo2008.phonebookelastic.model.Person;

import java.util.stream.Stream;

/**
 * Repository interface of Person for Elastic
 */
public interface PersonRepository extends ElasticsearchRepository<Person,String>{
    Stream<Person> findDistinctByNameContains(String name);
    Stream<Person> findDistinctByNameEquals(String name);
 }
