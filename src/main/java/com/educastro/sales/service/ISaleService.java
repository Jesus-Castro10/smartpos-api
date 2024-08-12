package com.educastro.sales.service;

import com.educastro.sales.model.dto.SaleDTO;
import com.educastro.sales.model.entities.Sale;

import java.util.List;

public interface ISaleService {
    public List<Sale> toListSale();
    public Sale findSaleById(Integer idSale);
    public Sale saveSale(SaleDTO saleDTO);
    public void deleteSale(Integer idSale);
}
