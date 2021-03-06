package dev.flaviojunior.web.rest;

import dev.flaviojunior.common.web.util.HeaderUtil;
import dev.flaviojunior.common.web.util.ResponseUtil;
import dev.flaviojunior.domain.Subject;
import dev.flaviojunior.repository.SubjectRepository;
import dev.flaviojunior.service.SubjectQueryService;
import dev.flaviojunior.service.SubjectService;
import dev.flaviojunior.service.criteria.SubjectCriteria;
import dev.flaviojunior.common.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link Subject}.
 */
@RestController
@RequestMapping("/api")
public class SubjectResource {

    private final Logger log = LoggerFactory.getLogger(SubjectResource.class);

    private static final String ENTITY_NAME = "subject";

    @Value("${spring.application.name}")
    private String applicationName;

    private final SubjectService subjectService;

    private final SubjectRepository subjectRepository;

    private final SubjectQueryService subjectQueryService;

    public SubjectResource(SubjectService subjectService, SubjectRepository subjectRepository, SubjectQueryService subjectQueryService) {
        this.subjectService = subjectService;
        this.subjectRepository = subjectRepository;
        this.subjectQueryService = subjectQueryService;
    }

    /**
     * {@code POST  /subjects} : Creates a new subject.
     *
     * @param subject A subject to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subject, or with status {@code 400 (Bad Request)} if the subject has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subjects")
    public ResponseEntity<Subject> createSubject(@Valid @RequestBody Subject subject) throws URISyntaxException {
        log.debug("REST request to save Subject : {}", subject);
        if (subject.getId() != null) {
            throw new BadRequestAlertException("A new subject cannot already have an ID", ENTITY_NAME);
        }
        Subject result = subjectService.save(subject);
        return ResponseEntity
            .created(new URI("/api/subjects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subjects/:id} : Updates an existing subject.
     *
     * @param id the id of the subject to save.
     * @param subject the subject to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subject,
     * or with status {@code 400 (Bad Request)} if the subject is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subject couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subjects/{id}")
    public ResponseEntity<Subject> updateSubject(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Subject subject
    ) throws URISyntaxException {
        log.debug("REST request to update Subject : {}, {}", id, subject);
        if (subject.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, subject.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!subjectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        Subject result = subjectService.save(subject);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, subject.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /subjects/:id} : Partial updates given fields of an existing subject, field will ignore if it is null
     *
     * @param id the id of the subject to save.
     * @param subject the subject to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subject,
     * or with status {@code 400 (Bad Request)} if the subject is not valid,
     * or with status {@code 404 (Not Found)} if the subject is not found,
     * or with status {@code 500 (Internal Server Error)} if the subject couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/subjects/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Subject> partialUpdateSubject(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Subject subject
    ) throws URISyntaxException {
        log.debug("REST request to partial update Subject partially : {}, {}", id, subject);
        if (subject.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, subject.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!subjectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        Optional<Subject> result = subjectService.partialUpdate(subject);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, subject.getId().toString())
        );
    }

    /**
     * {@code GET  /subjects} : get all the subjects.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subjects in body.
     */
    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> getAllSubjects(SubjectCriteria criteria) {
        log.debug("REST request to get Subjects by criteria: {}", criteria);
        List<Subject> entityList = subjectQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /subjects/count} : count all the subjects.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/subjects/count")
    public ResponseEntity<Long> countSubjects(SubjectCriteria criteria) {
        log.debug("REST request to count Subjects by criteria: {}", criteria);
        return ResponseEntity.ok().body(subjectQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /subjects/:id} : get the "id" subject.
     *
     * @param id the id of the subject to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subject, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
        log.debug("REST request to get Subject : {}", id);
        Optional<Subject> subject = subjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subject);
    }

    /**
     * {@code DELETE  /subjects/:id} : delete the "id" subject.
     *
     * @param id the id of the subject to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        log.debug("REST request to delete Subject : {}", id);
        subjectService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, id.toString()))
            .build();
    }
}
