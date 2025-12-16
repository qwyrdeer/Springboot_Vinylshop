package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.publisher.PublisherRequestDTO;
import nl.novi.vinylshop.dtos.publisher.PublisherResponseDTO;
import nl.novi.vinylshop.entities.PublisherEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PublisherMapper implements DTOMapper<PublisherResponseDTO, PublisherRequestDTO, PublisherEntity> {


    @Override
    public PublisherResponseDTO mapToDto(PublisherEntity model) {
        PublisherResponseDTO dto = new PublisherResponseDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setAddress(model.getAddress());
        dto.setContactDetails(model.getContactDetails());
        return dto;
    }

    @Override
    public List<PublisherResponseDTO> mapToDto(List<PublisherEntity> models) {
        List<PublisherResponseDTO> dtos = new ArrayList<>();
        for (PublisherEntity model : models) {
            dtos.add(mapToDto(model));
        }
        return dtos;
    }

    @Override
    public PublisherEntity mapToEntity(PublisherRequestDTO publisherModel) {
        PublisherEntity entity = new PublisherEntity();
        entity.setName(publisherModel.getName());
        entity.setAddress(publisherModel.getAddress());
        entity.setContactDetails(publisherModel.getContactDetails());
        return entity;
    }

}
