package net.atmacacode.libraryManagement.dto.request.author;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {
    @Positive(message = "ID değeri pozitif sayı olmak zorunda")
    private int id;

    @NotNull(message = "Yazar adı boş olamaz")
    private String name;

    private LocalDate birthdate;

    @NotNull(message = "Yazar ülkesi boş olamaz")
    private String country;
}
