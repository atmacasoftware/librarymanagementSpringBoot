package net.atmacacode.libraryManagement.dto.request.author;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveRequest {
    @NotNull(message = "Yazar adı boş olamaz")
    private String name;

    private LocalDate birthdate;

    @NotNull(message = "Yazar ülkesi boş olamaz")
    private String country;
}
