package dev.flaviojunior.service;

import dev.flaviojunior.common.service.QueryService;
import dev.flaviojunior.domain.Period;
import dev.flaviojunior.domain.Period_;
import dev.flaviojunior.repository.PeriodRepository;
import dev.flaviojunior.service.criteria.PeriodCriteria;
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
public class PeriodQueryService extends QueryService<Period> {

    private final Logger log = LoggerFactory.getLogger(PeriodQueryService.class);

    private final PeriodRepository periodRepository;

    public PeriodQueryService(PeriodRepository periodRepository) {
        this.periodRepository = periodRepository;
    }

    @Transactional(readOnly = true)
    public List<Period> findByCriteria(PeriodCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Period> specification = createSpecification(criteria);
        return periodRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public Page<Period> findByCriteria(PeriodCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Period> specification = createSpecification(criteria);
        return periodRepository.findAll(specification, page);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(PeriodCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Period> specification = createSpecification(criteria);
        return periodRepository.count(specification);
    }

    protected Specification<Period> createSpecification(PeriodCriteria criteria) {
        Specification<Period> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Period_.id));
            }
            if (criteria.getStartHour() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStartHour(), Period_.startHour));
            }
            if (criteria.getEndHour() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEndHour(), Period_.endHour));
            }
        }
        return specification;
    }
}
