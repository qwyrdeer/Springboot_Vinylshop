package nl.novi.vinylshop.dtos.album;

import nl.novi.vinylshop.dtos.stock.StockResponseDTO;

import java.util.List;

public class AlbumExtendedResponseDTO extends AlbumResponseDTO {
        private List<StockResponseDTO> stock;

    public List<StockResponseDTO> getStock() {
        return stock;
    }

    public void setStock(List<StockResponseDTO> stock) {
        this.stock = stock;
    }
}
