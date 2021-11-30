package dev.flaviojunior.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

/**
 * IssuedBooks.
 */
@Entity
@Table(name = "issued_books")
public class IssuedBooks implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorIssuedBooks")
    @SequenceGenerator(name = "sequenceGeneratorIssuedBooks", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "registration_number", nullable = false)
    private Integer registrationNumber;

    @NotNull
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @NotNull
    @Column(name = "issue_date", nullable = false)
    private Instant issueDate;

    @NotNull
    @Column(name = "return_date", nullable = false)
    private Instant returnDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IssuedBooks id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getRegistrationNumber() {
        return this.registrationNumber;
    }

    public IssuedBooks registrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public IssuedBooks isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Instant getIssueDate() {
        return this.issueDate;
    }

    public IssuedBooks issueDate(Instant issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public void setIssueDate(Instant issueDate) {
        this.issueDate = issueDate;
    }

    public Instant getReturnDate() {
        return this.returnDate;
    }

    public IssuedBooks returnDate(Instant returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IssuedBooks)) {
            return false;
        }
        return id != null && id.equals(((IssuedBooks) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IssuedBooks{" +
            "id=" + getId() +
            ", registrationNumber=" + getRegistrationNumber() +
            ", isbn='" + getIsbn() + "'" +
            ", issueDate='" + getIssueDate() + "'" +
            ", returnDate='" + getReturnDate() + "'" +
            "}";
    }
}
