package com.stokapp.stokapp.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import com.stokapp.stokapp.Entities.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stokapp.stokapp.services.ProductService;

@Controller
public class ProductController {
    @Autowired
    ProductService pService;

    @GetMapping(path = "/productIndex")
    public String productShow(ModelMap map) {
        List<Product> product = pService.ProductGetAll();
        map.addAttribute("products", product);
        return "products";
    }
    @GetMapping(path = "/productIndex/productAdd")
    public String productAddShow(ModelMap map) {
        List<Product> product = pService.ProductGetAllDesc();
        map.addAttribute("products", product);
        return "product/productAdd";
    }
    @GetMapping(path = "/sales/salesAddproductForSales")
    public String productAddShowForSales(ModelMap map) {
        List<Product> product = pService.ProductGetAllDescForSales();
        map.addAttribute("productsSales", product);
        return "sales/salesAdd";
    }
   
    @PostMapping(path = "/sales/salesAddproducts")
    public String productAddShowSales(@RequestParam("urunadi") String name, ModelMap map) {
            map.addAttribute("productsSalesFind", pService.GetByFindProduct("%"+name+"%"));
        return "sales/salesAdd";
    }
    @PostMapping("/productIndex/filterByName")
    public String findNameSearch(@RequestParam("urunadi") String name, ModelMap map) {
      map.addAttribute("products", pService.GetByFindProduct("%"+name+"%"));
        return "products";
    } 
    @GetMapping("/productIndex/productAdded")
    public String productAdd(@RequestParam("urunadi") String urunadi,
            @RequestParam("faturano") String faturano,
            @RequestParam("tutar") Double tutar,
            @RequestParam("adedi") Double adedi,
            // @RequestParam("kayitDurumu") Boolean kayitDurumu,
            @RequestParam("satisFiyati") Double satisFiyati,
            // @RequestParam("satisiskonto") Double satisiskonto,
            // @RequestParam("satisKdvsi") Double satisKdvsi,
            @RequestParam("satisadresi") String satisadresi
    // @RequestParam("idUMusteri") Long idUMusteri
    ) {
        Product product = new Product();
        product.setUrunadi(urunadi);
        product.setFaturano(faturano);
        product.setTutar(tutar);
        product.setAdedi(adedi);
        product.setKayitDurumu(false);
        product.setSatisFiyati(satisFiyati);
        product.setSatisadresi(satisadresi);
        pService.saved(product);
        //map.addAttribute("productadd", product);
        return "redirect:productAdd";
    }

   
   
}
