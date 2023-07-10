package com.example.rentacar.common.utilities.mappers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService {

    private ModelMapper modelMapper;

    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true)
                //ModelMapper'ın alan eşleştirmesindeki belirsizlikleri görmezden gelir
                //Yani birden fazla eşleşen alan varsa bu ayar belirsizlikleri önemsemeden devam etmeyi sağlar.

                .setMatchingStrategy(MatchingStrategies.LOOSE);
        //daha esnek olmayı sağlar ve alanlar arasında tam eşleşme olmasa bile uyum sağlamaya çalışır.
        return this.modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true)
                //ModelMapper'ın alan eşleştirmesindeki belirsizlikleri görmezden gelir
                //Yani birden fazla eşleşen alan varsa bu ayar belirsizlikleri önemsemeden devam etmeyi sağlar.

                .setMatchingStrategy(MatchingStrategies.STANDARD);
        //daha katı bir eşleştirme sağlar ve alan adları, büyük/küçük harf duyarlılığı ve alan türleri gibi faktörleri dikkate alır.

        return this.modelMapper;
    }
}
