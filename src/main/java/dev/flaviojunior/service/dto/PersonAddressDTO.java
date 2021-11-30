package dev.flaviojunior.service.dto;

import dev.flaviojunior.domain.enumeration.TypeOfAddress;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PersonAddress entity.
 */
public class PersonAddressDTO implements Serializable {

    private Long id;

    @NotNull
    private TypeOfAddress type;

    private AddressDTO address;

    private PersonDTO person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOfAddress getType() {
        return type;
    }

    public void setType(TypeOfAddress type) {
        this.type = type;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
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
        if (!(o instanceof PersonAddressDTO)) {
            return false;
        }

        PersonAddressDTO personAddressDTO = (PersonAddressDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, personAddressDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonAddressDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", address=" + getAddress() +
            ", person=" + getPerson() +
            "}";
    }
}
