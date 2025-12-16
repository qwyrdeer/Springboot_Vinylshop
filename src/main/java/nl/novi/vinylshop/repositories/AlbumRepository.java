package nl.novi.vinylshop.repositories;

import nl.novi.vinylshop.entities.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
    List<AlbumEntity> findByStockItemsNotEmpty();

    List<AlbumEntity> findByStockItemsEmpty();

    List<AlbumEntity> findByGenre_Id(Long genreId);
}