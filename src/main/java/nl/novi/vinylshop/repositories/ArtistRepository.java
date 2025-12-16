package nl.novi.vinylshop.repositories;

import nl.novi.vinylshop.entities.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
    List<ArtistEntity> findArtistsByAlbumsId(@Param("albumId") Long albumId);
}