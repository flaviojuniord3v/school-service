package dev.flaviojunior.web.rest;

import dev.flaviojunior.common.web.util.HeaderUtil;
import dev.flaviojunior.common.web.util.ResponseUtil;
import dev.flaviojunior.domain.Classes;
import dev.flaviojunior.repository.ClassesRepository;
import dev.flaviojunior.service.ClassesQueryService;
import dev.flaviojunior.service.ClassesService;
import dev.flaviojunior.service.criteria.ClassesCriteria;
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
 * REST controller for managing {@link Classes}.
 */
@RestController
@RequestMapping("/api")
public class ClassesResource {

    private final Logger log = LoggerFactory.getLogger(ClassesResource.class);

    private static final String ENTITY_NAME = "classes";

    @Value("${spring.application.name}")
    private String applicationName;

    private final ClassesService classesService;

    private final ClassesRepository classesRepository;

    private final ClassesQueryService classesQueryService;

    public ClassesResource(ClassesService classesService, ClassesRepository classesRepository, ClassesQueryService classesQueryService) {
        this.classesService = classesService;
        this.classesRepository = classesRepository;
        this.classesQueryService = classesQueryService;
    }

    /**
     * {@code POST  /classes} : Creates a new classes.
     *
     * @param classes A classes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new classes, or with status {@code 400 (Bad Request)} if the classes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/classes")
    public ResponseEntity<Classes> createClasses(@Valid @RequestBody Classes classes) throws URISyntaxException {
        log.debug("REST request to save Classes : {}", classes);
        if (classes.getId() != null) {
            throw new BadRequestAlertException("A new classes cannot already have an ID", ENTITY_NAME);
        }
        Classes result = classesService.save(classes);
        return ResponseEntity
            .created(new URI("/api/classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /classes/:id} : Updates an existing classes.
     *
     * @param id the id of the classes to save.
     * @param classes the classes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated classes,
     * or with status {@code 400 (Bad Request)} if the classes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the classes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/classes/{id}")
    public ResponseEntity<Classes> updateClasses(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Classes classes
    ) throws URISyntaxException {
        log.debug("REST request to update Classes : {}, {}", id, classes);
        if (classes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, classes.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!classesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        Classes result = classesService.save(classes);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, classes.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /classes/:id} : Partial updates given fields of an existing classes, field will ignore if it is null
     *
     * @param id the id of the classes to save.
     * @param classes the classes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated classes,
     * or with status {@code 400 (Bad Request)} if the classes is not valid,
     * or with status {@code 404 (Not Found)} if the classes is not found,
     * or with status {@code 500 (Internal Server Error)} if the classes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/classes/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Classes> partialUpdateClasses(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Classes classes
    ) throws URISyntaxException {
        log.debug("REST request to partial update Classes partially : {}, {}", id, classes);
        if (classes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, classes.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!classesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        Optional<Classes> result = classesService.partialUpdate(classes);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, classes.getId().toString())
        );
    }

    /**
     * {@code GET  /classes} : get all the classes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of classes in body.
     */
    @GetMapping("/classes")
    public ResponseEntity<List<Classes>> getAllClasses(ClassesCriteria criteria) {
        log.debug("REST request to get Classes by criteria: {}", criteria);
        List<Classes> entityList = classesQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /classes/count} : count all the classes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/classes/count")
    public ResponseEntity<Long> countClasses(ClassesCriteria criteria) {
        log.debug("REST request to count Classes by criteria: {}", criteria);
        return ResponseEntity.ok().body(classesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /classes/:id} : get the "id" classes.
     *
     * @param id the id of the classes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the classes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/classes/{id}")
    public ResponseEntity<Classes> getClasses(@PathVariable Long id) {
        log.debug("REST request to get Classes : {}", id);
        Optional<Classes> classes = classesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(classes);
    }

    /**
     * {@code DELETE  /classes/:id} : delete the "id" classes.
     *
     * @param id the id of the classes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/classes/{id}")
    public ResponseEntity<Void> deleteClasses(@PathVariable Long id) {
        log.debug("REST request to delete Classes : {}", id);
        classesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, id.toString()))
            .build();
    }
}
