package nl.novi.vinylshop.services;

import nl.novi.vinylshop.entities.Genre;

import java.util.List;

public interface GenreServiceInterface {
    List<Genre> findAllGenres();

    Genre findGenreById(Long id);

    Genre createGenre(Genre genre);

    Genre updateGenre(Long id, Genre genreInput);

    void deleteGenre(Long id);
}
