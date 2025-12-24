package nl.novi.vinylshop.repositories;

import nl.novi.vinylshop.entities.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {

    Optional<PublisherEntity> findPublisherEntityById(long id);
}
