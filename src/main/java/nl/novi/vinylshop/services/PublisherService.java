package nl.novi.vinylshop.services;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.vinylshop.dtos.publisher.PublisherRequestDTO;
import nl.novi.vinylshop.dtos.publisher.PublisherResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.mappers.PublisherMapper;
import nl.novi.vinylshop.repositories.AlbumRepository;
import nl.novi.vinylshop.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;
    private final AlbumRepository albumRepository;

    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper, AlbumRepository albumRepository) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
        this.albumRepository = albumRepository;
    }

    public List<PublisherResponseDTO> findAllPublishers() {
        return publisherMapper.mapToDto(publisherRepository.findAll());
    }

    public PublisherResponseDTO findPublisherById(Long id) {
        Optional<PublisherEntity> publisherEntity = publisherRepository.findById(id);
        return publisherEntity.map(publisherMapper::mapToDto).orElse(null);
    }

    public Optional<PublisherEntity> findPublisherEntityById(long id) {
        return publisherRepository.findPublisherEntityById(id);
    }

    public PublisherResponseDTO createPublisher(PublisherRequestDTO publisherRequestDTO) {
        PublisherEntity publisherEntity = publisherMapper.mapToEntity(publisherRequestDTO);
        publisherEntity = publisherRepository.save(publisherEntity);
        return publisherMapper.mapToDto(publisherEntity);
    }

    public PublisherResponseDTO updatePublisher(Long id, PublisherRequestDTO publisherInput) {
        PublisherEntity existingPublisherEntity = getPublisherEntity(id);
        existingPublisherEntity.setName(publisherInput.getName());
        existingPublisherEntity.setContactDetails(publisherInput.getContactDetails());
        existingPublisherEntity.setAddress(publisherInput.getAddress());
        publisherRepository.save(existingPublisherEntity);
            return publisherMapper.mapToDto(existingPublisherEntity);

    }

    public void deletePublisher(Long id) {

        Optional<PublisherEntity> optionalPublisher = publisherRepository.findById(id);

        if (optionalPublisher.isEmpty()) {
            throw new EntityNotFoundException("Publisher not found");
        }

        PublisherEntity publisher = optionalPublisher.get();

        for (AlbumEntity album : publisher.getAlbums()) {
            album.setPublisher(null);
            albumRepository.save(album);
        }

        publisherRepository.delete(publisher);
    }

    private PublisherEntity getPublisherEntity(Long id){
        Optional<PublisherEntity> publisherEntity = publisherRepository.findById(id);
        if (publisherEntity.isPresent()) {
            return publisherEntity.get();
        }
        return null;
    }

}
