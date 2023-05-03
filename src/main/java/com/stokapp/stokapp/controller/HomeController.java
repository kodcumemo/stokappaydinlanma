package com.stokapp.stokapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.stokapp.stokapp.Entities.Mesajlar;
import com.stokapp.stokapp.repository.MesajRepos;

@Controller
public class HomeController {
    @Autowired
    MesajRepos mRepos;
    @GetMapping(path = {"","/","home"})
    public String home(ModelMap map) {
        List<Mesajlar> mesajlar = mRepos.findAll(); 
        map.addAttribute("asmesaj", "Ana Sayfa Thymelife Çalışıyor");
        map.addAttribute("mesajlar", mesajlar);
        return "index";
    }
    
}
