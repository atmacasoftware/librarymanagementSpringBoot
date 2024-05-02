package net.atmacacode.libraryManagement.dto.request.book;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    @Positive(message = "ID değeri pozitif sayı olmak zorunda")
    private int id;

    @NotNull(message = "Kitap adı boş olamaz")
    private String name;

    @NotNull(message = "Yayınlanma yılı boş olamaz")
    private int publicationYear;

    @NotNull(message = "Stok miktarı boş olamaz")
    private int stock;

    @NotNull(message = "Yazar boş olamaz")
    private int authorId;

    @NotNull(message = "Kategori boş olamaz")
    private int categoryId;

    @NotNull(message = "Yayınevi boş olamaz")
    private int publisherId;;

}
