package com.abdulsamadsyed.person.service;


import com.abdulsamadsyed.person.Config.CacheConfig;
import com.abdulsamadsyed.person.model.Person;
import com.abdulsamadsyed.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


import javax.cache.annotation.CacheResult;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {


    @Value("${external.db.name:Unloaded DB}")
    private String dbName;

    @Value("${external.db.user: Unloaded User}")
    private String dbUser;

    @Value("${external.db.password: Unloaded Password}")
    private String password;

    @Value("${external.greetings.message:No value found for greetings message}")
    private String greetingsMessage;

    private final PersonRepository personRepository;

    public Mono<List<Person>> findAll() {

        System.out.println("All persons");
        System.out.println(greetingsMessage +"\nDb: " +  dbName + "\nUser: " + dbUser + "\nPassword: " + password);
        return Mono.just(personRepository.findAll());
    }


    @Cacheable(value = "personCache", key = "#id")
    public Optional<Person> findOne(Long id) throws InterruptedException {
        System.out.println("Before Sleep");
        Thread.sleep(4000);
        return personRepository.findById(id);
    }

    public Mono<Person> createPerson(Person person) {
        return Mono.just(personRepository.save(person));
    }

}
