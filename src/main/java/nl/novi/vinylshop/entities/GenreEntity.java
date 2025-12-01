package nl.novi.vinylshop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Genres")
public class GenreEntity extends BaseEntity {
//    @Column(nullable=false)
    private String name;
    private String description;

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
