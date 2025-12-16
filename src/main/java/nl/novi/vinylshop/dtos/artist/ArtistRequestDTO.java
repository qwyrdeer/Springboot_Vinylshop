package nl.novi.vinylshop.dtos.artist;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class ArtistRequestDTO {
    @Id
    private Long id;

    @NotBlank
    private String name;

    private String biography;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
