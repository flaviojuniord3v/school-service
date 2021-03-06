package dev.flaviojunior.web.rest;

import dev.flaviojunior.common.web.util.HeaderUtil;
import dev.flaviojunior.common.web.util.ResponseUtil;
import dev.flaviojunior.domain.Day;
import dev.flaviojunior.repository.DayRepository;
import dev.flaviojunior.service.DayQueryService;
import dev.flaviojunior.service.DayService;
import dev.flaviojunior.service.criteria.DayCriteria;
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
 * REST controller for managing {@link Day}.
 */
@RestController
@RequestMapping("/api")
public class DayResource {

    private final Logger log = LoggerFactory.getLogger(DayResource.class);

    private static final String ENTITY_NAME = "day";

    @Value("${spring.application.name}")
    private String applicationName;

    private final DayService dayService;

    private final DayRepository dayRepository;

    private final DayQueryService dayQueryService;

    public DayResource(DayService dayService, DayRepository dayRepository, DayQueryService dayQueryService) {
        this.dayService = dayService;
        this.dayRepository = dayRepository;
        this.dayQueryService = dayQueryService;
    }

    /**
     * {@code POST  /days} : Creates a new day.
     *
     * @param day A day to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new day, or with status {@code 400 (Bad Request)} if the day has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/days")
    public ResponseEntity<Day> createDay(@Valid @RequestBody Day day) throws URISyntaxException {
        log.debug("REST request to save Day : {}", day);
        if (day.getId() != null) {
            throw new BadRequestAlertException("A new day cannot already have an ID", ENTITY_NAME);
        }
        Day result = dayService.save(day);
        return ResponseEntity
            .created(new URI("/api/days/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /days/:id} : Updates an existing day.
     *
     * @param id the id of the day to save.
     * @param day the day to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated day,
     * or with status {@code 400 (Bad Request)} if the day is not valid,
     * or with status {@code 500 (Internal Server Error)} if the day couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/days/{id}")
    public ResponseEntity<Day> updateDay(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Day day)
        throws URISyntaxException {
        log.debug("REST request to update Day : {}, {}", id, day);
        if (day.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, day.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!dayRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        Day result = dayService.save(day);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, day.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /days/:id} : Partial updates given fields of an existing day, field will ignore if it is null
     *
     * @param id the id of the day to save.
     * @param day the day to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated day,
     * or with status {@code 400 (Bad Request)} if the day is not valid,
     * or with status {@code 404 (Not Found)} if the day is not found,
     * or with status {@code 500 (Internal Server Error)} if the day couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/days/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Day> partialUpdateDay(@PathVariable(value = "id", required = false) final Long id, @NotNull @RequestBody Day day)
        throws URISyntaxException {
        log.debug("REST request to partial update Day partially : {}, {}", id, day);
        if (day.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, day.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!dayRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        Optional<Day> result = dayService.partialUpdate(day);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, day.getId().toString())
        );
    }

    /**
     * {@code GET  /days} : get all the days.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of days in body.
     */
    @GetMapping("/days")
    public ResponseEntity<List<Day>> getAllDays(DayCriteria criteria) {
        log.debug("REST request to get Days by criteria: {}", criteria);
        List<Day> entityList = dayQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /days/count} : count all the days.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/days/count")
    public ResponseEntity<Long> countDays(DayCriteria criteria) {
        log.debug("REST request to count Days by criteria: {}", criteria);
        return ResponseEntity.ok().body(dayQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /days/:id} : get the "id" day.
     *
     * @param id the id of the day to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the day, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/days/{id}")
    public ResponseEntity<Day> getDay(@PathVariable Long id) {
        log.debug("REST request to get Day : {}", id);
        Optional<Day> day = dayService.findOne(id);
        return ResponseUtil.wrapOrNotFound(day);
    }

    /**
     * {@code DELETE  /days/:id} : delete the "id" day.
     *
     * @param id the id of the day to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/days/{id}")
    public ResponseEntity<Void> deleteDay(@PathVariable Long id) {
        log.debug("REST request to delete Day : {}", id);
        dayService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, id.toString()))
            .build();
    }
}
