package nl.novi.vinylshop.services;

import nl.novi.vinylshop.dtos.stock.StockRequestDTO;
import nl.novi.vinylshop.dtos.stock.StockResponseDTO;
import nl.novi.vinylshop.entities.StockEntity;
import nl.novi.vinylshop.mappers.StockMapper;
import nl.novi.vinylshop.repositories.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public StockService(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    public StockResponseDTO createStock(StockRequestDTO stockRequestDTO) {
        StockEntity stockEntity = stockMapper.mapToEntity(stockRequestDTO);
        stockEntity = stockRepository.save(stockEntity);
        return stockMapper.mapToDto(stockEntity);
    }
}

