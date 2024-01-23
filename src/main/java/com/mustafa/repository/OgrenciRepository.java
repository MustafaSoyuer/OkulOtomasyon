package com.mustafa.repository;

import com.mustafa.entity.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * TODO: REPOSITORY, Temel veritabanı işlemlerini yapmak için kullandığımız sınfı ifade eder.
 * Spring bu sınıftan bir Bean yaratır. Ancak burada dikkat edilmesi gerekli olan bir husus vardır.
 * Spring Boot 3.x den önceki sürümlerde bu sınıfta bir bean yaratılabilmesi için @Repository
 * anatasyonu eklemek zorunlu idi, Ancak artık ilgili sınıf JpaRepository den extends olması
 * yeterli olmaktadır
 *
 */
//@Repository
public interface OgrenciRepository extends JpaRepository<Ogrenci,Long> {

    /**
     * TODO: Jpa Keywords, spring kendisine özgü kelimelerin bir araya gelmesi ile olusan
     * methodların gövdelerini oluşturmabilmektedir.
     * Select * from tblogrenci where ad=? and soyad_? and yas>? and yas<?
     *     List<Ogrenrci> findAllByAdAndSoyadAndBetweenYas(String ad,String soyad,Integer yasStart,Integer yasEnd);
     *     TODO: Jpa Repository Keywordleri kullanılarak nasıl sorgu methodu oluşturuyoruz.
     *     1-[ find - count - get - delete ] bu key wordler den birisi ile başlaması gereklidir.
     *     2- By: ilk secilecek property belirleniyor.
     *     3- [Entity değişkeninin adı] Diikat entity değişkeni yazılırken mutlaka basharfi büyük olacak şekilde
     *     yazılmalıdır. Büyüklü küçüklü olsa bile ilk fark büyük diğer kelimelerin harleri
     *      örn:
     *      User{
     *          ad, -> findByAd
     *          adSoyad, -> findBy AdSoyad
     *          adsoyad, ->fingByAdsoyad
     *      }
     *      4- Eğer başka sorgular ek parametreler eklenecek ise, And, Or [In,Not,True] ile devam edilmelidir.
     *      5- Eğer method yazımında parametre talep edecek şekilde oluşturulmuş ise, parantezler içinde
     *      yazılan parametrelerin sırası ile eklenmesi gerekmektedir. Aşağıdaki örnekte olduğu gibi method
     *      adında Ad ve Soyad için iki parametre talep edildiği açıktır.
     *      6- Her methodun geri dönüş tipi belirtilmelidir. Burada sorgu neticesinde ne talep ediliyor ise
     *      ona göre geri dönüş tipi belirleyiniz.
     *
     *
     */

    /**
     *  select * from where ad=? and soyad=?
     */

    Ogrenci findByAdAndSoyad(String ad,String soyad);
    Optional<Ogrenci> findOptinalByAdAndSoyad(String ad,String soyad);

    /**
     *  select * from tblogrenci where ad=?
     */
    List<Ogrenci> findAllByAd(String ad);

    /**
     *  select * from tblogrenci where yas>?
     */
    List<Ogrenci> findAllByYasGreaterThan(Integer yas);

    /**
     *  Like -> _%
     *  select * from tblogrenci where ad like ? -> '%al'
     */
    List<Ogrenci> findAllByAdLike(String ad);
    List<Ogrenci> findAllByAdLikeIgnoreCase(String ad); // ilike

    /**
     * StartingWith
     * Aranılan ifade ile başlıyor mu?
     * search: ay
     * select * from tblogrenci where ad like 'ay%'
     */
    List<Ogrenci> findAllByAdStartingWith(String ad);

    /**
     *      select * from tblogrenci where ad like 'a%' and soyad like 'ab%'
     */
    List<Ogrenci> findAllByAdStartingWithAndSoyadStartingWith(String ad,String soyad);

