package net.atmacacode.libraryManagement.dto.request.category;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveRequest {
    @NotNull(message = "Kategori adı boş olamaz")
    private String name;

    @NotNull(message = "Kategori açıklaması boş olamaz")
    private String description;

}
