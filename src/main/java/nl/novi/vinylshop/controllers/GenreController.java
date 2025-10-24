package nl.novi.vinylshop.controllers;

import nl.novi.vinylshop.helpers.UrlHelper;
import nl.novi.vinylshop.services.GenreService;
import nl.novi.vinylshop.entities.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//GET /genres - Haalt een lijst van alle genres op.
//GET /genres/{id} - Haalt een specifiek genre op basis van ID op.
//POST /genres - Creëert een nieuw genre.
//PUT /genres/{id} - Werkt een bestaand genre bij.
//DELETE /genres/{id} - Verwijdert een genre.


@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;
    private final UrlHelper urlHelper;

    public GenreController(GenreService genreService,  UrlHelper urlHelper) {
        this.genreService = genreService;
        this.urlHelper = urlHelper;

    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        var genres = genreService.findAllGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        var genre = genreService.findGenreById(id);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genreInput) {
        var newGenre = genreService.createGenre(genreInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newGenre.getId())).body(newGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genreInput) {
        var updatedGenre = genreService.updateGenre(id, genreInput);
        return new ResponseEntity<>(updatedGenre, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


