package com.mustafa.service;

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
        ogrenci.getBaseEntity().setState(State.AKTIF);
        ogrenci.getBaseEntity().setCreateAt(System.currentTimeMillis());
        ogrenci.getBaseEntity().setUpdateAt(System.currentTimeMillis());
        ogrenciRepository.save(ogrenci);
    }

    public List<Ogrenci> findAll(){
        return ogrenciRepository.findAll();
    }


}
