package dev.flaviojunior.service;

import dev.flaviojunior.client.LibraryClient;
import dev.flaviojunior.client.StudentClient;
import dev.flaviojunior.domain.IssuedBooks;
import dev.flaviojunior.repository.IssuedBooksRepository;
import dev.flaviojunior.service.dto.BookDTO;
import dev.flaviojunior.service.dto.IssuedBooksDTO;
import dev.flaviojunior.service.dto.StudentDTO;
import dev.flaviojunior.service.mapper.IssuedBooksMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link IssuedBooks}.
 */
@Service
@Transactional
public class IssuedBooksService {

    private final Logger log = LoggerFactory.getLogger(IssuedBooksService.class);

    private final IssuedBooksRepository issuedBooksRepository;

    private final IssuedBooksMapper issuedBooksMapper;

    private final LibraryClient libraryClient;

    private final StudentClient studentClient;

    public IssuedBooksService(IssuedBooksRepository issuedBooksRepository, IssuedBooksMapper issuedBooksMapper, LibraryClient libraryClient, StudentClient studentClient) {
        this.issuedBooksRepository = issuedBooksRepository;
        this.issuedBooksMapper = issuedBooksMapper;
        this.libraryClient = libraryClient;
        this.studentClient = studentClient;
    }

    /**
     * Save a issuedBooks.
     *
     * @param issuedBooksDTO the entity to save.
     * @return the persisted entity.
     */
    public IssuedBooksDTO save(IssuedBooksDTO issuedBooksDTO) {
        log.debug("Request to save IssuedBooks : {}", issuedBooksDTO);
        IssuedBooks issuedBooks = issuedBooksMapper.toEntity(issuedBooksDTO);
        issuedBooks = issuedBooksRepository.save(issuedBooks);
        return issuedBooksMapper.toDto(issuedBooks);
    }

    /**
     * Partially update a issuedBooks.
     *
     * @param issuedBooksDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<IssuedBooksDTO> partialUpdate(IssuedBooksDTO issuedBooksDTO) {
        log.debug("Request to partially update IssuedBooks : {}", issuedBooksDTO);

        return issuedBooksRepository
            .findById(issuedBooksDTO.getId())
            .map(
                existingIssuedBooks -> {
                    issuedBooksMapper.partialUpdate(existingIssuedBooks, issuedBooksDTO);

                    return existingIssuedBooks;
                }
            )
            .map(issuedBooksRepository::save)
            .map(issuedBooksMapper::toDto);
    }

    /**
     * Get all the issuedBooks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<IssuedBooksDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IssuedBooks");
        return issuedBooksRepository.findAll(pageable).map(issuedBooksMapper::toDto);
    }

    /**
     * Get one issuedBooks by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<IssuedBooksDTO> findOne(Long id) {
        log.debug("Request to get IssuedBooks : {}", id);
        Optional<IssuedBooksDTO> issuedBooks = issuedBooksRepository.findById(id).map(issuedBooksMapper::toDto);
        issuedBooks.ifPresent(dto -> {
            String isbn = issuedBooks.map(IssuedBooksDTO::getIsbn).orElse("");
            BookDTO bookDTO = libraryClient.getBook(isbn).orElse(null);
            dto.setBook(bookDTO);

            Integer registrationNumber = issuedBooks.map(IssuedBooksDTO::getRegistrationNumber).orElse(null);
            StudentDTO studentDTO = studentClient.getStudent(registrationNumber).orElse(null);
            dto.setStudent(studentDTO);
        });
        return issuedBooks;
    }

    /**
     * Delete the issuedBooks by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete IssuedBooks : {}", id);
        issuedBooksRepository.deleteById(id);
    }
}
