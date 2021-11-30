package dev.flaviojunior.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the IssuedBooks entity.
 */
public class IssuedBooksDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer registrationNumber;

    @NotNull
    private String isbn;

    @NotNull
    private Instant issueDate;

    @NotNull
    private Instant returnDate;

    private BookDTO book;

    private StudentDTO student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Instant getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Instant issueDate) {
        this.issueDate = issueDate;
    }

    public Instant getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IssuedBooksDTO)) {
            return false;
        }

        IssuedBooksDTO issuedBooksDTO = (IssuedBooksDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, issuedBooksDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IssuedBooksDTO{" +
            "id=" + getId() +
            ", registrationNumber=" + getRegistrationNumber() +
            ", isbn='" + getIsbn() + "'" +
            ", issueDate='" + getIssueDate() + "'" +
            ", returnDate='" + getReturnDate() + "'" +
            "}";
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }
}
