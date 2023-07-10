package com.example.rentacar.common.utilities.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;

public interface ModelMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();
}
