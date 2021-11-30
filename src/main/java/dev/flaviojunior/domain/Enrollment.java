package dev.flaviojunior.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

/**
 * Enrollment.
 */
@Entity
@Table(name = "enrollment")
public class Enrollment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorEnrollment")
    @SequenceGenerator(name = "sequenceGeneratorEnrollment", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "roll_number", nullable = false)
    private Integer rollNumber;

    @NotNull
    @Column(name = "enrollment_date", nullable = false)
    private Instant enrollmentDate;

    @NotNull
    @Column(name = "school_year", nullable = false)
    private Integer schoolYear;

    @NotNull
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enrollment id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getRollNumber() {
        return this.rollNumber;
    }

    public Enrollment rollNumber(Integer rollNumber) {
        this.rollNumber = rollNumber;
        return this;
    }

    public void setRollNumber(Integer rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Instant getEnrollmentDate() {
        return this.enrollmentDate;
    }

    public Enrollment enrollmentDate(Instant enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
        return this;
    }

    public void setEnrollmentDate(Instant enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Integer getSchoolYear() {
        return this.schoolYear;
    }

    public Enrollment schoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public Enrollment studentId(Long studentId) {
        this.studentId = studentId;
        return this;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Enrollment)) {
            return false;
        }
        return id != null && id.equals(((Enrollment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Enrollment{" +
            "id=" + getId() +
            ", rollNumber=" + getRollNumber() +
            ", enrollmentDate='" + getEnrollmentDate() + "'" +
            ", schoolYear=" + getSchoolYear() +
            ", studentId=" + getStudentId() +
            "}";
    }
}
