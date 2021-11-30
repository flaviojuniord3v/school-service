package dev.flaviojunior.service;

import dev.flaviojunior.domain.Period;
import dev.flaviojunior.repository.PeriodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Period}.
 */
@Service
@Transactional
public class PeriodService {

    private final Logger log = LoggerFactory.getLogger(PeriodService.class);

    private final PeriodRepository periodRepository;

    public PeriodService(PeriodRepository periodRepository) {
        this.periodRepository = periodRepository;
    }

    /**
     * Save a period.
     *
     * @param period the entity to save.
     * @return the persisted entity.
     */
    public Period save(Period period) {
        log.debug("Request to save Period : {}", period);
        return periodRepository.save(period);
    }

    /**
     * Partially update a period.
     *
     * @param period the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Period> partialUpdate(Period period) {
        log.debug("Request to partially update Period : {}", period);

        return periodRepository
            .findById(period.getId())
            .map(
                existingPeriod -> {
                    if (period.getStartHour() != null) {
                        existingPeriod.setStartHour(period.getStartHour());
                    }
                    if (period.getEndHour() != null) {
                        existingPeriod.setEndHour(period.getEndHour());
                    }

                    return existingPeriod;
                }
            )
            .map(periodRepository::save);
    }

    /**
     * Get all the periods.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Period> findAll() {
        log.debug("Request to get all Periods");
        return periodRepository.findAll();
    }

    /**
     * Get one period by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Period> findOne(Long id) {
        log.debug("Request to get Period : {}", id);
        return periodRepository.findById(id);
    }

    /**
     * Delete the period by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Period : {}", id);
        periodRepository.deleteById(id);
    }
}
