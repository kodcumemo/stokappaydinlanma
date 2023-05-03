package com.stokapp.stokapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stokapp.stokapp.Entities.*;
import com.stokapp.stokapp.services.MustService;
import com.stokapp.stokapp.services.ProductService;
import com.stokapp.stokapp.services.SalesService;

@Controller
public class MusteriController {
  @Autowired
  MustService mustService;
  @Autowired
  SalesService sService;
  @Autowired
  ProductService pService;
  public static Long musteriidsi = 0l;
  public static Long prodid = 0l;

  @GetMapping(path = "/musteriIndex")
  public String MusteriShow(ModelMap map) {
    List<Musteri> must = mustService.getAll();
    map.addAttribute("musteries", must);
    map.addAttribute("zeki","NUR EFŞAN ");
    return "musteriler";
  }

  @GetMapping("musteriIndex/customerAdded")
  public String customerAddShow(ModelMap map) {
    List<Musteri> customer = mustService.customerGetAllDesc();
    map.addAttribute("customer", customer);
    return "customer/customerAdd";
  }

  @PostMapping("/musteriIndex/customerAdded")
  public String customerSaved(
      @RequestParam("isim") String isim,
      @RequestParam("adres") String adres) {
    Musteri must = new Musteri();
    must.setIsim(isim);
    must.setAdres(adres);
    must.setKayitdurumu(false);
    must.setMusterino("");
    mustService.savedCustomer(must);
    return "redirect:";
  }

  @GetMapping("/musteriIndex/{mustid}/sales") // The method to go to the sales page
  public String customDetail(@PathVariable("mustid") Long mustid, ModelMap map) {

    Musteri musteri = mustService.findByMusteriId(mustid);
    map.addAttribute("musteri", musteri);
    musteriidsi = mustid;
    List<Product> pList = pService.ProductGetAll();
    map.addAttribute("productSalesList", pList);

    List<Sales> sList = sService.salesGetAll(mustid);
    map.addAttribute("salesList", sList);
    Double sTopTutar = 0.0;
    Double sAdet     = 0.0;
    Double sAdetTutar = 0.0;
    Double iskonto   = 0.0;  
Double sTutar        = 0.0;
Double indirimi      = 0.0;
Double indirimli     = 0.0;
Double sKdv          = 0.0;
Double sKdvli         = 0.0;
Double topIskontolu = 0.0;
Double topKdv = 0.0;
Double topKdvli = 0.0;
    for(int i = 0; i < sList.size(); i++) {
      sAdet       = sList.get(i).getUrunAdedi();
      iskonto     = sList.get(i).getSatisiskonto();
      sTutar      = sList.get(i).getUrunFiyati();
      sAdetTutar  = sAdet * sTutar;
      sTopTutar   += sAdetTutar;
      indirimi    = sAdetTutar/100.0*iskonto;
      indirimli   = sAdetTutar - indirimi;
      topIskontolu += indirimli;
      sKdv        = sList.get(i).getSatisKdvsi();
      sKdvli       = indirimli/100*sKdv;
      topKdv += sKdv;
      topKdvli += sKdvli;
    }
    map.addAttribute("sTopTutar", Math.round(sTopTutar*100.0)/100.0);
    map.addAttribute("sAdet", Math.round(sAdet*100.0)/100.0);
    map.addAttribute("sAdetTutar", Math.round(sAdetTutar*100.0)/100.0);
    map.addAttribute("iskonto", Math.round(iskonto*100.0)/100.0);
    map.addAttribute("sTutar", Math.round(sTutar*100.0)/100.0);
    map.addAttribute("indirimi", Math.round(indirimi*100.0)/100.0);
    map.addAttribute("indirimli", Math.round(indirimli*100.0)/100.0);
    map.addAttribute("sKdv", Math.round(sKdv*100.0)/100.0);
    map.addAttribute("sKdvli", Math.round(sKdvli*100.0)/100.0);
    map.addAttribute("topIskontolu", Math.round(topIskontolu*100.0)/100.0);
    map.addAttribute("topKdv", Math.round(topKdv*100.0)/100.0);
    map.addAttribute("topKdvli", Math.round(topKdvli*100.0)/100.0);
    return "sales/salesAdd";
  }
  



  @PostMapping("/musteriIndex/filterByNameInMust")
  public String findNameSearchInMustCont(@RequestParam("urunadi") String name, ModelMap map) {
    map.addAttribute("productSalesList", pService.GetByFindProduct("%" + name + "%"));

    return "redirect:";
  }

  @PostMapping("/musteriIndex/filterByAdress")
  public String findAdressSearch(@RequestParam("adres") String adress, ModelMap map) {
    map.addAttribute("musteries", mustService.GetByFindCustomerAdress("%" + adress + "%"));
    return "musteriler";
  }

