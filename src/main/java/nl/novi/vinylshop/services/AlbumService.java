package nl.novi.vinylshop.services;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.vinylshop.dtos.album.AlbumExtendedResponseDTO;
import nl.novi.vinylshop.dtos.album.AlbumRequestDTO;
import nl.novi.vinylshop.dtos.album.AlbumResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import nl.novi.vinylshop.mappers.AlbumDTOMapper;
import nl.novi.vinylshop.mappers.AlbumExtendedDTOMapper;
import nl.novi.vinylshop.repositories.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RestController
@RequestMapping("/albums")
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumExtendedDTOMapper albumMapper;

    public AlbumService(AlbumRepository albumRepository, AlbumExtendedDTOMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
    }

    public AlbumExtendedResponseDTO findAlbumById(Long id) {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow();
        return albumMapper.mapToExtendedDto(album);
    }

    public AlbumResponseDTO createAlbum(AlbumRequestDTO albumRequestDTO) {
        AlbumEntity albumEntity = albumMapper.mapToEntity(albumRequestDTO);
        albumEntity = albumRepository.save(albumEntity);
        return albumMapper.mapToExtendedDto(albumEntity);
    }

    private AlbumEntity getAlbumEntity(Long id){
        Optional<AlbumEntity> albumEntity = albumRepository.findById(id);
        if (albumEntity.isPresent()) {
            return albumEntity.get();
        } return null;
    }

    public AlbumResponseDTO updateAlbum(Long id, AlbumRequestDTO albumInput) {
        AlbumEntity existingAlbumEntity = getAlbumEntity(id);
        existingAlbumEntity.setTitle(albumInput.getTitle());
        existingAlbumEntity.setReleaseYear(albumInput.getReleaseYear());
        albumRepository.save(existingAlbumEntity);
        return albumMapper.mapToExtendedDto(existingAlbumEntity);
    }

    public void deleteAlbum(Long id) {

        Optional<AlbumEntity> optionalAlbum = albumRepository.findById(id);

        if (optionalAlbum.isEmpty()) {
            throw new EntityNotFoundException("Album not found");
        }

        AlbumEntity album = optionalAlbum.get();

        if (album.getStockItems() != null && !album.getStockItems().isEmpty()) {
            throw new IllegalStateException(
                    "Album cannot be deleted because it still has stock"
            );
        }

        albumRepository.delete(album);
    }

    public List<AlbumResponseDTO> findAllAlbums() {
        List<AlbumEntity> albumEntities = albumRepository.findAll();
        return albumEntities.stream()
                .map(albumMapper::mapToExtendedDto)
                .collect(Collectors.toList());
    }
}
