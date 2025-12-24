package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.album.AlbumExtendedResponseDTO;
import nl.novi.vinylshop.dtos.album.AlbumRequestDTO;
import nl.novi.vinylshop.dtos.stock.StockResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumExtendedDTOMapper {

    private final StockMapper stockMapper;

    public AlbumExtendedDTOMapper(StockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }

    public AlbumEntity mapToEntity(AlbumRequestDTO dto) {
        AlbumEntity entity = new AlbumEntity();
        entity.setTitle(dto.getTitle());
        entity.setReleaseYear(dto.getReleaseYear());
        return entity;
    }

    public AlbumExtendedResponseDTO mapToExtendedDto(AlbumEntity model) {
        AlbumExtendedResponseDTO dto = new AlbumExtendedResponseDTO();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setReleaseYear(model.getReleaseYear());
        dto.setGenre(model.getGenre() != null ? model.getGenre().getName() : null);
        dto.setPublisher(model.getPublisher() != null ? model.getPublisher().getName() : null);

        if (model.getArtists() != null && !model.getArtists().isEmpty()) {
            List<String> artistNames = model.getArtists().stream()
                    .map(artist -> artist.getName())
                    .collect(Collectors.toList());
            dto.setArtists(artistNames);
        }

        if (model.getStockItems() != null && !model.getStockItems().isEmpty()) {
            List<StockResponseDTO> stockDtos = model.getStockItems().stream()
                    .map(stockMapper::mapToDto)
                    .collect(Collectors.toList());
            dto.setStock(stockDtos);
        }

        return dto;
    }
}