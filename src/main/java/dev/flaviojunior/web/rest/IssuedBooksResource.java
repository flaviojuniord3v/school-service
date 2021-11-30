package dev.flaviojunior.web.rest;

import dev.flaviojunior.common.web.util.HeaderUtil;
import dev.flaviojunior.common.web.util.PaginationUtil;
import dev.flaviojunior.common.web.util.ResponseUtil;
import dev.flaviojunior.repository.IssuedBooksRepository;
import dev.flaviojunior.service.IssuedBooksService;
import dev.flaviojunior.service.dto.IssuedBooksDTO;
import dev.flaviojunior.common.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link dev.flaviojunior.domain.IssuedBooks}.
 */
@RestController
@RequestMapping("/api")
public class IssuedBooksResource {

    private final Logger log = LoggerFactory.getLogger(IssuedBooksResource.class);

    private static final String ENTITY_NAME = "issuedBooks";

    @Value("${spring.application.name}")
    private String applicationName;

    private final IssuedBooksService issuedBooksService;

    private final IssuedBooksRepository issuedBooksRepository;

    public IssuedBooksResource(IssuedBooksService issuedBooksService, IssuedBooksRepository issuedBooksRepository) {
        this.issuedBooksService = issuedBooksService;
        this.issuedBooksRepository = issuedBooksRepository;
    }

    /**
     * {@code POST  /issued-books} : Creates a new issuedBooks.
     *
     * @param issuedBooksDTO A issuedBooksDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new issuedBooksDTO, or with status {@code 400 (Bad Request)} if the issuedBooks has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/issued-books")
    public ResponseEntity<IssuedBooksDTO> createIssuedBooks(@Valid @RequestBody IssuedBooksDTO issuedBooksDTO) throws URISyntaxException {
        log.debug("REST request to save IssuedBooks : {}", issuedBooksDTO);
        if (issuedBooksDTO.getId() != null) {
            throw new BadRequestAlertException("A new issuedBooks cannot already have an ID", ENTITY_NAME);
        }
        IssuedBooksDTO result = issuedBooksService.save(issuedBooksDTO);
        return ResponseEntity
            .created(new URI("/api/issued-books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /issued-books/:id} : Updates an existing issuedBooks.
     *
     * @param id the id of the issuedBooksDTO to save.
     * @param issuedBooksDTO the issuedBooksDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated issuedBooksDTO,
     * or with status {@code 400 (Bad Request)} if the issuedBooksDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the issuedBooksDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/issued-books/{id}")
    public ResponseEntity<IssuedBooksDTO> updateIssuedBooks(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody IssuedBooksDTO issuedBooksDTO
    ) throws URISyntaxException {
        log.debug("REST request to update IssuedBooks : {}, {}", id, issuedBooksDTO);
        if (issuedBooksDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, issuedBooksDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!issuedBooksRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        IssuedBooksDTO result = issuedBooksService.save(issuedBooksDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, issuedBooksDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /issued-books/:id} : Partial updates given fields of an existing issuedBooks, field will ignore if it is null
     *
     * @param id the id of the issuedBooksDTO to save.
     * @param issuedBooksDTO the issuedBooksDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated issuedBooksDTO,
     * or with status {@code 400 (Bad Request)} if the issuedBooksDTO is not valid,
     * or with status {@code 404 (Not Found)} if the issuedBooksDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the issuedBooksDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/issued-books/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<IssuedBooksDTO> partialUpdateIssuedBooks(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody IssuedBooksDTO issuedBooksDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update IssuedBooks partially : {}, {}", id, issuedBooksDTO);
        if (issuedBooksDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, issuedBooksDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!issuedBooksRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        Optional<IssuedBooksDTO> result = issuedBooksService.partialUpdate(issuedBooksDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, issuedBooksDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /issued-books} : get all the issuedBooks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of issuedBooks in body.
     */
    @GetMapping("/issued-books")
    public ResponseEntity<List<IssuedBooksDTO>> getAllIssuedBooks(Pageable pageable) {
        log.debug("REST request to get a page of IssuedBooks");
        Page<IssuedBooksDTO> page = issuedBooksService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /issued-books/:id} : get the "id" issuedBooks.
     *
     * @param id the id of the issuedBooksDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the issuedBooksDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/issued-books/{id}")
    public ResponseEntity<IssuedBooksDTO> getIssuedBooks(@PathVariable Long id) {
        log.debug("REST request to get IssuedBooks : {}", id);
        Optional<IssuedBooksDTO> issuedBooksDTO = issuedBooksService.findOne(id);
        return ResponseUtil.wrapOrNotFound(issuedBooksDTO);
    }

    /**
     * {@code DELETE  /issued-books/:id} : delete the "id" issuedBooks.
     *
     * @param id the id of the issuedBooksDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/issued-books/{id}")
    public ResponseEntity<Void> deleteIssuedBooks(@PathVariable Long id) {
        log.debug("REST request to delete IssuedBooks : {}", id);
        issuedBooksService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, id.toString()))
            .build();
    }
}
