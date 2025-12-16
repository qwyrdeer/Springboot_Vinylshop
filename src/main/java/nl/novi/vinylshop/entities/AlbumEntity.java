package nl.novi.vinylshop.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
    @Table(name = "albums")
    public class AlbumEntity extends BaseEntity {
        private String title;
        private int releaseYear;

    @ManyToOne
    @JoinColumn (name = "publisher_id")
    private PublisherEntity publisher;

    @OneToMany(mappedBy = "album")
    private Set<StockEntity> stockItems = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "albums_artists",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<ArtistEntity> artists = new HashSet<>();

    @ManyToOne
    @JoinColumn (name = "genre_id")
    private GenreEntity genre;

    public AlbumEntity() {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public Set<StockEntity> getStockItems() {
        return stockItems;
    }

    public void setStockItems(Set<StockEntity> stockItems) {
        this.stockItems = stockItems;
    }

    public Set<ArtistEntity> getArtists() {
        return artists;
    }

    public void setArtists(Set<ArtistEntity> artists) {
        this.artists = artists;
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
            return releaseYear;
        }

        public void setReleaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
        }
}

