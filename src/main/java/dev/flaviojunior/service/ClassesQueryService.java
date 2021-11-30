package dev.flaviojunior.service;

import dev.flaviojunior.common.service.QueryService;
import dev.flaviojunior.domain.Classes;
import dev.flaviojunior.domain.Classes_;
import dev.flaviojunior.repository.ClassesRepository;
import dev.flaviojunior.service.criteria.ClassesCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClassesQueryService extends QueryService<Classes> {

    private final Logger log = LoggerFactory.getLogger(ClassesQueryService.class);

    private final ClassesRepository classesRepository;

    public ClassesQueryService(ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }

    @Transactional(readOnly = true)
    public List<Classes> findByCriteria(ClassesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Classes> specification = createSpecification(criteria);
        return classesRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public Page<Classes> findByCriteria(ClassesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Classes> specification = createSpecification(criteria);
        return classesRepository.findAll(specification, page);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(ClassesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Classes> specification = createSpecification(criteria);
        return classesRepository.count(specification);
    }

    protected Specification<Classes> createSpecification(ClassesCriteria criteria) {
        Specification<Classes> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Classes_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Classes_.name));
            }
        }
        return specification;
    }
}
