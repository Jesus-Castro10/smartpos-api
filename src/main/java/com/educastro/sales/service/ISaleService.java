package com.educastro.sales.service;

import com.educastro.sales.model.Customer;
import com.educastro.sales.model.Employee;
import com.educastro.sales.model.Sale;
import com.educastro.sales.model.SaleDetails;

import java.util.List;

public interface ISaleService {
    public List<Sale> toListSale();
    public Sale findSaleById(Integer idSale);
    public Sale saveSale(Employee employee, Customer customer, List<SaleDetails> saleDetails);
    public void deleteSale(Sale sale);
}
