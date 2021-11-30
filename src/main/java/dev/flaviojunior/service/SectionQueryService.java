package dev.flaviojunior.service;

import dev.flaviojunior.common.service.QueryService;
import dev.flaviojunior.domain.Section;
import dev.flaviojunior.domain.Section_;
import dev.flaviojunior.repository.SectionRepository;
import dev.flaviojunior.service.criteria.SectionCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SectionQueryService extends QueryService<Section> {

    private final Logger log = LoggerFactory.getLogger(SectionQueryService.class);

    private final SectionRepository sectionRepository;

    public SectionQueryService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Transactional(readOnly = true)
    public List<Section> findByCriteria(SectionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Section> specification = createSpecification(criteria);
        return sectionRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public Page<Section> findByCriteria(SectionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Section> specification = createSpecification(criteria);
        return sectionRepository.findAll(specification, page);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(SectionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Section> specification = createSpecification(criteria);
        return sectionRepository.count(specification);
    }

    protected Specification<Section> createSpecification(SectionCriteria criteria) {
        Specification<Section> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Section_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Section_.name));
            }
        }
        return specification;
    }
}
