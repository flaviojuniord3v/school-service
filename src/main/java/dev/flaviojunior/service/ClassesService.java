package dev.flaviojunior.service;

import dev.flaviojunior.domain.Classes;
import dev.flaviojunior.repository.ClassesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Classes}.
 */
@Service
@Transactional
public class ClassesService {

    private final Logger log = LoggerFactory.getLogger(ClassesService.class);

    private final ClassesRepository classesRepository;

    public ClassesService(ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }

    /**
     * Save a classes.
     *
     * @param classes the entity to save.
     * @return the persisted entity.
     */
    public Classes save(Classes classes) {
        log.debug("Request to save Classes : {}", classes);
        return classesRepository.save(classes);
    }

    /**
     * Partially update a classes.
     *
     * @param classes the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Classes> partialUpdate(Classes classes) {
        log.debug("Request to partially update Classes : {}", classes);

        return classesRepository
            .findById(classes.getId())
            .map(
                existingClasses -> {
                    if (classes.getName() != null) {
                        existingClasses.setName(classes.getName());
                    }

                    return existingClasses;
                }
            )
            .map(classesRepository::save);
    }

    /**
     * Get all the classes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Classes> findAll() {
        log.debug("Request to get all Classes");
        return classesRepository.findAll();
    }

    /**
     * Get one classes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Classes> findOne(Long id) {
        log.debug("Request to get Classes : {}", id);
        return classesRepository.findById(id);
    }

    /**
     * Delete the classes by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Classes : {}", id);
        classesRepository.deleteById(id);
    }
}
