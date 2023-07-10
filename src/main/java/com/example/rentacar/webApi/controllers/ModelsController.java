package com.example.rentacar.webApi.controllers;


import com.example.rentacar.business.abstracts.ModelService;
import com.example.rentacar.business.requests.CreateBrandsRequest;
import com.example.rentacar.business.requests.CreateModelsRequest;
import com.example.rentacar.business.responses.GetAllBrandsResponse;
import com.example.rentacar.business.responses.GetAllModelsResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Java sınıfını RESTful web servisi olarak işaretlemek için kullanılır. (erişim noktası)
// HTTP protokolü üzerinden gelen isteklerin metodlara eşleştirilmesini sağlar ve bu metodlar sonucunda dönen verileri uygun formatlarda (JSON, XML vb.) yanıt olarak döndürebilir.
@RequestMapping("/api/models")
//Controller sınıfı üzerinde kullanıldığında, tüm metotlar için varsayılan bir URL yolu belirtir
@AllArgsConstructor
public class ModelsController {

    private ModelService modelService;

    @GetMapping()  //getmapping swagger'da get bölümü oluşturup veri görüntülenmesi ekranını sağlar
    //  URL yolunu özelleştirir örneğin burada ......./api/brands çağrıldığında tetiklenirek çalışır
    public List<GetAllModelsResponse> getAll() {

        return modelService.getAll();
    }

    @PostMapping() //postmapping swagger'da post bölümü oluşturup veri eklemeyi sağlar
    @ResponseStatus(code = HttpStatus.CREATED) //Post isteğinin başarıyla çağrıldığında  yeni  kaynak oluşursa yanıtın HTTP durumunun "201" olmasını sağlar
    public void add(@RequestBody() @Valid() CreateModelsRequest createModelsRequest){
        //@Valid  nesnenin (createModelsRequest) doğrulama işleminden geçmesi gerektiğini belirtir ,  @NotNull @Size(min=3,max=20) kontrolünü sağlar
        this.modelService.add(createModelsRequest);
    }
}
