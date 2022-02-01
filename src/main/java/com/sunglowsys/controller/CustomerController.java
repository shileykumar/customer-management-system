package com.sunglowsys.controller;

import com.sunglowsys.domain.Customer;
import com.sunglowsys.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("customer",customerService.findAll());
        return "index";
    }

    @GetMapping("/addCustomer")
    public String addCustomerForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        return "add_customer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("employee") Customer customer){
        customerService.save(customer);
        return "redirect:/";
    }

    @GetMapping("/updateCustomerForm/{id}")
    public String updateCustomerForm(@PathVariable("id") Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer",customer);
        return "update_customer";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id){
        customerService.delete(id);
        return "redirect:/";
    }
}
