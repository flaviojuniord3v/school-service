package dev.flaviojunior.repository;

import dev.flaviojunior.domain.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Classes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long>, JpaSpecificationExecutor<Classes> {}
