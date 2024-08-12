package com.educastro.sales.service;

import com.educastro.sales.model.dto.SaleDTO;
import com.educastro.sales.exception.ResourceNotFoundException;
import com.educastro.sales.model.entities.Sale;
import com.educastro.sales.model.entities.SaleDetails;
import com.educastro.sales.repository.SaleRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.educastro.sales.util.SaleFunctions.*;

@AllArgsConstructor
@Service
public class SaleService implements ISaleService{

    private final SaleRepository saleRepository;
    private final ModelMapper mapper;

    @Override
    public List<Sale> toListSale() {
        return saleRepository.findAll();
    }

    @Override
    public Sale findSaleById(Integer idSale) {
        return saleRepository.findById(idSale).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Sale saveSale(SaleDTO saleDTO) {
        Sale sale = mapper.map(saleDTO,Sale.class);
        sale.setDate(new Date());
        sale.setHour(LocalTime.now());
        return saleRepository.save(sale);
    }

    @Override
    public void deleteSale(Integer idSale) {
        Sale sale = findSaleById(idSale);
        saleRepository.delete(sale);
    }

    public byte[] generateInvoice(Sale sale) throws JRException {
        List<SaleDetails> list = new ArrayList<>();
        if (sale != null){
            list = recoveryProducts(sale);
        }
        // Load the report template
        InputStream reportTemplate = getClass().getResourceAsStream("/reports/Invoice.jrxml");
        // Compile the report template
        JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate);
        // Convert the list of employees to a JRBeanCollectionDataSource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        // Export the report to a byte array (PDF format)
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
