package dev.flaviojunior.repository;

import dev.flaviojunior.domain.Timetable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Timetable entity.
 */
@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long>, JpaSpecificationExecutor<Timetable> {
    @Query(
        value = "select distinct timetable from Timetable timetable left join fetch timetable.days",
        countQuery = "select count(distinct timetable) from Timetable timetable"
    )
    Page<Timetable> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct timetable from Timetable timetable left join fetch timetable.days")
    List<Timetable> findAllWithEagerRelationships();

    @Query("select timetable from Timetable timetable left join fetch timetable.days where timetable.id =:id")
    Optional<Timetable> findOneWithEagerRelationships(@Param("id") Long id);
}
