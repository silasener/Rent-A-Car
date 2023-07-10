package com.example.rentacar.dataAccess.abstracts;

import com.example.rentacar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository  extends JpaRepository<Brand,Integer>{

    //spring jpa key words
    boolean existsByName(String name); //Jpa otomatik olarak true ya da false döndüren bir sorgu oluşturarak veri tabanına parametresine göre inceler
    //exists == var mı demektir
}
