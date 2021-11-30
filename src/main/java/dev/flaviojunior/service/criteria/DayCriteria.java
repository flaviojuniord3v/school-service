package dev.flaviojunior.service.criteria;

import dev.flaviojunior.common.service.Criteria;
import dev.flaviojunior.common.service.filter.LongFilter;
import dev.flaviojunior.common.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

public class DayCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter number;

    private LongFilter studentClassesId;

    public DayCriteria() {}

    public DayCriteria(DayCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.number = other.number == null ? null : other.number.copy();
        this.studentClassesId = other.studentClassesId == null ? null : other.studentClassesId.copy();
    }

    @Override
    public DayCriteria copy() {
        return new DayCriteria(this);
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

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getNumber() {
        return number;
    }

    public StringFilter number() {
        if (number == null) {
            number = new StringFilter();
        }
        return number;
    }

    public void setNumber(StringFilter number) {
        this.number = number;
    }

    public LongFilter getStudentClassesId() {
        return studentClassesId;
    }

    public LongFilter studentClassesId() {
        if (studentClassesId == null) {
            studentClassesId = new LongFilter();
        }
        return studentClassesId;
    }

    public void setStudentClassesId(LongFilter studentClassesId) {
        this.studentClassesId = studentClassesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DayCriteria that = (DayCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(number, that.number) &&
            Objects.equals(studentClassesId, that.studentClassesId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number, studentClassesId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DayCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (number != null ? "number=" + number + ", " : "") +
            (studentClassesId != null ? "studentClassesId=" + studentClassesId + ", " : "") +
            "}";
    }
}
