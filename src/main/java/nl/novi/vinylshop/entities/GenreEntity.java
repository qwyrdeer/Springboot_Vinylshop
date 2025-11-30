package nl.novi.vinylshop.entities;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class GenreEntity extends BaseEntity {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
