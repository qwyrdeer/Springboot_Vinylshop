package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.genre.GenreRequestDTO;
import nl.novi.vinylshop.dtos.genre.GenreResponseDTO;
import nl.novi.vinylshop.entities.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper implements DTOMapper<GenreResponseDTO, GenreRequestDTO, GenreEntity> {


    @Override
    public GenreResponseDTO mapToDto(GenreEntity model) {
        GenreResponseDTO dto = new GenreResponseDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        return dto;
    }

    @Override
    public List<GenreResponseDTO> mapToDto(List<GenreEntity> models) {
        List<GenreResponseDTO> dtos = new ArrayList<>();
        for (GenreEntity model : models) {
            dtos.add(mapToDto(model));
        }
        return dtos;
    }

    @Override
    public GenreEntity mapToEntity(GenreRequestDTO genreModel) {
        GenreEntity entity = new GenreEntity();
        entity.setName(genreModel.getName());
        entity.setDescription(genreModel.getDescription());
        return entity;
    }
}
