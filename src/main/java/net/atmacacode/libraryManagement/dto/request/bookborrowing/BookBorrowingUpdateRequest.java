package net.atmacacode.libraryManagement.dto.request.bookborrowing;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingUpdateRequest {
    @Positive(message = "ID değeri pozitif sayı olmak zorunda")
    private int id;

    @NotNull(message = "Kiralayan kişi adı boş olamaz")
    private String borrowerName;

    @NotNull(message = "Kiralama tarihi boş olamaz")
    private LocalDate borrowingDate;

    @NotNull(message = "Teslim tarihi boş olamaz")
    private LocalDate returnDate;

    @NotNull(message = "Kitap boş olamaz")
    private int bookId;
}
