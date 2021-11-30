package dev.flaviojunior.web.rest;

import dev.flaviojunior.common.web.util.HeaderUtil;
import dev.flaviojunior.common.web.util.ResponseUtil;
import dev.flaviojunior.domain.Period;
import dev.flaviojunior.repository.PeriodRepository;
import dev.flaviojunior.service.PeriodQueryService;
import dev.flaviojunior.service.PeriodService;
import dev.flaviojunior.service.criteria.PeriodCriteria;
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
 * REST controller for managing {@link Period}.
 */
@RestController
@RequestMapping("/api")
public class PeriodResource {

    private final Logger log = LoggerFactory.getLogger(PeriodResource.class);

    private static final String ENTITY_NAME = "period";

    @Value("${spring.application.name}")
    private String applicationName;

    private final PeriodService periodService;

    private final PeriodRepository periodRepository;

    private final PeriodQueryService periodQueryService;

    public PeriodResource(PeriodService periodService, PeriodRepository periodRepository, PeriodQueryService periodQueryService) {
        this.periodService = periodService;
        this.periodRepository = periodRepository;
        this.periodQueryService = periodQueryService;
    }

    /**
     * {@code POST  /periods} : Creates a new period.
     *
     * @param period A period to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new period, or with status {@code 400 (Bad Request)} if the period has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/periods")
    public ResponseEntity<Period> createPeriod(@Valid @RequestBody Period period) throws URISyntaxException {
        log.debug("REST request to save Period : {}", period);
        if (period.getId() != null) {
            throw new BadRequestAlertException("A new period cannot already have an ID", ENTITY_NAME);
        }
        Period result = periodService.save(period);
        return ResponseEntity
            .created(new URI("/api/periods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /periods/:id} : Updates an existing period.
     *
     * @param id the id of the period to save.
     * @param period the period to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated period,
     * or with status {@code 400 (Bad Request)} if the period is not valid,
     * or with status {@code 500 (Internal Server Error)} if the period couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/periods/{id}")
    public ResponseEntity<Period> updatePeriod(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Period period
    ) throws URISyntaxException {
        log.debug("REST request to update Period : {}, {}", id, period);
        if (period.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, period.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!periodRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        Period result = periodService.save(period);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, period.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /periods/:id} : Partial updates given fields of an existing period, field will ignore if it is null
     *
     * @param id the id of the period to save.
     * @param period the period to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated period,
     * or with status {@code 400 (Bad Request)} if the period is not valid,
     * or with status {@code 404 (Not Found)} if the period is not found,
     * or with status {@code 500 (Internal Server Error)} if the period couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/periods/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Period> partialUpdatePeriod(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Period period
    ) throws URISyntaxException {
        log.debug("REST request to partial update Period partially : {}, {}", id, period);
        if (period.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, period.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!periodRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        Optional<Period> result = periodService.partialUpdate(period);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, period.getId().toString())
        );
    }

    /**
     * {@code GET  /periods} : get all the periods.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of periods in body.
     */
    @GetMapping("/periods")
    public ResponseEntity<List<Period>> getAllPeriods(PeriodCriteria criteria) {
        log.debug("REST request to get Periods by criteria: {}", criteria);
        List<Period> entityList = periodQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /periods/count} : count all the periods.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/periods/count")
    public ResponseEntity<Long> countPeriods(PeriodCriteria criteria) {
        log.debug("REST request to count Periods by criteria: {}", criteria);
        return ResponseEntity.ok().body(periodQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /periods/:id} : get the "id" period.
     *
     * @param id the id of the period to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the period, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/periods/{id}")
    public ResponseEntity<Period> getPeriod(@PathVariable Long id) {
        log.debug("REST request to get Period : {}", id);
        Optional<Period> period = periodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(period);
    }

    /**
     * {@code DELETE  /periods/:id} : delete the "id" period.
     *
     * @param id the id of the period to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/periods/{id}")
    public ResponseEntity<Void> deletePeriod(@PathVariable Long id) {
        log.debug("REST request to delete Period : {}", id);
        periodService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, id.toString()))
            .build();
    }
}
