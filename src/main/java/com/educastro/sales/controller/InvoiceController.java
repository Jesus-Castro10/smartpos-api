package com.educastro.sales.controller;

import com.educastro.sales.model.entities.Invoice;
import com.educastro.sales.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/invoice")
@RestController
@CrossOrigin
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> getInvoices(){
        return invoiceService.findAll();
    }

    @GetMapping("{id}")
    public Invoice getInvoice(@PathVariable(value = "id") Integer id){
        return invoiceService.findById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable(value = "id") Integer id){
        invoiceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
