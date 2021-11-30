package dev.flaviojunior.service.dto;

import dev.flaviojunior.domain.enumeration.TypeOfRelationship;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the StudentRelationship entity.
 */
public class StudentRelationshipDTO implements Serializable {

    private Long id;

    @NotNull
    private TypeOfRelationship type;

    private StudentDTO student;

    private PersonDTO person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOfRelationship getType() {
        return type;
    }

    public void setType(TypeOfRelationship type) {
        this.type = type;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentRelationshipDTO)) {
            return false;
        }

        StudentRelationshipDTO studentRelationshipDTO = (StudentRelationshipDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, studentRelationshipDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StudentRelationshipDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", student=" + getStudent() +
            ", person=" + getPerson() +
            "}";
    }
}
