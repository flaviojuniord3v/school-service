package dev.flaviojunior.service;

import dev.flaviojunior.domain.Section;
import dev.flaviojunior.repository.SectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Section}.
 */
@Service
@Transactional
public class SectionService {

    private final Logger log = LoggerFactory.getLogger(SectionService.class);

    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    /**
     * Save a section.
     *
     * @param section the entity to save.
     * @return the persisted entity.
     */
    public Section save(Section section) {
        log.debug("Request to save Section : {}", section);
        return sectionRepository.save(section);
    }

    /**
     * Partially update a section.
     *
     * @param section the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Section> partialUpdate(Section section) {
        log.debug("Request to partially update Section : {}", section);

        return sectionRepository
            .findById(section.getId())
            .map(
                existingSection -> {
                    if (section.getName() != null) {
                        existingSection.setName(section.getName());
                    }

                    return existingSection;
                }
            )
            .map(sectionRepository::save);
    }

    /**
     * Get all the sections.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Section> findAll() {
        log.debug("Request to get all Sections");
        return sectionRepository.findAll();
    }

    /**
     * Get one section by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Section> findOne(Long id) {
        log.debug("Request to get Section : {}", id);
        return sectionRepository.findById(id);
    }

    /**
     * Delete the section by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Section : {}", id);
        sectionRepository.deleteById(id);
    }
}
