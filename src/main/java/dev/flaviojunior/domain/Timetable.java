package dev.flaviojunior.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Timetable.
 */
@Entity
@Table(name = "timetable")
public class Timetable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorTimetable")
    @SequenceGenerator(name = "sequenceGeneratorTimetable", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;

    @ManyToOne
    private Classes classes;

    @ManyToOne
    private Enrollment student;

    @ManyToOne
    @JsonIgnoreProperties(value = { "person", "subjects" }, allowSetters = true)
    private Teacher teacher;

    @ManyToOne
    private Section section;

    @ManyToOne
    @JsonIgnoreProperties(value = { "teachers" }, allowSetters = true)
    private Subject subject;

    @ManyToOne
    private Period period;

    @ManyToMany
    @JoinTable(
        name = "rel_timetable__day",
        joinColumns = @JoinColumn(name = "timetable_id"),
        inverseJoinColumns = @JoinColumn(name = "day_id")
    )
    @JsonIgnoreProperties(value = { "studentClasses" }, allowSetters = true)
    private Set<Day> days = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timetable id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getRoomNumber() {
        return this.roomNumber;
    }

    public Timetable roomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Classes getClasses() {
        return this.classes;
    }

    public Timetable classes(Classes classes) {
        this.setClasses(classes);
        return this;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Enrollment getStudent() {
        return this.student;
    }

    public Timetable student(Enrollment enrollment) {
        this.setStudent(enrollment);
        return this;
    }

    public void setStudent(Enrollment enrollment) {
        this.student = enrollment;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public Timetable teacher(Teacher teacher) {
        this.setTeacher(teacher);
        return this;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Section getSection() {
        return this.section;
    }

    public Timetable section(Section section) {
        this.setSection(section);
        return this;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public Timetable subject(Subject subject) {
        this.setSubject(subject);
        return this;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Period getPeriod() {
        return this.period;
    }

    public Timetable period(Period period) {
        this.setPeriod(period);
        return this;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Set<Day> getDays() {
        return this.days;
    }

    public Timetable days(Set<Day> days) {
        this.setDays(days);
        return this;
    }

    public Timetable addDay(Day day) {
        this.days.add(day);
        day.getStudentClasses().add(this);
        return this;
    }

    public Timetable removeDay(Day day) {
        this.days.remove(day);
        day.getStudentClasses().remove(this);
        return this;
    }

    public void setDays(Set<Day> days) {
        this.days = days;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Timetable)) {
            return false;
        }
        return id != null && id.equals(((Timetable) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Timetable{" +
            "id=" + getId() +
            ", roomNumber=" + getRoomNumber() +
            "}";
    }
}
