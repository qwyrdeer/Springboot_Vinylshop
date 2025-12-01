package nl.novi.vinylshop.services;

import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<PublisherEntity> findAllPublishers() {
        return publisherRepository.findAll();
    }

    public Object findPublisherById(Long id) {
        Optional<PublisherEntity> publisherEntity = publisherRepository.findById(id);
        if (publisherEntity.isPresent()) {
            return publisherEntity.get();
        }
        return null;
    }

    public PublisherEntity createPublisher(PublisherEntity publisherEntity) {
        publisherRepository.save(publisherEntity);
        return publisherEntity;

    }

    public PublisherEntity updatePublisher(Long id,  PublisherEntity publisherInput) {
        PublisherEntity existingPublisherEntity = (PublisherEntity) findPublisherById(id);
        existingPublisherEntity.setContactDetails(publisherInput.getContactDetails());
        existingPublisherEntity.setAddress(publisherInput.getAddress());
        publisherRepository.save(existingPublisherEntity);

            return existingPublisherEntity;

    }

    public void deletePublisher(Long id) {
        try{
            PublisherEntity existingPublisherEntity = (PublisherEntity) findPublisherById(id);
            publisherRepository.delete(existingPublisherEntity);
        } catch (IndexOutOfBoundsException ex) {
        }
    }
}
