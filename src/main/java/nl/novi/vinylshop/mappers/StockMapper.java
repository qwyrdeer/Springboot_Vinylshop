package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.stock.StockRequestDTO;
import nl.novi.vinylshop.dtos.stock.StockResponseDTO;
import nl.novi.vinylshop.entities.StockEntity;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {


    public StockEntity mapToEntity(StockRequestDTO dto) {
        StockEntity entity = new StockEntity();
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        entity.setCondition(dto.getCondition());
        return entity;
    }

    public StockResponseDTO mapToDto(StockEntity entity) {
        StockResponseDTO dto = new StockResponseDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setCondition(entity.getCondition());
        return dto;
    }
}