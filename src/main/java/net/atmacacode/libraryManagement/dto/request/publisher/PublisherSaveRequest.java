package net.atmacacode.libraryManagement.dto.request.publisher;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveRequest {
    @NotNull(message = "Yayınevi adı boş olamaz")
    private String name;

    @NotNull(message = "Kuruluş yılı boş olamaz")
    private int establishmentYear;

    @NotNull(message = "Adres boş olamaz")
    private String address;

}
