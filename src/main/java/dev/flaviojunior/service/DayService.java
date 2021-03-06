package dev.flaviojunior.service;

import dev.flaviojunior.domain.Day;
import dev.flaviojunior.repository.DayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Day}.
 */
@Service
@Transactional
public class DayService {

    private final Logger log = LoggerFactory.getLogger(DayService.class);

    private final DayRepository dayRepository;

    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    /**
     * Save a day.
     *
     * @param day the entity to save.
     * @return the persisted entity.
     */
    public Day save(Day day) {
        log.debug("Request to save Day : {}", day);
        return dayRepository.save(day);
    }

    /**
     * Partially update a day.
     *
     * @param day the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Day> partialUpdate(Day day) {
        log.debug("Request to partially update Day : {}", day);

        return dayRepository
            .findById(day.getId())
            .map(
                existingDay -> {
                    if (day.getName() != null) {
                        existingDay.setName(day.getName());
                    }
                    if (day.getNumber() != null) {
                        existingDay.setNumber(day.getNumber());
                    }

                    return existingDay;
                }
            )
            .map(dayRepository::save);
    }

    /**
     * Get all the days.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Day> findAll() {
        log.debug("Request to get all Days");
        return dayRepository.findAll();
    }

    /**
     * Get one day by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Day> findOne(Long id) {
        log.debug("Request to get Day : {}", id);
        return dayRepository.findById(id);
    }

    /**
     * Delete the day by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Day : {}", id);
        dayRepository.deleteById(id);
    }
}