    /**
     * Sıralama işlemleri
     * select * from tblogrenci order by ad [desc-asc]
     * OrderBy
     * asc -> a...z, 0...9
     * desc -> z...a, 9...0
     */
    List<Ogrenci> findByOrderByYas(); //a..z
    List<Ogrenci> findAllByOrderByYasDesc(); //z..a

    /**
     *      select * from tblogrenci where ad like 'a%' order by soyad desc
     */
    List<Ogrenci> findAllByAdStartingWithOrderBySoyadDesc(String ad);

    /**
     *        Sorgularda gelen detayını kısıtlanması
     *        select * from tblogrenci limit 3 -(TOP)
     */

    List<Ogrenci> findTop5ByYasGreaterThan(Integer yas);

    /**
     *      select * from tblogrenci order by yas desc limit 1
     */
    Ogrenci findTopByOrderByYasDesc();

    /**
     *  Betweem -> iki değer aralığında sorgulamalar için
     *  DİKKAT!!!  --> yas<=? and yas>=?  [50,60] ikiside de dahil
     */
    List<Ogrenci> findAllByYasBetween(Integer start, Integer end);

    /**
     * select * from tblogrenci where ad like 'a%' and yas<=? and yas>=?
     */
    List<Ogrenci> findAllByAdStartingWithAndYasBetween(String ad, Integer start, Integer end);


    /**
     * DİKKAT!!!
     * sorgulamalar da her zaman değer gelmeyebilir bu nedenle çektiğiniz bilgi null atacaktır.
     * Bu nedenle null check yapmamız gerekecek. Bu doğru bir yöntem değildir.
     * Bunu yönetmek için Optional
     *
     *                                      ÖNEMLİ !!!!
     * Optional kullanırken DB den mutlaka en fazla  1 kayıt geleceğinden emin olun.
     * neden? -> çünkü DB den birden fazla kayıt gelirse sorgu sonucu Optional a atayamaz ve
     * uygulama exception fırlatır.
     */
    Optional<Ogrenci> findOptionalByAdAndSoyadAndYas(String ad, String soyad, Integer yas);

    /**
     * Boolean değeri sorgulamak için True / False direkt olarak kullanılabilir.
     * select * from tblogrenci where isactive = true
     */
//    List<Ogrenci> findAllByIsActiveTrue(); // aktif öğrenciler
//
//    List<Ogrenci> findAllByIsActiveFalse(); // pasif öğrenciler
//
//    List<Ogrenci> findAllByIsactive(Boolean isActive);

    List<Ogrenci> findDistinctByAdAndSoyad(String ad, String soyad);

    List<Ogrenci> findAllByYasIsNull();
    List<Ogrenci> findAllByYasIsNotNull();

    /**
     * select * from tblogrenci where ad like '%?%'
     */
    List<Ogrenci> findAllByAdContaining(String ad);

    /**
     * Performanslı aramalarda sıklıkla kullanıyorum.
     *
     * select * from tblogrenci where ad in(?,?,?,?)
     */
    List<Ogrenci> findAllByAdIn(List<String> adlar);

    /**
     * HQL -> Hibernate Query Language
     * JPQL -> Jakarta Persistence Query Language
     * NativeSQL -> Native Structure Query Language
     */

    @Query("select o from Ogrenci o where o.soyad= ?1")
    List<Ogrenci> senGetirBanaTumVerileriSoyadinaGore(String soyad);

    @Query("select o from Ogrenci o where o.ad = ?1 and o.yas= ?2")
    List<Ogrenci> birBakBakayimOgrencilerSiniftaMi(String ad, Integer yas);

    /**
     * Native SQL
     */
    @Query(nativeQuery = true, value = "select * from Ogrenci")
    List<Ogrenci> tumOgrencileriGetir();

    /**
     * select count(*) from tblogrenci where yas>15
     */
    @Query("select count(o) from Ogrenci o where o.yas> ?1")
    Integer kacOgrenciVar(Integer yas);

}
