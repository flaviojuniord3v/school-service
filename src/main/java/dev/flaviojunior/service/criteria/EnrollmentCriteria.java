package dev.flaviojunior.service.criteria;

import dev.flaviojunior.common.service.Criteria;
import dev.flaviojunior.common.service.filter.InstantFilter;
import dev.flaviojunior.common.service.filter.IntegerFilter;
import dev.flaviojunior.common.service.filter.LongFilter;

import java.io.Serializable;
import java.util.Objects;

public class EnrollmentCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter rollNumber;

    private InstantFilter enrollmentDate;

    private IntegerFilter schoolYear;

    private LongFilter studentId;

    public EnrollmentCriteria() {}

    public EnrollmentCriteria(EnrollmentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.rollNumber = other.rollNumber == null ? null : other.rollNumber.copy();
        this.enrollmentDate = other.enrollmentDate == null ? null : other.enrollmentDate.copy();
        this.schoolYear = other.schoolYear == null ? null : other.schoolYear.copy();
        this.studentId = other.studentId == null ? null : other.studentId.copy();
    }

    @Override
    public EnrollmentCriteria copy() {
        return new EnrollmentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRollNumber() {
        return rollNumber;
    }

    public IntegerFilter rollNumber() {
        if (rollNumber == null) {
            rollNumber = new IntegerFilter();
        }
        return rollNumber;
    }

    public void setRollNumber(IntegerFilter rollNumber) {
        this.rollNumber = rollNumber;
    }

    public InstantFilter getEnrollmentDate() {
        return enrollmentDate;
    }

    public InstantFilter enrollmentDate() {
        if (enrollmentDate == null) {
            enrollmentDate = new InstantFilter();
        }
        return enrollmentDate;
    }

    public void setEnrollmentDate(InstantFilter enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public IntegerFilter getSchoolYear() {
        return schoolYear;
    }

    public IntegerFilter schoolYear() {
        if (schoolYear == null) {
            schoolYear = new IntegerFilter();
        }
        return schoolYear;
    }

    public void setSchoolYear(IntegerFilter schoolYear) {
        this.schoolYear = schoolYear;
    }

    public LongFilter getStudentId() {
        return studentId;
    }

    public LongFilter studentId() {
        if (studentId == null) {
            studentId = new LongFilter();
        }
        return studentId;
    }

    public void setStudentId(LongFilter studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EnrollmentCriteria that = (EnrollmentCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(rollNumber, that.rollNumber) &&
            Objects.equals(enrollmentDate, that.enrollmentDate) &&
            Objects.equals(schoolYear, that.schoolYear) &&
            Objects.equals(studentId, that.studentId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rollNumber, enrollmentDate, schoolYear, studentId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EnrollmentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (rollNumber != null ? "rollNumber=" + rollNumber + ", " : "") +
            (enrollmentDate != null ? "enrollmentDate=" + enrollmentDate + ", " : "") +
            (schoolYear != null ? "schoolYear=" + schoolYear + ", " : "") +
            (studentId != null ? "studentId=" + studentId + ", " : "") +
            "}";
    }
}
