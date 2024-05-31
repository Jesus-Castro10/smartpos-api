package com.educastro.sales.controller;

import com.educastro.sales.model.*;
import com.educastro.sales.service.CustomerService;
import com.educastro.sales.service.EmployeeService;
import com.educastro.sales.service.ProductService;
import com.educastro.sales.service.SaleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class SaleController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private List<SaleDetails> detailsList = new ArrayList<>();
    private List<String> detailsName = new ArrayList<>();

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/sales")
    public String showSales(HttpServletRequest request) {
        var customer = new Customer("0000", "xxxx", "xxxx");
        request.getSession().setAttribute("detailsList", detailsList);
        request.getSession().setAttribute("product", new Product());
        request.getSession().setAttribute("customer", customer);
        return "sales";
    }

    @GetMapping("/searchCustomer")
    public String searchCustomer(@RequestParam("idCustomer") String idCustomer, HttpServletRequest request) {
        Customer customer = customerService.findCustomerById(idCustomer);
        if (customer != null) {
            request.getSession().setAttribute("customer", customer);
        } else {
            //Mostrr venta emergente de que no existe el usuario
            logger.info("Customer empty");
        }
        logger.info("Customer replace => " + customer);
        return "sales";
    }

    @GetMapping("/addCart")
    public String addCart(@RequestParam("amount") int amount, HttpServletRequest request) {
        Product product = (Product) request.getSession().getAttribute("product");
        detailsList.remove(new SaleDetails());
        SaleDetails saleDetails = new SaleDetails(product);
        if (product != null && product.getIdProduct() != null) {
            int index = detailsExist(saleDetails);
            if (index != -1) {
                var sd2 = (SaleDetails) detailsList.get(index);
                logger.info("sd2.getAmount() -> " + sd2.getAmount());
                logger.info("amount before -> " + amount);
                amount += sd2.getAmount();
                logger.info("amount now -> " + amount);
                saleDetails.setAmount(amount);
                logger.info("sale now -> " + saleDetails.getAmount());
                detailsList.set(index, saleDetails);
            } else {
                saleDetails.setAmount(amount);
                detailsList.add(saleDetails);
                detailsName.add(saleDetails.getProduct().getName());
            }
        } else {
            //Ventana de no hay product seleccionado
            logger.info("Producto no encontrado");
        }
        request.getSession().setAttribute("product", new Product());
        return "sales";
    }

    @GetMapping("/searchProduct")
    public String searchProduct(@RequestParam("idProduct") Integer idProduct, HttpServletRequest request) {
        Product product = productService.findProductById(idProduct);
        if (product != null) {
            request.getSession().setAttribute("product", product);
        } else {
            logger.info("Producto no encotrado");
        }
        return "sales";
    }

    @GetMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable(value = "id") int id) {
        var sd = (SaleDetails) detailsList.get(id);
        detailsName.remove(sd.getProduct().getName());
        detailsList.remove(id);
        return "sales";
    }

    @GetMapping("/saveSale")
    public void saveSale(HttpServletRequest request, @AuthenticationPrincipal User user, HttpServletResponse response) {
        Employee employee = employeeService.findEmployeeById(user.getUsername());
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        JSONArray products = new JSONArray();
        double subTotal = 0;
        for (SaleDetails saleDetails : detailsList) {
            products.put(new JSONObject()
                    .put("name", saleDetails.getProduct().getName())
                    .put("amount", saleDetails.getAmount())
            );
            subTotal += saleDetails.getSubTotal();
        }
        double total = subTotal + (subTotal * 0.19);
        logger.info("sales  => " + products);
        Date date = new Date();
        LocalTime hour = LocalTime.now();
        Sale sale = new Sale(customer, employee, date, hour, products.toString(), total);
        saleService.saveSale(sale);
        invoice(sale,response);
        detailsList.clear();
    }

    @GetMapping("/deleteSale/{id}")
    public String deleteSale(@PathVariable(value = "id") Integer idSale) {
        var sale = saleService.findSaleById(idSale);
        saleService.deleteSale(sale);
        return "redirect:/salesHistory";
    }

    @GetMapping("/salesHistory")
    public String showSaleHistory(ModelMap map) {
        List<Sale> sales = saleService.toListSale();
        map.put("sales", sales);
        return "salesHistory";
    }

    @GetMapping("/seeSale/{id}")
    public String seeSale(@PathVariable(value = "id") Integer idSale, ModelMap map) {
        var sale = saleService.findSaleById(idSale);
        List<SaleDetails> saleDetailsList = recoveryProducts(sale);
        map.put("sale", sale);
        map.put("salesDetails", saleDetailsList);
        return "seeSale";
    }

    @GetMapping("/printSale")
    public String printSale() {
        var sale = saleService.findSaleById(1);
        recoveryProducts(sale);
        return "redirect:/salesHistory";
    }

    @GetMapping("/getSaleDetails")
    @ResponseBody
    public List<SaleDetails> getDetailsList(HttpServletRequest request){
            List<SaleDetails> saleDetailsList = (List<SaleDetails>) request.getSession().getAttribute("detailsList");
            for(SaleDetails details : saleDetailsList){
                logger.info("get details -> " + details);
            }
            return saleDetailsList;
    }

    private void invoice(Sale sale,HttpServletResponse response){
        try {
            byte[] reportBytes = saleService.generateInvoice(sale);
            // Set the response headers
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=employeeReport.pdf");

            // Write the report bytes to the response output stream
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(reportBytes);
            outputStream.flush();
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int detailsExist(SaleDetails saleDetails) {
        if (detailsName.contains(saleDetails.getProduct().getName())) {
            return detailsName.indexOf(saleDetails.getProduct().getName());
        }
        return -1;
    }

    private List<SaleDetails> recoveryProducts(Sale sale) {
        JSONArray products = new JSONArray(sale.getProducts());
        List<SaleDetails> salesDetails = new ArrayList<>();
        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);
            var name = product.getString("name");
            var p = productService.findProductByName(name);
            var amount = product.getInt("amount");
            var saleDetails = new SaleDetails(p, amount);
            salesDetails.add(saleDetails);
        }
        for (SaleDetails saleDetails1 : salesDetails) {
            logger.info(saleDetails1.toString());
        }
        logger.info("product json => " + products);
        return salesDetails;
    }
}
