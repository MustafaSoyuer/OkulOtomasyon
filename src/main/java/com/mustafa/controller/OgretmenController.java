package com.mustafa.controller;

import com.mustafa.dto.request.OgretmenSaveRequestDto;
import com.mustafa.dto.response.OgretmenResponseDto;
import com.mustafa.entity.Ogretmen;
import com.mustafa.service.OgretmenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ogretmen")
@RequiredArgsConstructor
public class OgretmenController {
    private final OgretmenService ogretmenService;

    /**
     * public String -> string döner: response 200 OK.
     * istisna olsa ne olacak?
     * siz elle hatalı bir durum rapor ermek isteseniz ne olacak?
     * Boş bir nesne ile cevapp veremezsiniz. Bu nedenle cevabı
     * manipule edebileceğiniz bir sınıfa ihtiyacınız var.
     * Response<Entity> -> istenilen türde cevap döner.
     * 200, 300, 400, 500 şeklinde cevap dönebilir.
     *
     * Post->
     * form element(form-data) içinde
     * @RequestBody raw -> json body
     * *********
     * *********
     * *** DİKKAT!!!!!
     * controller, iş katmanı değildir bu nedenle burada iş yapmıyoruz.
     * temel amacı işlenmiş veriyi almak ve servise iletmektir. Bu nedenle
     * burada sadece tutarlı veriyi iletme işlemi yapıyorsunuz.
     */
    @PostMapping("/add")
    public ResponseEntity<Void> save(@RequestBody OgretmenSaveRequestDto dto){
//        Ogretmen ogretmen = Ogretmen.builder()
//                .ad(dto.getAd())
//                .soyad(dto.getSoyad())
//                .brans(dto.getBrans())
//                .build();
//        ogretmenService.save(ogretmen);
        ogretmenService.save(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<OgretmenResponseDto>> getAll(){
        return ResponseEntity.ok(ogretmenService.findAll());
    }
}
