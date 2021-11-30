package dev.flaviojunior.service.criteria;

import dev.flaviojunior.common.service.Criteria;
import dev.flaviojunior.common.service.filter.LongFilter;
import dev.flaviojunior.common.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

public class ClassesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    public ClassesCriteria() {}

    public ClassesCriteria(ClassesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
    }

    @Override
    public ClassesCriteria copy() {
        return new ClassesCriteria(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClassesCriteria that = (ClassesCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClassesCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            "}";
    }
}
