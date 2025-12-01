package nl.novi.vinylshop.services;

import nl.novi.vinylshop.entities.GenreEntity;
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

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Haalt alle record uit de mock-database op.
     * Als de mock-database leeg is, wordt een lege lijst gertourneerd.
     * @return
     */
    public List<GenreEntity> findAllGenres() {
        return genreRepository.findAll();
    }

    /**
     * Haalt een bestaande Genre-record op uit de mock-database op basis van het id.
     * Als er geen record bestaat met dat id, wordt een Exception opgegooid.
     * @param id
     * @return
     */
    public GenreEntity findGenreById(Long id) {
        Optional<GenreEntity> genreEntity = genreRepository.findById(id);
        if (genreEntity.isPresent()) {
            return genreEntity.get();
        }
        return null;
    }

    /**
     * Slaat een nieuw Genre-record op in de mock-database en maakt daarbij een uniek ID aan voor het object.
     * @param genre Het te creëren en op te slaan genre. Moet niet `null` zijn.
     * @return Het opgeslagen Genre-object met het toegekende id.
     */
    public GenreEntity createGenre(GenreEntity genreEntity) {
        genreRepository.save(genreEntity);
        return genreEntity;

    }

    /**
     * Update een bestaande Genre-record uit de mock-database op basis van het id.
     * @param id
     * @param genreInput
     * @return
     */
    public GenreEntity updateGenre(Long id, GenreEntity genreInput){
        GenreEntity existingGenreEntity = findGenreById(id);
        existingGenreEntity.setName(genreInput.getName());
        existingGenreEntity.setDescription(genreInput.getDescription());
        genreRepository.save(existingGenreEntity);
        return existingGenreEntity;
    }

    /**
     * Verwijderd een Genre uit de mock-database op basis van het id
     * @param id
     */
    public void deleteGenre(Long id) {
        try{
        GenreEntity existingGenreEntity = findGenreById(id);
        genreRepository.delete(existingGenreEntity);
        } catch (IndexOutOfBoundsException ex) {
        }

    }
}