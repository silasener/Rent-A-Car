package com.example.rentacar.business.concerets;

import com.example.rentacar.business.abstracts.ModelService;
import com.example.rentacar.business.requests.CreateModelsRequest;
import com.example.rentacar.business.responses.GetAllModelsResponse;
import com.example.rentacar.common.utilities.mappers.ModelMapperService;
import com.example.rentacar.dataAccess.abstracts.ModelRepository;
import com.example.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;


    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = modelRepository.findAll();

        List<GetAllModelsResponse> modelsResponse = models.stream() //models listesini bir Stream'e dönüştürür ( ardışık işlemleri yapabilmek için).
                .map(model -> this.modelMapperService.forResponse() //ModelMapper nesnesi alınır dönüşüm işlemlerindeki ayarlarını içeren örneği döndürür.
                        .map(model, GetAllModelsResponse.class)) //her bir model nesnesini GetAllModelsResponse sınıfına dönüştürmek için kullanılır.
                .collect(Collectors.toList()); //Stream'in sonucunu bir List haline getirmek için collect işlemi kullanılır.
        //Collectors.toList() metodu, Stream'deki öğeleri bir List'e toplar.

        return modelsResponse;
    }

    @Override
    public void add(CreateModelsRequest createModelsRequest) {
        Model model = this.modelMapperService.forRequest().map(createModelsRequest, Model.class); //request Model tipine dönüştürülür
        //.map(dönüştürülecek kaynak, dönüştürülmesi gereken class tipi) parametrelerini alır

       /* Model model=new Model();
        model.setName(createModelsRequest.getName());  // oluşturulan brand'in adı parametrede gelen ile set edilir
        */

        this.modelRepository.save(model);
    }
}
