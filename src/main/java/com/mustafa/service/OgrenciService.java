package com.mustafa.service;

import com.mustafa.entity.BaseEntity;
import com.mustafa.entity.Ogrenci;
import com.mustafa.entity.enums.State;
import com.mustafa.repository.OgrenciRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // constuctor yazmak yerine
public class OgrenciService {
    /**
     *  TODO: DİKKAT!!! IDEMPOTENT DESING PATTERN
     *  idempotent terimi; bir işlemin aynıyla tekrar tekrar uygulansa dahi, sonuclarının ve sunucu tarafındaki
     *  işlemlerinin değişmesi durumudur. Idempotent bir işlemi tekrar tekrar uygulama sonucu değiştirmemelidir.
     *  Uygulamalarımızda, tutarlılık ve ön görülebilirlik için kullanılır.
     *
     *  HTTP isteklerinde Idempotent olanlar:
     *  GET, PUT, DELETE
     *
     *  Idempotent sistememizde olusabilecek hatalrla basacıkmada yardımcı olabilecek bir tasarım prensibidir.
     *
     *
     */
    /**
     *      Bir bağımlılığın Enjekte edilmesi için temelde kullandığımız 2 yöntem vardır.
     *      1- @Autowired ile işaretlemek
     *      2- ConstructorInjection ile spring application context in DI ile bağımlılık enjeksiyonu yapmak
     */

//    1. Yöntem
//    @Autowired // gerekli olanları kendi oluşturur
//    private OgrenciRepository ogrenciRepository;

//            2.Yöntem - ConstructorInjection
    private final OgrenciRepository ogrenciRepository;
    // DİKKAT!!!!!!
    //constructor u yazmadan yazılmış gibi kullanmak için
    //lombok anatasyonlarını kullanabilirsiniz.
//    public OgrenciService(OgrenciRepository ogrenciRepository){
//        this.ogrenciRepository = ogrenciRepository;
//    }

    public void save(Ogrenci ogrenci){
        ogrenci.setBaseEntity(
                BaseEntity.builder()
                        .state(State.AKTIF)
                        .createAt(System.currentTimeMillis())
                        .updateAt(System.currentTimeMillis())
                        .build()
        );
        ogrenciRepository.save(ogrenci);
    }

    public List<Ogrenci> findAll(){
        return ogrenciRepository.findAll();
    }

    public List<Ogrenci> findAllByAd(String ad){
        return ogrenciRepository.findAllByAd(ad);
    }


    public List<Ogrenci> findAllByAdLike(String ad) {
        return ogrenciRepository.findAllByAdLike(ad);
    }

    public List<Ogrenci> findAllByAdStartingWith(String ad) {
        return ogrenciRepository.findAllByAdStartingWith(ad);
    }

    public List<Ogrenci> findAllByAdStartingWithAndSoyadStartingWith(String ad,String soyad){
        return ogrenciRepository.findAllByAdStartingWithAndSoyadStartingWith(ad,soyad);
    }


}
