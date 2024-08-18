package com.educastro.sales.util;

import com.educastro.sales.model.Sale;
import com.educastro.sales.model.SaleDetails;
import com.educastro.sales.service.ProductService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SaleFunctions {

    @Autowired
    private static ProductService productService;

    public static List<SaleDetails> recoveryProducts(Sale sale) {
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
        return salesDetails;
    }
}
