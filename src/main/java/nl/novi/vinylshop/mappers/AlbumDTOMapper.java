package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.album.AlbumExtendedResponseDTO;
import nl.novi.vinylshop.dtos.album.AlbumRequestDTO;
import nl.novi.vinylshop.dtos.album.AlbumResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import nl.novi.vinylshop.entities.ArtistEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumDTOMapper implements DTOMapper<AlbumResponseDTO, AlbumRequestDTO, AlbumEntity> {

    @Override
    public AlbumResponseDTO mapToDto(AlbumEntity album) {
        AlbumResponseDTO dto = new AlbumResponseDTO();
        dto.setId(album.getId());
        dto.setTitle(album.getTitle());
        dto.setReleaseYear(album.getReleaseYear());

        if (album.getGenre() != null) {
            dto.setGenre(album.getGenre().getName());
        }
        // getId zou beter zijn.

        if (album.getPublisher() != null) {
            dto.setPublisher(album.getPublisher().getName());
        }

        if (album.getArtists() != null && !album.getArtists().isEmpty()) {
            List<String> artistNames = album.getArtists().stream()
                    .map(ArtistEntity::getName)
                    .toList();
            dto.setArtists(artistNames);
        }

        return dto;
    }

    @Override
    public List<AlbumResponseDTO> mapToDto(List<AlbumEntity> models) {
        List<AlbumResponseDTO> dtos = new ArrayList<>();
        for (AlbumEntity model : models) {
            dtos.add(mapToDto(model));
        }
        return dtos;
    }

    @Override
    public AlbumEntity mapToEntity(AlbumRequestDTO albumModel) {
        AlbumEntity entity = new AlbumEntity();
        entity.setTitle(albumModel.getTitle());
        entity.setReleaseYear(albumModel.getReleaseYear());
        return entity;
    }

    public AlbumExtendedResponseDTO mapToExtendedDto(AlbumEntity album) {
        return null;
    }
}