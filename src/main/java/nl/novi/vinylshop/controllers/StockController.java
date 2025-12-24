package nl.novi.vinylshop.controllers;

import nl.novi.vinylshop.dtos.genre.GenreRequestDTO;
import nl.novi.vinylshop.dtos.genre.GenreResponseDTO;
import nl.novi.vinylshop.dtos.stock.StockRequestDTO;
import nl.novi.vinylshop.dtos.stock.StockResponseDTO;
import nl.novi.vinylshop.helpers.UrlHelper;
import nl.novi.vinylshop.services.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums/{albumId}/stock")

public class StockController {
    private final StockService stockService;
    private final UrlHelper urlHelper;

    public StockController(StockService stockService, UrlHelper urlHelper) {
        this.stockService = stockService;
        this.urlHelper = urlHelper;
    }


    @PostMapping
    public ResponseEntity<StockResponseDTO> createStock(@RequestBody StockRequestDTO stockInput) {
        var newStock = stockService.createStock(stockInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newStock.getId())).body(newStock);
    }


//    @GetMapping

}
