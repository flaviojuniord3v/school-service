package dev.flaviojunior.service;

import dev.flaviojunior.common.service.QueryService;
import dev.flaviojunior.domain.*;
import dev.flaviojunior.repository.TimetableRepository;
import dev.flaviojunior.service.criteria.TimetableCriteria;
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
public class TimetableQueryService extends QueryService<Timetable> {

    private final Logger log = LoggerFactory.getLogger(TimetableQueryService.class);

    private final TimetableRepository timetableRepository;

    public TimetableQueryService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    @Transactional(readOnly = true)
    public List<Timetable> findByCriteria(TimetableCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Timetable> specification = createSpecification(criteria);
        return timetableRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public Page<Timetable> findByCriteria(TimetableCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Timetable> specification = createSpecification(criteria);
        return timetableRepository.findAll(specification, page);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(TimetableCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Timetable> specification = createSpecification(criteria);
        return timetableRepository.count(specification);
    }

    protected Specification<Timetable> createSpecification(TimetableCriteria criteria) {
        Specification<Timetable> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Timetable_.id));
            }
            if (criteria.getRoomNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRoomNumber(), Timetable_.roomNumber));
            }
            if (criteria.getClassesId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getClassesId(), root -> root.join(Timetable_.classes, JoinType.LEFT).get(Classes_.id))
                    );
            }
            if (criteria.getStudentId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getStudentId(),
                            root -> root.join(Timetable_.student, JoinType.LEFT).get(Enrollment_.id)
                        )
                    );
            }
            if (criteria.getTeacherId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getTeacherId(), root -> root.join(Timetable_.teacher, JoinType.LEFT).get(Teacher_.id))
                    );
            }
            if (criteria.getSectionId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getSectionId(), root -> root.join(Timetable_.section, JoinType.LEFT).get(Section_.id))
                    );
            }
            if (criteria.getSubjectId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getSubjectId(), root -> root.join(Timetable_.subject, JoinType.LEFT).get(Subject_.id))
                    );
            }
            if (criteria.getPeriodId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getPeriodId(), root -> root.join(Timetable_.period, JoinType.LEFT).get(Period_.id))
                    );
            }
            if (criteria.getDayId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getDayId(), root -> root.join(Timetable_.days, JoinType.LEFT).get(Day_.id))
                    );
            }
        }
        return specification;
    }
}
