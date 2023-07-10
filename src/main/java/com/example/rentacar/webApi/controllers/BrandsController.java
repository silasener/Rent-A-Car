package com.example.rentacar.webApi.controllers;

import com.example.rentacar.business.abstracts.BrandService;
import com.example.rentacar.business.requests.CreateBrandsRequest;
import com.example.rentacar.business.requests.UpdateBrandsRequest;
import com.example.rentacar.business.responses.GetAllBrandsResponse;
import com.example.rentacar.business.responses.GetByIdBrandsResponse;
import com.example.rentacar.entities.concretes.Brand;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Java sınıfını RESTful web servisi olarak işaretlemek için kullanılır. (erişim noktası)
// HTTP protokolü üzerinden gelen isteklerin metodlara eşleştirilmesini sağlar ve bu metodlar sonucunda dönen verileri uygun formatlarda (JSON, XML vb.) yanıt olarak döndürebilir.
@RequestMapping("/api/brands")
//Controller sınıfı üzerinde kullanıldığında, tüm metotlar için varsayılan bir URL yolu belirtir
public class BrandsController {

    private BrandService brandService;

    @Autowired
    //Spring IoC  konteynırı  uygun bağımlılıkların otomatik olarak enjekte edilmesini sağlayarak yönetimi kolaylaştırır ve bağımlılıkların elle atanmasıyla ilgili kod tekrarları azalır.
    public BrandsController(BrandService brandService) { //Parametreyi kontrol ederek uygulama taranır ve onun implement edildiği class bulunarak işlem yapılır
        this.brandService = brandService;
    }



    @GetMapping()  //getmapping swagger'da get bölümü oluşturup veri görüntülenmesi ekranını sağlar
    //  URL yolunu özelleştirir örneğin burada ......./api/brands çağrıldığında tetiklenirek çalışır
    public List<GetAllBrandsResponse> getAll() {
        return brandService.getAll();
    }




    @GetMapping("/{id}")  //süslü parantez içerisinde yazmak değişken anlamına gelir
    //  URL yolunu özelleştirir örneğin burada ......./api/brands çağrıldığında tetiklenirek çalışır
    public GetByIdBrandsResponse getById(@ PathVariable int id){ //@ PathVariable url yoluna girilen değeri  değişkene çevirip buna göre fonksiyonun çalışmasını sağlar
        return this.brandService.getById(id);
    }




    @PostMapping() //postmapping swagger'da post bölümü oluşturup veri eklemeyi sağlar
    @ResponseStatus(code = HttpStatus.CREATED) //Post isteğinin başarıyla çağrıldığında  yeni  kaynak oluşursa yanıtın HTTP durumunun "201" olmasını sağlar
    public void add(@RequestBody @Valid CreateBrandsRequest createBrandsRequest){
        this.brandService.add(createBrandsRequest);
    }




    @PutMapping // ilgili metodun veri güncellemesini sağlar
    public void update(@RequestBody UpdateBrandsRequest updateBrandsRequest){ //@RequestBody gelen verileri ilgili Java nesnesine dönüştürür
        this.brandService.update(updateBrandsRequest);
    }


    @DeleteMapping("/{id}") // ilgili metodun veri silmesini sağlar
    public void delete(@PathVariable int id){ //@ PathVariable url yoluna girilen değeri  değişkene çevirip buna göre fonksiyonun çalışmasını sağlar
        this.brandService.delete(id);
    }


}
