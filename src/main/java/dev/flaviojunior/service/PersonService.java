package dev.flaviojunior.service;

import dev.flaviojunior.domain.Person;
import dev.flaviojunior.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Person}.
 */
@Service
@Transactional
public class PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Save a person.
     *
     * @param person the entity to save.
     * @return the persisted entity.
     */
    public Person save(Person person) {
        log.debug("Request to save Person : {}", person);
        return personRepository.save(person);
    }

    /**
     * Partially update a person.
     *
     * @param person the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Person> partialUpdate(Person person) {
        log.debug("Request to partially update Person : {}", person);

        return personRepository
            .findById(person.getId())
            .map(
                existingPerson -> {
                    if (person.getFirstName() != null) {
                        existingPerson.setFirstName(person.getFirstName());
                    }
                    if (person.getLastName() != null) {
                        existingPerson.setLastName(person.getLastName());
                    }
                    if (person.getGender() != null) {
                        existingPerson.setGender(person.getGender());
                    }
                    if (person.getBirthDate() != null) {
                        existingPerson.setBirthDate(person.getBirthDate());
                    }
                    if (person.getEmail() != null) {
                        existingPerson.setEmail(person.getEmail());
                    }
                    if (person.getPhoneNumber() != null) {
                        existingPerson.setPhoneNumber(person.getPhoneNumber());
                    }

                    return existingPerson;
                }
            )
            .map(personRepository::save);
    }

    /**
     * Get all the people.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        log.debug("Request to get all People");
        return personRepository.findAll();
    }

    /**
     * Get one person by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Person> findOne(Long id) {
        log.debug("Request to get Person : {}", id);
        return personRepository.findById(id);
    }

    /**
     * Delete the person by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Person : {}", id);
        personRepository.deleteById(id);
    }
}
