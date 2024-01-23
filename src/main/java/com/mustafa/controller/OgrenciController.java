package com.mustafa.controller;

import com.mustafa.entity.Ogrenci;
import com.mustafa.entity.enums.Cinsiyet;
import com.mustafa.service.OgrenciService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ogrenci")
@RequiredArgsConstructor
public class OgrenciController {
    private final OgrenciService ogrenciService;

    /**
     *  POST isteği, end-point tüm istek türlerinde aynıdır, değişmez. Sadece client ın geliş şekli değişir.
     *  http://localhost:8080/ogrenci/save
     *          DİKKAT!!!
     *  POST isteğinde parametreler BODY içinde sunucuya iletilir. Böylece GET isteğinde açık bir şekilde
     *  iletilen datalar güvenli bir şekilde sunucuya iletilmiş olur.
     *
     */
    @PostMapping("/save")
    public void save(String ad, String soyad, Integer yas, Cinsiyet cinsiyet){
        System.out.println();
        Ogrenci ogrenci = Ogrenci.builder()
                .ad(ad)
                .soyad(soyad)
                .yas(yas)
                .cinsiyet(cinsiyet)
                .build();
        ogrenciService.save(ogrenci);
    }

    @GetMapping("/get-all")
    public List<Ogrenci> getAll(){
        System.out.println();
        return ogrenciService.findAll();
    }

    @GetMapping("/get-all-by-ad")
    public List<Ogrenci> gelAllByAd(String ad){
        return ogrenciService.findAllByAd(ad);
    }

    @GetMapping("/get-all-by-ad-like")
    public List<Ogrenci> getAllByAdLike(String ad){
        return ogrenciService.findAllByAdLike("%"+ad+"%");
    }

    @GetMapping("/get-all-by-ad-starting-with")
    public List<Ogrenci> getAllByAdStartingWith(String ad){
        return ogrenciService.findAllByAdStartingWith(ad);
    }

    @GetMapping("/get-all-by-ad-soyad-starting-with")
    public List<Ogrenci> getAllByAdAndSoyadStartingWith(String ad, String soyad){
        return ogrenciService.findAllByAdStartingWithAndSoyadStartingWith(ad,soyad);
    }




}
