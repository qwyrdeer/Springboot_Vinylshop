package nl.novi.vinylshop.repository;

import nl.novi.vinylshop.entities.GenreEntity;
import nl.novi.vinylshop.entities.PublishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<PublishEntity, Long> {

}
