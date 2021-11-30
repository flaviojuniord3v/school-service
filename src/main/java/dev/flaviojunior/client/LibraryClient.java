package dev.flaviojunior.client;

import dev.flaviojunior.service.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "library-service")
public interface LibraryClient {

    @GetMapping("api/books/{isbn}/isbn")
    public Optional<BookDTO> getBook(@PathVariable String isbn);
}
