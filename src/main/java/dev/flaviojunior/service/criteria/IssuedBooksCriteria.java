package dev.flaviojunior.service.criteria;

import dev.flaviojunior.common.service.Criteria;
import dev.flaviojunior.common.service.filter.InstantFilter;
import dev.flaviojunior.common.service.filter.IntegerFilter;
import dev.flaviojunior.common.service.filter.LongFilter;
import dev.flaviojunior.common.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

public class IssuedBooksCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter registrationNumber;

    private StringFilter isbn;

    private InstantFilter issueDate;

    private InstantFilter returnDate;

    public IssuedBooksCriteria() {}

    public IssuedBooksCriteria(IssuedBooksCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.registrationNumber = other.registrationNumber == null ? null : other.registrationNumber.copy();
        this.isbn = other.isbn == null ? null : other.isbn.copy();
        this.issueDate = other.issueDate == null ? null : other.issueDate.copy();
        this.returnDate = other.returnDate == null ? null : other.returnDate.copy();
    }

    @Override
    public IssuedBooksCriteria copy() {
        return new IssuedBooksCriteria(this);
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

    public IntegerFilter getRegistrationNumber() {
        return registrationNumber;
    }

    public IntegerFilter registrationNumber() {
        if (registrationNumber == null) {
            registrationNumber = new IntegerFilter();
        }
        return registrationNumber;
    }

    public void setRegistrationNumber(IntegerFilter registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public StringFilter getIsbn() {
        return isbn;
    }

    public StringFilter isbn() {
        if (isbn == null) {
            isbn = new StringFilter();
        }
        return isbn;
    }

    public void setIsbn(StringFilter isbn) {
        this.isbn = isbn;
    }

    public InstantFilter getIssueDate() {
        return issueDate;
    }

    public InstantFilter issueDate() {
        if (issueDate == null) {
            issueDate = new InstantFilter();
        }
        return issueDate;
    }

    public void setIssueDate(InstantFilter issueDate) {
        this.issueDate = issueDate;
    }

    public InstantFilter getReturnDate() {
        return returnDate;
    }

    public InstantFilter returnDate() {
        if (returnDate == null) {
            returnDate = new InstantFilter();
        }
        return returnDate;
    }

    public void setReturnDate(InstantFilter returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final IssuedBooksCriteria that = (IssuedBooksCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(registrationNumber, that.registrationNumber) &&
            Objects.equals(isbn, that.isbn) &&
            Objects.equals(issueDate, that.issueDate) &&
            Objects.equals(returnDate, that.returnDate)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationNumber, isbn, issueDate, returnDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IssuedBooksCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (registrationNumber != null ? "registrationNumber=" + registrationNumber + ", " : "") +
            (isbn != null ? "isbn=" + isbn + ", " : "") +
            (issueDate != null ? "issueDate=" + issueDate + ", " : "") +
            (returnDate != null ? "returnDate=" + returnDate + ", " : "") +
            "}";
    }
}
