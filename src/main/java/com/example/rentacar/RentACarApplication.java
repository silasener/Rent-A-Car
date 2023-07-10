package com.example.rentacar;

import com.example.rentacar.common.utilities.exceptions.BusinessException;
import com.example.rentacar.common.utilities.exceptions.ProblemDetails;
import com.example.rentacar.common.utilities.exceptions.ValidationProblemDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@SpringBootApplication
@RestControllerAdvice // Bir sınıfa veya yönteme uygulandığında, global bir istisna (exception) işleme mekanizması sağlar
//istemciden gelen isteklerde oluşabilecek istisnaları merkezi bir şekilde yönetebilir ve UYGUN YANITLAR DÖNDÜRMEYİ SAĞLAR
public class RentACarApplication {

    // localhost:8080/api/brands/getall
    // localhost:8080/swagger-ui/index.html    webde göstermek için ui arayüz linki : swagger (dependcy ile)

    public static void main(String[] args) {
        SpringApplication.run(RentACarApplication.class, args);
        //ModelMapperConfig'de new yapar @Bean'e kaydeder
    }


        // @RestControllerAdvice ile beraber kullanılır
        @ExceptionHandler //istisna yönetimi ve uygun yanıtların döndürülmesi konusunda kontrolcü sınıfların işlevlerini özelleştirir
        @ResponseStatus(code = HttpStatus.BAD_REQUEST) //400 kodlu  HTTP isteğinin sunucu tarafından doğru bir şekilde anlaşılmadığını ve geçersiz bir istek olduğunu belirtmek için kullanılır
        public ProblemDetails handleBusinessException( BusinessException businessException) { //hata yakaladığında kullancııya bütün bilgiler verilmemesi için yapılan özelliştirme fonksiyonu
            ProblemDetails problemDetails=new ProblemDetails();
            problemDetails.setMessage(businessException.getMessage()); //kullancıya sadece ilgili mesaj verilir , hepsi verilmez

            return problemDetails;
        }

    // @RestControllerAdvice ile beraber kullanılır
    @ExceptionHandler //istisna yönetimi ve uygun yanıtların döndürülmesi konusunda kontrolcü sınıfların işlevlerini özelleştirir
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //400 kodlu  HTTP isteğinin sunucu tarafından doğru bir şekilde anlaşılmadığını ve geçersiz bir istek olduğunu belirtmek için kullanılır
    public ProblemDetails handleValidationException( MethodArgumentNotValidException methodArgumentNotValidException) { //hata yakaladığında kullancııya bütün bilgiler verilmemesi için yapılan özelliştirme fonksiyonu
        ValidationProblemDetails validationProblemDetails=new ValidationProblemDetails();
        validationProblemDetails.setMessage("VALIDATION.EXCEPTION"); //kullancıya sadece ilgili mesaj verilir , hepsi verilmez
        validationProblemDetails.setValidationErrors(new HashMap<String,String>());

        //hata bilgileri array olarak geldiği için bütün hata bilgileri foreach ile tek tek gezilir
        for (FieldError fieldError: methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            //methodArgumentNotValidException.getBindingResult().getFieldErrors(): gelen hatayı liste şeklinde verir
            validationProblemDetails.getValidationErrors().put(fieldError.getField(),fieldError.getDefaultMessage());
            // Map<hangi alanda (.getField) ,ne hatası var (.getDefault)> şeklinde parametreler tutar burada hataya o error yerleştirilir
            //kullanıcıya hangi alanda ne hatası olduğunu belirtmek için yapılan işlemlerdir
        }

        return validationProblemDetails;
    }






}
