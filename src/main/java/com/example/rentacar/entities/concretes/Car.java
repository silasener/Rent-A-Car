package com.example.rentacar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "cars") //bu classı veritabanında "brands" tablosu olarak ilişkilendirir
@Data //getter setter equals toString hashCode fonksiyolarını otomatik oluşturur
@NoArgsConstructor //parametresiz constructor otomatik oluşturur
@AllArgsConstructor //parametreli constructor otomatik oluşturur
@Entity // classın , veritabanında "brands" adındaki tablonun  bir VARLIK olduğunu belirtir
public class Car {
    @Id //pk olarak işaretlenir
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk oluşma tipi belirlenir (otomatik artar)
    @Column(name = "id") //veritabanında değişkene ait sütuna ad verilir, id olarak görüntülenir
    private int id;

    @Column(name = "plate", unique = true) //unique = true Car varlığının benzersizliğini sağlamak için kullanılır
    private String plate; //plaka

    @Column(name = "dailyPrice")
    private double dailyPrice; //günlük fiyat

    @Column(name = "modelYear")
    private int modelYear; //model yılı

    @Column(name = "state")
    private int state; // arabanın durumudur örneğin 1-müsait , 2-bakımda gibi


    //İki tabloyu( car ve model) birbirine bağladığın anahtar değişken : model id olduğu için  ilişki yazılırken olduğun classtan-->diğer classa yazılır
    @ManyToOne //Car tablosunda model id den birçok  kez varken , model tablosunda model id'si bir kez var
    @JoinColumn(name = "model_id")
    private Model model; //car (araba) sadece bir kişiye ait olabilir ; 35 eb 9274 bir car'dır fakat modeli qashqai'dir
}
