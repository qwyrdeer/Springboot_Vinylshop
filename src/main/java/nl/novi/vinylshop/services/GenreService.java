package nl.novi.vinylshop.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import nl.novi.vinylshop.dtos.genre.GenreRequestDTO;
import nl.novi.vinylshop.dtos.genre.GenreResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import nl.novi.vinylshop.entities.GenreEntity;
import nl.novi.vinylshop.mappers.GenreMapper;
import nl.novi.vinylshop.repositories.AlbumRepository;
import nl.novi.vinylshop.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Deze GenreService is een tijdelijke oplossing om een echte GenreService na te bootsen.
 * In de volgende les zul je deze GenreService moeten aanpassen of een nieuwe GenreService maken,
 * zodat het gebruik maakt van een database in plaats van een ArrayList.
 * De beschikbare methodes in deze Service zijn:
 * - findAllGenres
 * - findGenreById
 * - createGenre
 * - updateGenre
 * - deleteGenre
 */
@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    private final AlbumRepository albumRepository;

    public GenreService(GenreRepository genreRepository, GenreMapper genreMapper, AlbumRepository albumRepository) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
        this.albumRepository = albumRepository;
    }

    /**
     * Haalt alle record uit de mock-database op.
     * Als de mock-database leeg is, wordt een lege lijst gertourneerd.
     * @return
     */
    public List<GenreResponseDTO> findAllGenres() {
        return genreMapper.mapToDto(genreRepository.findAll());
    }

    /**
     * Haalt een bestaande Genre-record op uit de mock-database op basis van het id.
     * Als er geen record bestaat met dat id, wordt een Exception opgegooid.
     * @param id
     * @return
     */
    public GenreResponseDTO findGenreById(Long id) {
        Optional<GenreEntity> genreEntity = genreRepository.findById(id);
        if (genreEntity.isPresent()) {
            return genreMapper.mapToDto(genreEntity.get());
        }
        return null;
    }

//    /**
//     * Slaat een nieuw Genre-record op in de mock-database en maakt daarbij een uniek ID aan voor het object.
//     * @param  Het te creëren en op te slaan genre. Moet niet `null` zijn.
//     * @return Het opgeslagen Genre-object met het toegekende id.
//     */
    public GenreResponseDTO createGenre(GenreRequestDTO genreRequestDTO) {
        GenreEntity genreEntity = genreMapper.mapToEntity(genreRequestDTO);
        genreEntity = genreRepository.save(genreEntity);
        return genreMapper.mapToDto(genreEntity);
    }

    private GenreEntity getGenreEntity(Long id){
        Optional<GenreEntity> genreEntity = genreRepository.findById(id);
        if (genreEntity.isPresent()) {
            return genreEntity.get();
        }
        return null;
    }

    /**
     * Update een bestaande Genre-record uit de mock-database op basis van het id.
     * @param id
     * @param genreInput
     * @return
     */
    public GenreResponseDTO updateGenre(Long id, GenreRequestDTO genreInput){
        GenreEntity existingGenreEntity = getGenreEntity(id);
        existingGenreEntity.setName(genreInput.getName());
        existingGenreEntity.setDescription(genreInput.getDescription());
        genreRepository.save(existingGenreEntity);
        return genreMapper.mapToDto(existingGenreEntity);
    }

    /**
     * Verwijderd een Genre uit de mock-database op basis van het id
     * @param id
     */

    @Transactional
    public void deleteGenre(Long id) {

        Optional<GenreEntity> optionalGenre = genreRepository.findById(id);

        if (optionalGenre.isEmpty()) {
            throw new EntityNotFoundException("Genre not found");
        }

        GenreEntity genre = optionalGenre.get();

        List<AlbumEntity> albums = albumRepository.findByGenre_Id(id);

        for (AlbumEntity album : albums) {
            album.setGenre(null);
            albumRepository.save(album);
        }

        genreRepository.delete(genre);
    }
}