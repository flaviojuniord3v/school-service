package dev.flaviojunior.service;

import dev.flaviojunior.domain.Timetable;
import dev.flaviojunior.repository.TimetableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Timetable}.
 */
@Service
@Transactional
public class TimetableService {

    private final Logger log = LoggerFactory.getLogger(TimetableService.class);

    private final TimetableRepository timetableRepository;

    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    /**
     * Save a timetable.
     *
     * @param timetable the entity to save.
     * @return the persisted entity.
     */
    public Timetable save(Timetable timetable) {
        log.debug("Request to save Timetable : {}", timetable);
        return timetableRepository.save(timetable);
    }

    /**
     * Partially update a timetable.
     *
     * @param timetable the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Timetable> partialUpdate(Timetable timetable) {
        log.debug("Request to partially update Timetable : {}", timetable);

        return timetableRepository
            .findById(timetable.getId())
            .map(
                existingTimetable -> {
                    if (timetable.getRoomNumber() != null) {
                        existingTimetable.setRoomNumber(timetable.getRoomNumber());
                    }

                    return existingTimetable;
                }
            )
            .map(timetableRepository::save);
    }

    /**
     * Get all the timetables.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Timetable> findAll() {
        log.debug("Request to get all Timetables");
        return timetableRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the timetables with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Timetable> findAllWithEagerRelationships(Pageable pageable) {
        return timetableRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one timetable by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Timetable> findOne(Long id) {
        log.debug("Request to get Timetable : {}", id);
        return timetableRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the timetable by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Timetable : {}", id);
        timetableRepository.deleteById(id);
    }
}
