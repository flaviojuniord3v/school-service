package dev.flaviojunior.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Day.
 */
@Entity
@Table(name = "day")
public class Day implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorDay")
    @SequenceGenerator(name = "sequenceGeneratorDay", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "number", nullable = false)
    private String number;

    @ManyToMany(mappedBy = "days")
    @JsonIgnoreProperties(value = { "classes", "student", "teacher", "section", "subject", "period", "days" }, allowSetters = true)
    private Set<Timetable> studentClasses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Day id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Day name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.number;
    }

    public Day number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<Timetable> getStudentClasses() {
        return this.studentClasses;
    }

    public Day studentClasses(Set<Timetable> timetables) {
        this.setStudentClasses(timetables);
        return this;
    }

    public Day addStudentClasses(Timetable timetable) {
        this.studentClasses.add(timetable);
        timetable.getDays().add(this);
        return this;
    }

    public Day removeStudentClasses(Timetable timetable) {
        this.studentClasses.remove(timetable);
        timetable.getDays().remove(this);
        return this;
    }

    public void setStudentClasses(Set<Timetable> timetables) {
        if (this.studentClasses != null) {
            this.studentClasses.forEach(i -> i.removeDay(this));
        }
        if (timetables != null) {
            timetables.forEach(i -> i.addDay(this));
        }
        this.studentClasses = timetables;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Day)) {
            return false;
        }
        return id != null && id.equals(((Day) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Day{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", number='" + getNumber() + "'" +
            "}";
    }
}
