package com.stokapp.stokapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.stokapp.stokapp.Entities.Sales;
import com.stokapp.stokapp.services.SalesService;

@Controller
public class SalesController {
    @Autowired
    SalesService salesService;
    @GetMapping(path = "/sales")
    public String salesHome() {
        return "/sales";
    }
    @GetMapping(path = "sales/salesAdd")
    public String salesShow(ModelMap map) {
        List<Sales> sales = salesService.getAll();
        map.addAttribute("salesMesaj",sales);
        return "sales/salesAdd";
    }
    
    
}
