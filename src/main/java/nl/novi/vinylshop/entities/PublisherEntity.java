package nl.novi.vinylshop.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Publishers")
public class PublisherEntity extends BaseEntity {
        @Column(nullable=false)
        private String name;
        private String address;
        private String contactDetails;

    @OneToMany(mappedBy = "publisher")
    private Set<AlbumEntity> albums = new HashSet<>();

    public Set<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<AlbumEntity> albums) {
        this.albums = albums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

}
