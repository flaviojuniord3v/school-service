package dev.flaviojunior.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Period.
 */
@Entity
@Table(name = "period")
public class Period implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorPeriod")
    @SequenceGenerator(name = "sequenceGeneratorPeriod", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "start_hour", nullable = false)
    private String startHour;

    @NotNull
    @Column(name = "end_hour", nullable = false)
    private String endHour;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Period id(Long id) {
        this.id = id;
        return this;
    }

    public String getStartHour() {
        return this.startHour;
    }

    public Period startHour(String startHour) {
        this.startHour = startHour;
        return this;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return this.endHour;
    }

    public Period endHour(String endHour) {
        this.endHour = endHour;
        return this;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Period)) {
            return false;
        }
        return id != null && id.equals(((Period) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Period{" +
            "id=" + getId() +
            ", startHour='" + getStartHour() + "'" +
            ", endHour='" + getEndHour() + "'" +
            "}";
    }
}
