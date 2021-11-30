package dev.flaviojunior.service.criteria;

import dev.flaviojunior.common.service.Criteria;
import dev.flaviojunior.common.service.filter.LocalDateFilter;
import dev.flaviojunior.common.service.filter.LongFilter;
import dev.flaviojunior.common.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

public class TeacherCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter photo;

    private LocalDateFilter hireDate;

    private LongFilter personId;

    private LongFilter subjectId;

    public TeacherCriteria() {}

    public TeacherCriteria(TeacherCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.photo = other.photo == null ? null : other.photo.copy();
        this.hireDate = other.hireDate == null ? null : other.hireDate.copy();
        this.personId = other.personId == null ? null : other.personId.copy();
        this.subjectId = other.subjectId == null ? null : other.subjectId.copy();
    }

    @Override
    public TeacherCriteria copy() {
        return new TeacherCriteria(this);
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

    public StringFilter getPhoto() {
        return photo;
    }

    public StringFilter photo() {
        if (photo == null) {
            photo = new StringFilter();
        }
        return photo;
    }

    public void setPhoto(StringFilter photo) {
        this.photo = photo;
    }

    public LocalDateFilter getHireDate() {
        return hireDate;
    }

    public LocalDateFilter hireDate() {
        if (hireDate == null) {
            hireDate = new LocalDateFilter();
        }
        return hireDate;
    }

    public void setHireDate(LocalDateFilter hireDate) {
        this.hireDate = hireDate;
    }

    public LongFilter getPersonId() {
        return personId;
    }

    public LongFilter personId() {
        if (personId == null) {
            personId = new LongFilter();
        }
        return personId;
    }

    public void setPersonId(LongFilter personId) {
        this.personId = personId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TeacherCriteria that = (TeacherCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(photo, that.photo) &&
            Objects.equals(hireDate, that.hireDate) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(subjectId, that.subjectId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photo, hireDate, personId, subjectId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TeacherCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (photo != null ? "photo=" + photo + ", " : "") +
            (hireDate != null ? "hireDate=" + hireDate + ", " : "") +
            (personId != null ? "personId=" + personId + ", " : "") +
            (subjectId != null ? "subjectId=" + subjectId + ", " : "") +
            "}";
    }
}
