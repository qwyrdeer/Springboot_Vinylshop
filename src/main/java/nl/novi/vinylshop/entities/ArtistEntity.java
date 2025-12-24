package nl.novi.vinylshop.entities;

import jakarta.persistence.*;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {
    private String name;
    private String biography;

    @ManyToMany(mappedBy = "artists")
    private Set<AlbumEntity> albums = new HashSet<>();

    public ArtistEntity(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }

    public ArtistEntity() {

    }

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

}
