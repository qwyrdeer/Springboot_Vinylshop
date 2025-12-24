package nl.novi.vinylshop.dtos.genre;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class GenreRequestDTO {
    @Id
    private Long id;

    @NotBlank
    @Max(value = 100, message = "Naam mag niet langer zijn dan 100 karakters")
    @Min(value = 2, message = "Naam mag niet korter zijn dan 2 karakters")
    private String name;
    @Max(value = 255, message = "Beschrijving mag niet langer zijn dan 255 karakters")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
