package com.educastro.sales.service;

import com.educastro.sales.model.Sale;

import java.util.List;

public interface ISaleService {
    public List<Sale> toListSale();
    public Sale findSaleById(Integer idSale);
    public void saveSale(Sale sale);
    public void deleteSale(Sale sale);
}
