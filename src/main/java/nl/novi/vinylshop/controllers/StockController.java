package nl.novi.vinylshop.controllers;

import nl.novi.vinylshop.helpers.UrlHelper;
import nl.novi.vinylshop.services.StockService;
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

//    We hebben al een functionaliteit om een Stock aan een Album toe te voegen.
//    Dat is namelijk de POST mapping in de StockController.

//    @GetMapping

}
