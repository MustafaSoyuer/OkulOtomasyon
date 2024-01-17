package com.mustafa.repository;

import com.mustafa.entity.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OgrenciRepository extends JpaRepository<Ogrenci,Long> {
}
