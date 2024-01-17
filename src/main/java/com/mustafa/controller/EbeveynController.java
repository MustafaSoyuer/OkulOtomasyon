package com.mustafa.controller;

import com.mustafa.entity.Ebeveyn;
import com.mustafa.service.EbeveynService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ebeveyn")
@RequiredArgsConstructor
public class EbeveynController {
    private final EbeveynService ebeveynService;

    /**
     *  Controller sayfasından dışarıya hizmet etme işlemleri End-Point ile yapılır.
     *  bir endpoint şu şekilde görünür
     *  [http or https]://[IP-ADDRESS]:[optinal:DEV,API,TEST]/[optinal:VERSION]/[CLASS:name]/[ACTION:name]
     *  http://localhost:8080/dev/v1/ebeveyn/save
     *  port dahil kısım ilgili sunucuda yayın ypan uygulamaya ulaşmak için kullanılır. diğerksıımlar geliştiriciye göre
     *  değişim gösterir. Asıl olan class a erişim ve içindeki methodları tetiklemek için kullanılan parametrelerdir.
     *
     *
     */

    /**
     * GetMapping -> endPoint e gelen isteğin GET türünden olması gerektiğini niteler ve belirtilen adrese
     * gelen isteği yakalr. Böylelikle ilgili methodun tetiklenmesini sağlar.
     *
     * http://localhost:8080/ebeveyn/save
     */

    @GetMapping("/save")
    public void save(){
        System.out.println("Ebeveyn kayit methodu calisti");
        ebeveynService.save(Ebeveyn.builder()
                        .ad("Ahmet")
                        .adres("Ankara")
                        .soyad("TAS")
                        .telefon("0 555 777 7777")
                .build());
        ebeveynService.save(Ebeveyn.builder()
                .ad("Canan")
                .adres("Izmir")
                .soyad("KAYI")
                .telefon("0 555 777 7778")
                .build());
        ebeveynService.save(Ebeveyn.builder()
                .ad("Bulent")
                .adres("Bursa")
                .soyad("BAKI")
                .telefon("0 555 777 7779")
                .build());
        ebeveynService.save(Ebeveyn.builder()
                .ad("Tuna")
                .adres("Ankara")
                .soyad("TEK")
                .telefon("0 555 777 7776")
                .build());
    }

    /**
     * http://localhost:8080/ebeveyn/get-all
     * @return
     */
    @GetMapping("/get-all")
    public List<Ebeveyn> findAll(){
        return ebeveynService.findAll();
    }

    /**
     * http://localhost:8080/ebeveyn/get-all-by-adres
     * GET isteklerinde bilgi transferi isteğin header kısmında taşınır.
     * yani URL adresine bilgi ekleyerek göndermeniz gereklidir. bu işlem
     * end-point in sonuna ? ekleyerek yapılır. ? den sonra gönderilmek istenilen
     * parametrenin adı ? değer şeklinde yazılır.
     * @param adres
     * @return
     */
    @GetMapping("/get-all-by-adres?adres=Ankara")
    public List<Ebeveyn> findAllByAdres(String adres){
        return ebeveynService.findAll()
                .stream().filter(x->x.getAdres().contains(adres))
                .toList();
    }

    /**
     * GET isteklerinde bilgi eklemesi yapılırken, header içinde birden fazla bilgi taşıyabiliriz.
     * Bu bilgileri eklemek için ifadeler arasına & işareti eklenir
     * http://localhost:8080/ebeveyn/get-all-by-adres-and-ad?adres=a&ad=a
     * @param adres
     * @param ad
     * @return
     */
    @GetMapping("/get-all-by-adres-and-ad")
    public List<Ebeveyn> findAllByAdresAndAd(String adres, String ad){
        return ebeveynService.findAll()
                .stream().filter(x->x.getAdres().contains(adres) && x.getAd().contains(ad))
                .toList();
    }


}
