package dev.flaviojunior.service.mapper;

import dev.flaviojunior.domain.IssuedBooks;
import dev.flaviojunior.service.dto.IssuedBooksDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link IssuedBooks} and its DTO {@link IssuedBooksDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface IssuedBooksMapper extends EntityMapper<IssuedBooksDTO, IssuedBooks> {}
