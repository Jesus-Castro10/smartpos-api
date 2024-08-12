package com.educastro.sales.controller;

import com.educastro.sales.model.dto.SaleDTO;
import com.educastro.sales.model.entities.Sale;
import com.educastro.sales.service.SaleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/sales")
@RestController
@CrossOrigin
public class SaleController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final SaleService saleService;

    @GetMapping
    public List<Sale> findAll() {
        return saleService.toListSale();
    }

    @GetMapping("{id}")
    public Sale getSale(@PathVariable(value = "id") Integer idSale) {
        return saleService.findSaleById(idSale);
    }

    @PostMapping
    public Sale saveSale(@RequestBody SaleDTO saleDTO) {
        return saleService.saveSale(saleDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable(value = "id") Integer idSale) {
        saleService.deleteSale(idSale);
        return ResponseEntity.noContent().build();
    }
}
