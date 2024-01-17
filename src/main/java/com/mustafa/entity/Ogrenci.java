package com.mustafa.entity;

import com.mustafa.entity.enums.Cinsiyet;
import com.mustafa.entity.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_ogrenci")
public class Ogrenci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column( name = "ad", nullable = false,length = 50)
    String ad;
    @Column( name = "soyad", nullable = false,length = 50)
    String soyad;
    @Enumerated
    Cinsiyet cinsiyet;
    Integer yas;
    @Embedded
    BaseEntity baseEntity;

}
