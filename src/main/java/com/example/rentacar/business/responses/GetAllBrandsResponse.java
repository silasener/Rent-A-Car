package com.example.rentacar.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBrandsResponse {  // Response (yanıt) : Sunucunun istemciye gönderdiği cevaptır
private int id;
private String name;
}
