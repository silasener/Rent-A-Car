package com.example.rentacar.business.abstracts;

import com.example.rentacar.business.requests.CreateModelsRequest;
import com.example.rentacar.business.responses.GetAllModelsResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();

    void add(CreateModelsRequest createModelsRequest);
}
