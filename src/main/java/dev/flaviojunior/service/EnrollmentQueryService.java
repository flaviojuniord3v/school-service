package dev.flaviojunior.service;

import dev.flaviojunior.common.service.QueryService;
import dev.flaviojunior.domain.Enrollment;
import dev.flaviojunior.domain.Enrollment_;
import dev.flaviojunior.repository.EnrollmentRepository;
import dev.flaviojunior.service.criteria.EnrollmentCriteria;
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
public class EnrollmentQueryService extends QueryService<Enrollment> {

    private final Logger log = LoggerFactory.getLogger(EnrollmentQueryService.class);

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentQueryService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional(readOnly = true)
    public List<Enrollment> findByCriteria(EnrollmentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Enrollment> specification = createSpecification(criteria);
        return enrollmentRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public Page<Enrollment> findByCriteria(EnrollmentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Enrollment> specification = createSpecification(criteria);
        return enrollmentRepository.findAll(specification, page);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(EnrollmentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Enrollment> specification = createSpecification(criteria);
        return enrollmentRepository.count(specification);
    }

    protected Specification<Enrollment> createSpecification(EnrollmentCriteria criteria) {
        Specification<Enrollment> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Enrollment_.id));
            }
            if (criteria.getRollNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRollNumber(), Enrollment_.rollNumber));
            }
            if (criteria.getEnrollmentDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnrollmentDate(), Enrollment_.enrollmentDate));
            }
            if (criteria.getSchoolYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSchoolYear(), Enrollment_.schoolYear));
            }
            if (criteria.getStudentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStudentId(), Enrollment_.studentId));
            }
        }
        return specification;
    }
}
