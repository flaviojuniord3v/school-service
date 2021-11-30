package dev.flaviojunior.service;

import dev.flaviojunior.domain.PersonAddress;
import dev.flaviojunior.repository.PersonAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link PersonAddress}.
 */
@Service
@Transactional
public class PersonAddressService {

    private final Logger log = LoggerFactory.getLogger(PersonAddressService.class);

    private final PersonAddressRepository personAddressRepository;

    public PersonAddressService(PersonAddressRepository personAddressRepository) {
        this.personAddressRepository = personAddressRepository;
    }

    /**
     * Save a personAddress.
     *
     * @param personAddress the entity to save.
     * @return the persisted entity.
     */
    public PersonAddress save(PersonAddress personAddress) {
        log.debug("Request to save PersonAddress : {}", personAddress);
        return personAddressRepository.save(personAddress);
    }

    /**
     * Partially update a personAddress.
     *
     * @param personAddress the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PersonAddress> partialUpdate(PersonAddress personAddress) {
        log.debug("Request to partially update PersonAddress : {}", personAddress);

        return personAddressRepository
            .findById(personAddress.getId())
            .map(
                existingPersonAddress -> {
                    if (personAddress.getType() != null) {
                        existingPersonAddress.setType(personAddress.getType());
                    }

                    return existingPersonAddress;
                }
            )
            .map(personAddressRepository::save);
    }

    /**
     * Get all the personAddresses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PersonAddress> findAll() {
        log.debug("Request to get all PersonAddresses");
        return personAddressRepository.findAll();
    }

    /**
     * Get one personAddress by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PersonAddress> findOne(Long id) {
        log.debug("Request to get PersonAddress : {}", id);
        return personAddressRepository.findById(id);
    }

    /**
     * Delete the personAddress by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PersonAddress : {}", id);
        personAddressRepository.deleteById(id);
    }
}
