package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.artist.ArtistRequestDTO;
import nl.novi.vinylshop.dtos.artist.ArtistResponseDTO;
import nl.novi.vinylshop.dtos.genre.ArtistRequestDTO;
import nl.novi.vinylshop.entities.ArtistEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class ArtistDTOMapper<ArtistRequestDTO> {

    public ArtistEntity mapToEntity(nl.novi.vinylshop.dtos.artist.ArtistRequestDTO artistRequestDTO) {
        return null;
    }

    @Component
    public class ArtistMapper implements DTOMapper<ArtistResponseDTO, ArtistRequestDTO, ArtistEntity> {


        @Override
        public ArtistResponseDTO mapToDto(ArtistEntity model) {
            ArtistResponseDTO dto = new ArtistResponseDTO();
            dto.setId(model.getId());
            dto.setName(model.getName());
            dto.setBiography(model.getBiography());
            return dto;
        }

        @Override
        public List<ArtistResponseDTO> mapToDto(List<ArtistEntity> models) {
            List<ArtistResponseDTO> dtos = new ArrayList<>();
            for (ArtistEntity model : models) {
                dtos.add(mapToDto(model));
            }
            return dtos;
        }

        @Override
        public ArtistEntity mapToEntity(ArtistRequestDTO artistModel) {
            ArtistEntity entity = new ArtistEntity();
            entity.setName(artistModel.getName());
            entity.setBiography(artistModel.getBiography());
            return entity;
        }
    }
}