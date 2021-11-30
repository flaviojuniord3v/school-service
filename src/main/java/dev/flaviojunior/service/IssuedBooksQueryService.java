package dev.flaviojunior.service;

import dev.flaviojunior.common.service.QueryService;
import dev.flaviojunior.domain.IssuedBooks;
import dev.flaviojunior.domain.IssuedBooks_;
import dev.flaviojunior.repository.IssuedBooksRepository;
import dev.flaviojunior.service.criteria.IssuedBooksCriteria;
import dev.flaviojunior.service.dto.IssuedBooksDTO;
import dev.flaviojunior.service.mapper.IssuedBooksMapper;
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
public class IssuedBooksQueryService extends QueryService<IssuedBooks> {

    private final Logger log = LoggerFactory.getLogger(IssuedBooksQueryService.class);

    private final IssuedBooksRepository issuedBooksRepository;

    private final IssuedBooksMapper issuedBooksMapper;

    public IssuedBooksQueryService(IssuedBooksRepository issuedBooksRepository, IssuedBooksMapper issuedBooksMapper) {
        this.issuedBooksRepository = issuedBooksRepository;
        this.issuedBooksMapper = issuedBooksMapper;
    }

    @Transactional(readOnly = true)
    public List<IssuedBooksDTO> findByCriteria(IssuedBooksCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<IssuedBooks> specification = createSpecification(criteria);
        return issuedBooksMapper.toDto(issuedBooksRepository.findAll(specification));
    }

    @Transactional(readOnly = true)
    public Page<IssuedBooksDTO> findByCriteria(IssuedBooksCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<IssuedBooks> specification = createSpecification(criteria);
        return issuedBooksRepository.findAll(specification, page).map(issuedBooksMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(IssuedBooksCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<IssuedBooks> specification = createSpecification(criteria);
        return issuedBooksRepository.count(specification);
    }

    protected Specification<IssuedBooks> createSpecification(IssuedBooksCriteria criteria) {
        Specification<IssuedBooks> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), IssuedBooks_.id));
            }
            if (criteria.getRegistrationNumber() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getRegistrationNumber(), IssuedBooks_.registrationNumber));
            }
            if (criteria.getIsbn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIsbn(), IssuedBooks_.isbn));
            }
            if (criteria.getIssueDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIssueDate(), IssuedBooks_.issueDate));
            }
            if (criteria.getReturnDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReturnDate(), IssuedBooks_.returnDate));
            }
        }
        return specification;
    }
}
