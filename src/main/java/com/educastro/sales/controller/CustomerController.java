package com.educastro.sales.controller;

import com.educastro.sales.model.dto.CustomerDTO;
import com.educastro.sales.model.entities.Customer;
import com.educastro.sales.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/customers")
@RestController
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> findAll(){
        return customerService.toListCustomer();
    }

    @GetMapping("{id}")
    public Customer getCustomer(@PathVariable Integer id){
        return customerService.findCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO customer){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));
    }

    @PutMapping
    public Customer updateCustomer(@PathVariable Integer id,
                                   @Valid @RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(id,customerDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
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
}
