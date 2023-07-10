package com.example.rentacar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "models") //bu classı veritabanında "models" tablosu olarak ilişkilendirir
@Data //getter setter equals toString hashCode fonksiyolarını otomatik oluşturur
@NoArgsConstructor //parametresiz constructor otomatik oluşturur
@AllArgsConstructor //parametreli constructor otomatik oluşturur
@Entity // classın , veritabanında "brands" adındaki tablonun  bir VARLIK olduğunu belirtir
public class Model { //Model'deki brand ; Brand'teki id ile bağlanır


    @Id //pk olarak işaretlenir
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk oluşma tipi belirlenir (otomatik artar)
    @Column(name = "id") //veritabanında değişkene ait sütuna ad verilir, id olarak görüntülenir
    private int id;

    @Column(name = "name")
    private String name;

    //İki tabloyu( brand ve model) birbirine bağladığın anahtar değişken : brand id olduğu için  ilişki yazılırken olduğun classtan-->diğer classa yazılır
    @ManyToOne //Model tablosunda brand'teki id den birçok  kez varken , brand tablosunda model id'si bir kez var
    @JoinColumn(name = "brand_id")
    private Brand brand; //model sadece bir markaya ait olabilir ; qashqai modeli nissan brand (markasına) aittir


    @OneToMany(mappedBy = "model") //Model tablosunda id bir kez varken, Car tablosunda model id birden çok kez var
    //mappedBy = "model"  Car'daki model ile ilişkilendirilir
    private List<Car> cars; //bir modele ait birden çok araç olabilir , qashqai modeline ait 35 eb 9274 ve 35 be 9274 olması gibi


}
