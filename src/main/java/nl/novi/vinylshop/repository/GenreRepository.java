package nl.novi.vinylshop.repository;


import nl.novi.vinylshop.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

}
