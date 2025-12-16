package nl.novi.vinylshop.entities;

import jakarta.persistence.*;

@Entity
    @Table(name = "albums")
    public class AlbumEntity extends BaseEntity {
        private String title;
        private int releaseYear;

    @ManyToOne
    @JoinColumn (name = "publisher_id", unique = true)
    private PublisherEntity publishers;

    @ManyToMany()
    private StockEntity stockItems;

    @ManyToMany
    @JoinColumn (name = "artists", unique = true)
    private ArtistEntity artists;

    @OneToOne
    @JoinColumn (name = "genre_id", referencedColumnName = "id")
    private GenreEntity genres;

    public AlbumEntity(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public int getReleaseYear() {
            return releaseYear;
        }

        public void setReleaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

