package nl.novi.vinylshop.services;

import nl.novi.vinylshop.dtos.artist.ArtistRequestDTO;
import nl.novi.vinylshop.dtos.artist.ArtistResponseDTO;
import nl.novi.vinylshop.entities.ArtistEntity;
import nl.novi.vinylshop.mappers.ArtistDTOMapper;
import nl.novi.vinylshop.repositories.ArtistRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistDTOMapper artistMapper;

    public ArtistService(ArtistRepository artistRepository, ArtistDTOMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    @GetMapping
    public List<ArtistResponseDTO> findAllArtists() {
        return artistMapper.mapToDto(artistRepository.findAll());
    }

    public ArtistResponseDTO findArtistById(Long id) {
        Optional<ArtistEntity> artistEntity = artistRepository.findById(id);
        if (artistEntity.isPresent()) {
            return artistMapper.mapToDto(artistEntity.get());
        }
        return null;
    }

    public ArtistResponseDTO createArtist(ArtistRequestDTO artistRequestDTO) {
        ArtistEntity artistEntity = artistMapper.mapToEntity(artistRequestDTO);
        artistEntity = artistRepository.save(artistEntity);
        return artistMapper.mapToDto(artistEntity);
    }

    private ArtistEntity getArtistEntity(Long id){
        Optional<ArtistEntity> artistEntity = artistRepository.findById(id);
        if (artistEntity.isPresent()) {
            return artistEntity.get();
        }
        return null;
    }

    public ArtistResponseDTO updateArtist(Long id, ArtistRequestDTO artistInput){
        ArtistEntity existingArtistEntity = getArtistEntity(id);
        existingArtistEntity.setName(artistInput.getName());
        existingArtistEntity.setBiography(artistInput.getBiography());
        artistRepository.save(existingArtistEntity);
        return artistMapper.mapToDto(existingArtistEntity);
    }

    public void deleteArtist(Long id){
        try{
            ArtistEntity existingArtistEntity = getArtistEntity(id);
            artistRepository.delete(existingArtistEntity);
        } catch (IndexOutOfBoundsException ex) {
    }
    }
}
