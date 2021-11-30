package dev.flaviojunior.service;

import dev.flaviojunior.common.service.QueryService;
import dev.flaviojunior.domain.Day;
import dev.flaviojunior.domain.Day_;
import dev.flaviojunior.domain.Timetable_;
import dev.flaviojunior.repository.DayRepository;
import dev.flaviojunior.service.criteria.DayCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DayQueryService extends QueryService<Day> {

    private final Logger log = LoggerFactory.getLogger(DayQueryService.class);

    private final DayRepository dayRepository;

    public DayQueryService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @Transactional(readOnly = true)
    public List<Day> findByCriteria(DayCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Day> specification = createSpecification(criteria);
        return dayRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public Page<Day> findByCriteria(DayCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Day> specification = createSpecification(criteria);
        return dayRepository.findAll(specification, page);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(DayCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Day> specification = createSpecification(criteria);
        return dayRepository.count(specification);
    }

    protected Specification<Day> createSpecification(DayCriteria criteria) {
        Specification<Day> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Day_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Day_.name));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumber(), Day_.number));
            }
            if (criteria.getStudentClassesId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getStudentClassesId(),
                            root -> root.join(Day_.studentClasses, JoinType.LEFT).get(Timetable_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
