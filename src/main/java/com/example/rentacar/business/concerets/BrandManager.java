package com.example.rentacar.business.concerets;

import com.example.rentacar.business.abstracts.BrandService;
import com.example.rentacar.business.requests.CreateBrandsRequest;
import com.example.rentacar.business.requests.UpdateBrandsRequest;
import com.example.rentacar.business.responses.GetAllBrandsResponse;
import com.example.rentacar.business.responses.GetByIdBrandsResponse;
import com.example.rentacar.business.rules.BrandBusinessRules;
import com.example.rentacar.common.utilities.mappers.ModelMapperService;
import com.example.rentacar.dataAccess.abstracts.BrandRepository;
import com.example.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll(); // veritabanındaki bütün bilgiler brands listesine eşitlenir

       /* List<GetAllBrandsResponse> brandsResponse= new ArrayList<GetAllBrandsResponse>(); //boş liste oluşturulur
        // sadece kullanıcının istediği verileri elde etmek için; (istenen veriler Id ve Name ise)

        for (Brand brand: brands) { //veritabanındaki her veri tek tek gezilir
            GetAllBrandsResponse responseItem=new GetAllBrandsResponse(); // her veri satırı için bir eleman oluşturulur
            responseItem.setId(brand.getId()); // o elemanın Id'si , veritabanındaki verinin Id'sine eşitlenir
            responseItem.setName(brand.getName()); // o elemanın Name'i , veritabanındaki verinin Name'ine eşitlenir
            brandsResponse.add(responseItem); // her veri satırına ait isteği karşılayan veri formatı brandsResponse listesinde tutulur
        }
        */

        List<GetAllBrandsResponse> brandsResponse = brands.stream() //brands listesini bir Stream'e dönüştürür ( ardışık işlemleri yapabilmek için).
                .map(brand -> this.modelMapperService.forResponse(). //ModelMapper nesnesi alınır dönüşüm işlemlerindeki ayarlarını içeren örneği döndürür.
                        map(brand, GetAllBrandsResponse.class)) //her bir brand nesnesini GetAllBrandsResponse sınıfına dönüştürmek için kullanılır.
                .collect(Collectors.toList());// Stream'in sonucunu bir List haline getirmek için collect işlemi kullanılır.
        // Collectors.toList() metodu, Stream'deki öğeleri bir List'e toplar.

        return brandsResponse;
    }

    @Override
    public GetByIdBrandsResponse getById(int id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow();  //veri tabanında ilgili Id aranır , bulunamazsa hata fırlatır
        GetByIdBrandsResponse response = this.modelMapperService.forResponse().map(brand, GetByIdBrandsResponse.class);
        // veri tabanında ilgili Id'ye sahip brand bulunursa bu brand .map özelliği ile GetByIdBrandsResponse.class tipine dönüştürülür

        return response;
    }

    @Override
    public void add(CreateBrandsRequest createBrandsRequest) {
        // brand tablosunda name varsa true , yoksa false döner. BrandRepo'da yazdığım Jpa Key word sayesinde kullanılır
        this.brandBusinessRules.checkIfBrandNameExists(createBrandsRequest.getName());



        Brand brand = this.modelMapperService.forRequest().map(createBrandsRequest, Brand.class); //request Brand tipine dönüştürülür
        //.map(dönüştürülecek kaynak, dönüştürülmesi gereken class tipi) parametrelerini alır

       /* Brand brand=new Brand();
        brand.setName(createBrandsRequest.getName());  // oluşturulan brand'in adı parametrede gelen ile set edilir
        */

        this.brandRepository.save(brand); // veri tabanına yeni brand adıyla beraber kaydedilir.
    }

    @Override
    public void update(UpdateBrandsRequest updateBrandsRequest) {
        Brand brand = this.modelMapperService.forRequest().map(updateBrandsRequest, Brand.class); //request Brand tipine dönüştürülür
        this.brandRepository.save(brand); //Brand update hali veri tabanına kaydedilir

    }

    @Override
    public void delete(int id) {
        this.brandRepository.deleteById(id);  //ilgili Id'ye göre veri tabanından veriyi siler
        // this.brandRepository.delete(); fonksiyonu kullanmak için parametre brand lazımdı o yüzden deleteById(id) kullanıldı

    }


}
