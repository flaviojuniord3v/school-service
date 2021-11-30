package dev.flaviojunior.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Teacher.
 */
@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorTeacher")
    @SequenceGenerator(name = "sequenceGeneratorTeacher", allocationSize = 1)
    private Long id;

    @Column(name = "photo")
    private String photo;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @ManyToOne
    private Person person;

    @ManyToMany(mappedBy = "teachers")
    @JsonIgnoreProperties(value = { "teachers" }, allowSetters = true)
    private Set<Subject> subjects = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher id(Long id) {
        this.id = id;
        return this;
    }

    public String getPhoto() {
        return this.photo;
    }

    public Teacher photo(String photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getHireDate() {
        return this.hireDate;
    }

    public Teacher hireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Person getPerson() {
        return this.person;
    }

    public Teacher person(Person person) {
        this.setPerson(person);
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Subject> getSubjects() {
        return this.subjects;
    }

    public Teacher subjects(Set<Subject> subjects) {
        this.setSubjects(subjects);
        return this;
    }

    public Teacher addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.getTeachers().add(this);
        return this;
    }

    public Teacher removeSubject(Subject subject) {
        this.subjects.remove(subject);
        subject.getTeachers().remove(this);
        return this;
    }

    public void setSubjects(Set<Subject> subjects) {
        if (this.subjects != null) {
            this.subjects.forEach(i -> i.removeTeacher(this));
        }
        if (subjects != null) {
            subjects.forEach(i -> i.addTeacher(this));
        }
        this.subjects = subjects;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Teacher)) {
            return false;
        }
        return id != null && id.equals(((Teacher) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Teacher{" +
            "id=" + getId() +
            ", photo='" + getPhoto() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            "}";
    }
}
