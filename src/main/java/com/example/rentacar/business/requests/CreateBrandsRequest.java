package com.example.rentacar.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandsRequest { // request (istek) : istemcinin sunucuya ilettiği taleptir
    @NotNull
    @NotBlank
    @Size(min =3, max = 20) //Controller'da @Valid sayesinde kontrol yapılır ve ğeer buna uyulmuyorsa  MethodArgumentNotValidException döndürür
    private String name; //sadece name istenir id istemeye gerek yok çünkü son kullanıcı otomatik artan bir bilgiyi bilmesi gerekmez
}
