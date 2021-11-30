package dev.flaviojunior.service.criteria;

import dev.flaviojunior.common.service.Criteria;
import dev.flaviojunior.common.service.filter.IntegerFilter;
import dev.flaviojunior.common.service.filter.LongFilter;

import java.io.Serializable;
import java.util.Objects;

public class TimetableCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter roomNumber;

    private LongFilter classesId;

    private LongFilter studentId;

    private LongFilter teacherId;

    private LongFilter sectionId;

    private LongFilter subjectId;

    private LongFilter periodId;

    private LongFilter dayId;

    public TimetableCriteria() {}

    public TimetableCriteria(TimetableCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.roomNumber = other.roomNumber == null ? null : other.roomNumber.copy();
        this.classesId = other.classesId == null ? null : other.classesId.copy();
        this.studentId = other.studentId == null ? null : other.studentId.copy();
        this.teacherId = other.teacherId == null ? null : other.teacherId.copy();
        this.sectionId = other.sectionId == null ? null : other.sectionId.copy();
        this.subjectId = other.subjectId == null ? null : other.subjectId.copy();
        this.periodId = other.periodId == null ? null : other.periodId.copy();
        this.dayId = other.dayId == null ? null : other.dayId.copy();
    }

    @Override
    public TimetableCriteria copy() {
        return new TimetableCriteria(this);
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

    public IntegerFilter getRoomNumber() {
        return roomNumber;
    }

    public IntegerFilter roomNumber() {
        if (roomNumber == null) {
            roomNumber = new IntegerFilter();
        }
        return roomNumber;
    }

    public void setRoomNumber(IntegerFilter roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LongFilter getClassesId() {
        return classesId;
    }

    public LongFilter classesId() {
        if (classesId == null) {
            classesId = new LongFilter();
        }
        return classesId;
    }

    public void setClassesId(LongFilter classesId) {
        this.classesId = classesId;
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

    public LongFilter getTeacherId() {
        return teacherId;
    }

    public LongFilter teacherId() {
        if (teacherId == null) {
            teacherId = new LongFilter();
        }
        return teacherId;
    }

    public void setTeacherId(LongFilter teacherId) {
        this.teacherId = teacherId;
    }

    public LongFilter getSectionId() {
        return sectionId;
    }

    public LongFilter sectionId() {
        if (sectionId == null) {
            sectionId = new LongFilter();
        }
        return sectionId;
    }

    public void setSectionId(LongFilter sectionId) {
        this.sectionId = sectionId;
    }

    public LongFilter getSubjectId() {
        return subjectId;
    }

    public LongFilter subjectId() {
        if (subjectId == null) {
            subjectId = new LongFilter();
        }
        return subjectId;
    }

    public void setSubjectId(LongFilter subjectId) {
        this.subjectId = subjectId;
    }

    public LongFilter getPeriodId() {
        return periodId;
    }

    public LongFilter periodId() {
        if (periodId == null) {
            periodId = new LongFilter();
        }
        return periodId;
    }

    public void setPeriodId(LongFilter periodId) {
        this.periodId = periodId;
    }

    public LongFilter getDayId() {
        return dayId;
    }

    public LongFilter dayId() {
        if (dayId == null) {
            dayId = new LongFilter();
        }
        return dayId;
    }

    public void setDayId(LongFilter dayId) {
        this.dayId = dayId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TimetableCriteria that = (TimetableCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(roomNumber, that.roomNumber) &&
            Objects.equals(classesId, that.classesId) &&
            Objects.equals(studentId, that.studentId) &&
            Objects.equals(teacherId, that.teacherId) &&
            Objects.equals(sectionId, that.sectionId) &&
            Objects.equals(subjectId, that.subjectId) &&
            Objects.equals(periodId, that.periodId) &&
            Objects.equals(dayId, that.dayId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomNumber, classesId, studentId, teacherId, sectionId, subjectId, periodId, dayId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TimetableCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (roomNumber != null ? "roomNumber=" + roomNumber + ", " : "") +
            (classesId != null ? "classesId=" + classesId + ", " : "") +
            (studentId != null ? "studentId=" + studentId + ", " : "") +
            (teacherId != null ? "teacherId=" + teacherId + ", " : "") +
            (sectionId != null ? "sectionId=" + sectionId + ", " : "") +
            (subjectId != null ? "subjectId=" + subjectId + ", " : "") +
            (periodId != null ? "periodId=" + periodId + ", " : "") +
            (dayId != null ? "dayId=" + dayId + ", " : "") +
            "}";
    }
}
