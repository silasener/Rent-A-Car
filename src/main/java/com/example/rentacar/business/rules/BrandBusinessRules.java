package com.example.rentacar.business.rules;

import com.example.rentacar.common.utilities.exceptions.BusinessException;
import com.example.rentacar.dataAccess.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRules { // Brand varlığına ait kurallar yazılır

    private BrandRepository brandRepository;

    public void checkIfBrandNameExists(String name){ //exists == var mı demektir, brand tablosunda name var mı metodu

        if(this.brandRepository.existsByName(name)){ // brand tablosunda name varsa true , yoksa false döner. BrandRepo'da yazdığım Jpa Key word sayesinde kullanılır

            throw new BusinessException("Brand name tabloda zaten bulunmakta"); // RuntimeException'dan extend edilerek exception paketinde class oluşturuldu

        }
    }
}
