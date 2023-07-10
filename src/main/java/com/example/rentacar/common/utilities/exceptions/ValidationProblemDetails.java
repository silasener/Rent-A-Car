package com.example.rentacar.common.utilities.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationProblemDetails extends ProblemDetails{
    private Map<String,String> validationErrors; // Map<hangi alanda (.getField) ,ne hatası var (.getDefault)> şeklinde parametreler tutar

}
