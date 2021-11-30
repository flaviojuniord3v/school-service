package dev.flaviojunior.web.rest;

import dev.flaviojunior.common.web.util.HeaderUtil;
import dev.flaviojunior.common.web.util.ResponseUtil;
import dev.flaviojunior.domain.Timetable;
import dev.flaviojunior.repository.TimetableRepository;
import dev.flaviojunior.service.TimetableQueryService;
import dev.flaviojunior.service.TimetableService;
import dev.flaviojunior.service.criteria.TimetableCriteria;
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
 * REST controller for managing {@link Timetable}.
 */
@RestController
@RequestMapping("/api")
public class TimetableResource {

    private final Logger log = LoggerFactory.getLogger(TimetableResource.class);

    private static final String ENTITY_NAME = "timetable";

    @Value("${spring.application.name}")
    private String applicationName;

    private final TimetableService timetableService;

    private final TimetableRepository timetableRepository;

    private final TimetableQueryService timetableQueryService;

    public TimetableResource(
        TimetableService timetableService,
        TimetableRepository timetableRepository,
        TimetableQueryService timetableQueryService
    ) {
        this.timetableService = timetableService;
        this.timetableRepository = timetableRepository;
        this.timetableQueryService = timetableQueryService;
    }

    /**
     * {@code POST  /timetables} : Creates a new timetable.
     *
     * @param timetable A timetable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new timetable, or with status {@code 400 (Bad Request)} if the timetable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/timetables")
    public ResponseEntity<Timetable> createTimetable(@Valid @RequestBody Timetable timetable) throws URISyntaxException {
        log.debug("REST request to save Timetable : {}", timetable);
        if (timetable.getId() != null) {
            throw new BadRequestAlertException("A new timetable cannot already have an ID", ENTITY_NAME);
        }
        Timetable result = timetableService.save(timetable);
        return ResponseEntity
            .created(new URI("/api/timetables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /timetables/:id} : Updates an existing timetable.
     *
     * @param id the id of the timetable to save.
     * @param timetable the timetable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated timetable,
     * or with status {@code 400 (Bad Request)} if the timetable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the timetable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/timetables/{id}")
    public ResponseEntity<Timetable> updateTimetable(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Timetable timetable
    ) throws URISyntaxException {
        log.debug("REST request to update Timetable : {}, {}", id, timetable);
        if (timetable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, timetable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!timetableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        Timetable result = timetableService.save(timetable);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, timetable.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /timetables/:id} : Partial updates given fields of an existing timetable, field will ignore if it is null
     *
     * @param id the id of the timetable to save.
     * @param timetable the timetable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated timetable,
     * or with status {@code 400 (Bad Request)} if the timetable is not valid,
     * or with status {@code 404 (Not Found)} if the timetable is not found,
     * or with status {@code 500 (Internal Server Error)} if the timetable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/timetables/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Timetable> partialUpdateTimetable(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Timetable timetable
    ) throws URISyntaxException {
        log.debug("REST request to partial update Timetable partially : {}, {}", id, timetable);
        if (timetable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, timetable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!timetableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        Optional<Timetable> result = timetableService.partialUpdate(timetable);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, timetable.getId().toString())
        );
    }

    /**
     * {@code GET  /timetables} : get all the timetables.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of timetables in body.
     */
    @GetMapping("/timetables")
    public ResponseEntity<List<Timetable>> getAllTimetables(TimetableCriteria criteria) {
        log.debug("REST request to get Timetables by criteria: {}", criteria);
        List<Timetable> entityList = timetableQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /timetables/count} : count all the timetables.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/timetables/count")
    public ResponseEntity<Long> countTimetables(TimetableCriteria criteria) {
        log.debug("REST request to count Timetables by criteria: {}", criteria);
        return ResponseEntity.ok().body(timetableQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /timetables/:id} : get the "id" timetable.
     *
     * @param id the id of the timetable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the timetable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/timetables/{id}")
    public ResponseEntity<Timetable> getTimetable(@PathVariable Long id) {
        log.debug("REST request to get Timetable : {}", id);
        Optional<Timetable> timetable = timetableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(timetable);
    }

    /**
     * {@code DELETE  /timetables/:id} : delete the "id" timetable.
     *
     * @param id the id of the timetable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/timetables/{id}")
    public ResponseEntity<Void> deleteTimetable(@PathVariable Long id) {
        log.debug("REST request to delete Timetable : {}", id);
        timetableService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, id.toString()))
            .build();
    }
}
