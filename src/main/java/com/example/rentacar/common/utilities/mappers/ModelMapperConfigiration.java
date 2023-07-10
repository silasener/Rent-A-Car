package com.example.rentacar.common.utilities.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Spring çalışınca otomatik bir kere ModelMapper new'ler hep kullanılır
@Configuration  // Sınıfı Spring konteynırına (IOC) bileşen  olarak projeye eklemek için kullanılır
public class ModelMapperConfigiration {

    @Bean //IOC konteynırına sınıfı bileşen olarak kaydeder
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

