package com.example.rentacar.business.abstracts;

import com.example.rentacar.business.requests.CreateBrandsRequest;
import com.example.rentacar.business.requests.UpdateBrandsRequest;
import com.example.rentacar.business.responses.GetAllBrandsResponse;
import com.example.rentacar.business.responses.GetByIdBrandsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  //BrandService  sınıfını Spring bileşenine dönüştürmek için @Service anotasyonunu kullanılır
public interface BrandService {
    List<GetAllBrandsResponse> getAll();

    GetByIdBrandsResponse getById(int id);

    void add(CreateBrandsRequest createBrandsRequest);

    void update(UpdateBrandsRequest updateBrandsRequest);

    void delete(int id);
}
