package dev.flaviojunior.service;

import dev.flaviojunior.domain.Enrollment;
import dev.flaviojunior.repository.EnrollmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Enrollment}.
 */
@Service
@Transactional
public class EnrollmentService {

    private final Logger log = LoggerFactory.getLogger(EnrollmentService.class);

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    /**
     * Save a enrollment.
     *
     * @param enrollment the entity to save.
     * @return the persisted entity.
     */
    public Enrollment save(Enrollment enrollment) {
        log.debug("Request to save Enrollment : {}", enrollment);
        return enrollmentRepository.save(enrollment);
    }

    /**
     * Partially update a enrollment.
     *
     * @param enrollment the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Enrollment> partialUpdate(Enrollment enrollment) {
        log.debug("Request to partially update Enrollment : {}", enrollment);

        return enrollmentRepository
            .findById(enrollment.getId())
            .map(
                existingEnrollment -> {
                    if (enrollment.getRollNumber() != null) {
                        existingEnrollment.setRollNumber(enrollment.getRollNumber());
                    }
                    if (enrollment.getEnrollmentDate() != null) {
                        existingEnrollment.setEnrollmentDate(enrollment.getEnrollmentDate());
                    }
                    if (enrollment.getSchoolYear() != null) {
                        existingEnrollment.setSchoolYear(enrollment.getSchoolYear());
                    }
                    if (enrollment.getStudentId() != null) {
                        existingEnrollment.setStudentId(enrollment.getStudentId());
                    }

                    return existingEnrollment;
                }
            )
            .map(enrollmentRepository::save);
    }

    /**
     * Get all the enrollments.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Enrollment> findAll() {
        log.debug("Request to get all Enrollments");
        return enrollmentRepository.findAll();
    }

    /**
     * Get one enrollment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Enrollment> findOne(Long id) {
        log.debug("Request to get Enrollment : {}", id);
        return enrollmentRepository.findById(id);
    }

    /**
     * Delete the enrollment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Enrollment : {}", id);
        enrollmentRepository.deleteById(id);
    }
}
