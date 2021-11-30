package dev.flaviojunior.service;

import dev.flaviojunior.domain.Subject;
import dev.flaviojunior.repository.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Subject}.
 */
@Service
@Transactional
public class SubjectService {

    private final Logger log = LoggerFactory.getLogger(SubjectService.class);

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    /**
     * Save a subject.
     *
     * @param subject the entity to save.
     * @return the persisted entity.
     */
    public Subject save(Subject subject) {
        log.debug("Request to save Subject : {}", subject);
        return subjectRepository.save(subject);
    }

    /**
     * Partially update a subject.
     *
     * @param subject the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Subject> partialUpdate(Subject subject) {
        log.debug("Request to partially update Subject : {}", subject);

        return subjectRepository
            .findById(subject.getId())
            .map(
                existingSubject -> {
                    if (subject.getName() != null) {
                        existingSubject.setName(subject.getName());
                    }
                    if (subject.getCode() != null) {
                        existingSubject.setCode(subject.getCode());
                    }

                    return existingSubject;
                }
            )
            .map(subjectRepository::save);
    }

    /**
     * Get all the subjects.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        log.debug("Request to get all Subjects");
        return subjectRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the subjects with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Subject> findAllWithEagerRelationships(Pageable pageable) {
        return subjectRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one subject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Subject> findOne(Long id) {
        log.debug("Request to get Subject : {}", id);
        return subjectRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the subject by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Subject : {}", id);
        subjectRepository.deleteById(id);
    }
}
