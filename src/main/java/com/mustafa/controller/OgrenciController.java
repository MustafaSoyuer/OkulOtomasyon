package com.mustafa.controller;

import com.mustafa.service.OgrenciService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ogrenci")
@RequiredArgsConstructor
public class OgrenciController {
    private final OgrenciService ogrenciService;

}
