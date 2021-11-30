package dev.flaviojunior.repository;

import dev.flaviojunior.domain.IssuedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the IssuedBooks entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IssuedBooksRepository extends JpaRepository<IssuedBooks, Long>, JpaSpecificationExecutor<IssuedBooks> {}