  @PostMapping("/musteriIndex/filterByName")
  public String findNameSearch(@RequestParam("isim") String name, ModelMap map) {
    map.addAttribute("musteries", mustService.GetByFindCustomerName("%" + name + "%"));
    return "musteriler";
  }

  @PostMapping("/musteriIndex/filterByCustomerNo")
  public String findCustomerNoSearch(@RequestParam("musterino") String customerno, ModelMap map) {
    map.addAttribute("musteries", mustService.GetByFindCustomerNo("%" + customerno + "%"));
    return "musteriler";
  }

  @GetMapping("/productSales/{prodid}/ProductDetailSales")
  public String productSalesDetail(@PathVariable("prodid") Long prodid, ModelMap map) {
    Product productForSales = pService.findByProductId(prodid);
    map.addAttribute("pfs", productForSales);
    map.addAttribute("prodided", prodid);
    map.addAttribute("mustid", musteriidsi);
    return "/sales/salesProductDetail";
  }

  @GetMapping(path = "/sales/sProductDetail")
  public String productAddShowForSalesDetail(ModelMap map) {
    List<Product> products = pService.ProductGetAll();
    // burada boi bi sales nesnesi yollacaksın o zaman thfield kullanabilirsin
    map.addAttribute("salesObj", new Sales()); // bu şekilde anladım teşekkürler hocam rica ederim kolay gelsin
    map.addAttribute("products", products);
    return "/sales/salesProductDetail";
  }
  
  @PostMapping("/musteriIndex/salesSave") // Urunlerin detayına girilen sorgu ÜRÜN KAYDETME BURADA YAPILIYOR
  public String salesSaveProducted(@RequestParam("iduruna") Long idurunp,
                                   @RequestParam("idMusteria") Long idMusterip,
                                   @RequestParam(value="satisKdvsia" , required = false) Double satisKdvsip,
                                   @RequestParam("satisadresia") String satisadresip,
                                   @RequestParam(value="satisiskontoa",required = false) Double satisiskontop,
                                   @RequestParam("urunAdedia") Double urunAdedip,
                                   @RequestParam("urunAdia") String urunAdip,
                                   @RequestParam("urunFiyatia") Double satisFiyatip,
                                   ModelMap map) {
                                    Double newKdv = 0.0;
                                    Double newIskonto = 0.0;
                                    {satisKdvsip = newKdv;}
                                    {satisiskontop = newIskonto;}
                                    Product pr = pService.findByProductId(idurunp);
    Sales syusave = new Sales();
    syusave.setIdMusteri(idMusterip);
    syusave.setIdurun(idurunp);
    if(satisKdvsip == null || satisKdvsip.equals("")){
      syusave.setSatisKdvsi(0.0);
    } else {
      syusave.setSatisKdvsi(satisKdvsip);
    }
    if(satisiskontop == null || satisiskontop.equals("")){
    syusave.setSatisiskonto(0.0);
    }else {
    syusave.setSatisiskonto(satisiskontop);
    }
    syusave.setSatisadresi(satisadresip);
    
    
    syusave.setSatistarihi(LocalDate.now());
    syusave.setUrunAdedi(urunAdedip);
    syusave.setUrunAdi(urunAdip);
    syusave.setUrunFiyati(satisFiyatip);
    syusave.setUrunkaydi(false);
    sService.salesSaved(syusave);
    map.addAttribute("productData", pr);
    map.addAttribute("mustidSave", new Musteri());
    return "redirect:/musteriIndex/"+idMusterip+"/sales";
  }
  @GetMapping(path = "/musteriIndex/save")
  public String productEdit(ModelMap map) {
    
      String uyari = "Kaydet Tuşu";
      map.addAttribute("MusterideKalmakicin", new Musteri());
      map.addAttribute("kaydetbutonucalisiyormu",uyari );
      return "redirect:";
  }
  @GetMapping(path = "/musteriIndex/{ids}/deleteSales")
    public String salesDelete(@PathVariable("ids") Long idsatisyapilan, ModelMap map){
        sService.delete(idsatisyapilan);
        return "redirect:/musteriIndex";
    }
    @GetMapping( path = "/musteriIndex/{ids}/edit")
  public String salesUpdatePages(@PathVariable("ids") Long idsatisyapilan,ModelMap map) {
    Sales sales = sService.getById(idsatisyapilan);
    map.addAttribute("salesListEdit", sales);
    return "sales/salesEdit";
  }
  @PostMapping("/musteriIndex/{ids}/edit")
  public String salesUpdate (@PathVariable("ids") Long idsatisyapilan,
                             @ModelAttribute("salesListEdit") Sales sales) {
        //sales.setIdsatisyapilan(idsatisyapilan);
       
        sales.setIdsatisyapilan(idsatisyapilan);
        sService.salesSaved(sales);
        return "redirect:/sales";

  }
  
}
