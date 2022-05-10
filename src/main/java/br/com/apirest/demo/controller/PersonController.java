package br.com.apirest.demo.controller;

import br.com.apirest.demo.entity.Person;
import br.com.apirest.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    //Annotation for Spring Dependency Injection
    @Autowired
    private PersonRepository personRepository;

    //Annotation for mapping url and http method used
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    //Annotation PathVariable used when passing value into url
    @RequestMapping(value="/person/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long id ){
        Optional<Person> person = personRepository.findById(id);

        if( person.isPresent() ){
            return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
    }

    //Method specialist for insert person in Database
    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Person createPerson(@Valid @RequestBody Person person){
        return personRepository.save(person);
    }

    //Update of person from form web throught of method PUT
    @RequestMapping(value="/person", method =  RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person formPerson){
        Optional<Person> personCheck = personRepository.findById( formPerson.getId() );
        if( personCheck.isPresent() ){
            Person personUpdate = personCheck.get();
            personUpdate.setName( formPerson.getName() );
            personRepository.save(personUpdate);
            return new ResponseEntity<Person>(personUpdate, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
    }

    //Method created with objective to exclude entity person from database
    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> excludePerson(@PathVariable(value = "id") Long id){
        Optional<Person> person = personRepository.findById(id);

        if(person.isPresent()){
            personRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
