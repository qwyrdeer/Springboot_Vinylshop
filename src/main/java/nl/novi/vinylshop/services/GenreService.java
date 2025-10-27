package nl.novi.vinylshop.services;

import nl.novi.vinylshop.entities.Genre;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    private final ArrayList<Genre> genreRepository;

    public GenreService() {
        genreRepository = new ArrayList<>();
    }

    /**
     * Haalt alle record uit de mock-database op.
     * Als de mock-database leeg is, wordt een lege lijst gertourneerd.
     * @return
     */
    public List<Genre> findAllGenres() {
        return genreRepository;
    }

    /**
     * Haalt een bestaande Genre-record op uit de mock-database op basis van het id.
     * Als er geen record bestaat met dat id, wordt een Exception opgegooid.
     * @param id
     * @return
     */
    public Genre findGenreById(Long id) {
        return genreRepository.stream().filter(g -> g.getId().equals(id)).findFirst().orElseThrow(()->new IndexOutOfBoundsException("Genre met ID " + id + " niet gevonden"));
    }

    /**
     * Slaat een nieuw Genre-record op in de mock-database en maakt daarbij een uniek ID aan voor het object.
     * @param genre Het te creëren en op te slaan genre. Moet niet `null` zijn.
     * @return Het opgeslagen Genre-object met het toegekende id.
     */
    public Genre createGenre(Genre genre) {
        genre.setId(findNextId(genreRepository));
        genreRepository.add(genre);
        return genre;

    }

    /**
     * Update een bestaande Genre-record uit de mock-database op basis van het id.
     * @param id
     * @param genreInput
     * @return
     */
    public Genre updateGenre(Long id, Genre genreInput){
        Genre existingGenreEntity = findGenreById(id);

        existingGenreEntity.setName(genreInput.getName());
        existingGenreEntity.setDescription(genreInput.getDescription());

        return existingGenreEntity;
    }

    /**
     * Verwijderd een Genre uit de mock-database op basis van het id
     * @param id
     */
    public void deleteGenre(Long id) {
        try{
        Genre existingGenreEntity = findGenreById(id);
        genreRepository.remove(existingGenreEntity);
        } catch (IndexOutOfBoundsException _) {
        }

    }

    /**
     * Een database maakt automatisch de volgende, unieke Primary Key voor je.
     * Deze helper-methode bootst die functionaliteit na in de ArrayList.
     */
    private Long findNextId(ArrayList<Genre> genreRepository) {
        Long highest = 0L;
        if(!genreRepository.isEmpty()){
            for(Genre genre : genreRepository){
                if(genre.getId() > highest){
                    highest = genre.getId();
                }
            }
        }
        return highest+1;
    }

}