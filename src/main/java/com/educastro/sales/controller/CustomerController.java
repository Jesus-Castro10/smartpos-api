package com.educastro.sales.controller;

import com.educastro.sales.model.dto.CustomerDTO;
import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RequestMapping("/api/customers")
@RestController
@CrossOrigin
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> findAll(){
        return customerService.toListCustomer();
    }

    @GetMapping("{id}")
    public Customer getCustomer(@PathVariable(value = "id") Integer idCustomer){
        return customerService.findCustomerById(idCustomer);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO customer, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(customer));
    }

    @PutMapping
    public Customer updateCustomer(@PathVariable(value = "id") Integer idCustomer,
                                   @Valid @RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(idCustomer,customerDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(value = "id") Integer idCustomer){
        customerService.deleteCustomer(idCustomer);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

//    @GetMapping("/customerExcelExport")
//    public ModelAndView exportToExcel() {
//        ModelAndView mav = new ModelAndView();
//        mav.setView(new CustomerDataExcelExport());
//        //read data from DB
//        List<Customer> list= customerService.toListCustomer();
//        //send to excelImpl class
//        mav.addObject("list", list);
//        return mav;
//    }

//    @PatchMapping("/mark_as_finished/{id}")
//    public ResponseEntity<Void> markAsFiniched(@PathVariable("id") Long id) {
//        this.taskService.updateTaskAsFinished(id);
//        return ResponseEntity.noContent().build();
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
//        this.taskService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}
