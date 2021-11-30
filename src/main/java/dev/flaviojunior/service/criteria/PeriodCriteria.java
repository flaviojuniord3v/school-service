package dev.flaviojunior.service.criteria;

import dev.flaviojunior.common.service.Criteria;
import dev.flaviojunior.common.service.filter.LongFilter;
import dev.flaviojunior.common.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

public class PeriodCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter startHour;

    private StringFilter endHour;

    public PeriodCriteria() {}

    public PeriodCriteria(PeriodCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.startHour = other.startHour == null ? null : other.startHour.copy();
        this.endHour = other.endHour == null ? null : other.endHour.copy();
    }

    @Override
    public PeriodCriteria copy() {
        return new PeriodCriteria(this);
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

    public StringFilter getStartHour() {
        return startHour;
    }

    public StringFilter startHour() {
        if (startHour == null) {
            startHour = new StringFilter();
        }
        return startHour;
    }

    public void setStartHour(StringFilter startHour) {
        this.startHour = startHour;
    }

    public StringFilter getEndHour() {
        return endHour;
    }

    public StringFilter endHour() {
        if (endHour == null) {
            endHour = new StringFilter();
        }
        return endHour;
    }

    public void setEndHour(StringFilter endHour) {
        this.endHour = endHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PeriodCriteria that = (PeriodCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(startHour, that.startHour) && Objects.equals(endHour, that.endHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startHour, endHour);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PeriodCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (startHour != null ? "startHour=" + startHour + ", " : "") +
            (endHour != null ? "endHour=" + endHour + ", " : "") +
            "}";
    }
}
