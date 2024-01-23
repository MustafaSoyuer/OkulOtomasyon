package com.mustafa.service;


import com.mustafa.dto.request.OgretmenSaveRequestDto;
import com.mustafa.dto.response.OgretmenResponseDto;
import com.mustafa.entity.Ogretmen;
import com.mustafa.repository.OgretmenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OgretmenService {
    private final OgretmenRepository ogretmenRepository;
//    public OgretmenService(OgretmenRepository ogretmenRepository){
//        this.ogretmenRepository = ogretmenRepository;
//    } lombok yazıyor bunu ->@Requ...Const..


    public void save(OgretmenSaveRequestDto dto){
        Ogretmen ogretmen = Ogretmen.builder()
                .ad(dto.getAd())
                .soyad(dto.getSoyad())
                .brans(dto.getBrans())
                .build();
        ogretmenRepository.save(ogretmen);
    }

    public List<OgretmenResponseDto> findAll(){
        List<OgretmenResponseDto> result = new ArrayList<>();
        ogretmenRepository.findAll().forEach(o->
            result.add(
                    OgretmenResponseDto.builder()
                            .id(o.getId())
                            .ad(o.getAd())
                            .soyad(o.getSoyad())
                            .brans(o.getBrans())
                            .build()
            )
        );
                return result;
    }

}
