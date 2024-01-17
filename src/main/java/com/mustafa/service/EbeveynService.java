package com.mustafa.service;

import com.mustafa.entity.BaseEntity;
import com.mustafa.entity.Ebeveyn;
import com.mustafa.entity.enums.State;
import com.mustafa.repository.EbeveynRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EbeveynService {
    private final EbeveynRepository ebeveynRepository;
    public void save(Ebeveyn ebeveyn){
        ebeveyn.setBaseEntity(BaseEntity.builder()
                .state(State.AKTIF)
                .createAt(System.currentTimeMillis())
                .updateAt(System.currentTimeMillis())
                .build()
        );

        ebeveynRepository.save(ebeveyn);
    }

    public List<Ebeveyn> findAll() {
        return ebeveynRepository.findAll();
    }
}
