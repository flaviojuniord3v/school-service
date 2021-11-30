package dev.flaviojunior.client;

import dev.flaviojunior.service.dto.BookDTO;
import dev.flaviojunior.service.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("student-service")
public interface StudentClient {

    @GetMapping("api/students/{registrationNumber}/registrationNumber")
    public Optional<StudentDTO> getStudent(@PathVariable Integer registrationNumber);
}
