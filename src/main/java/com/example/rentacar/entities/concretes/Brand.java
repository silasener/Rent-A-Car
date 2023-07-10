package com.example.rentacar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Table(name = "brands") //bu classı veritabanında "brands" tablosu olarak ilişkilendirir
@Data //getter setter equals toString hashCode fonksiyolarını otomatik oluşturur
@NoArgsConstructor //parametresiz constructor otomatik oluşturur
@AllArgsConstructor //parametreli constructor otomatik oluşturur
@Entity // classın , veritabanında "brands" adındaki tablonun  bir VARLIK olduğunu belirtir
public class Brand {
    @Id //pk olarak işaretlenir
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk oluşma tipi belirlenir (otomatik artar)
    @Column(name = "id") //veritabanında değişkene ait sütuna ad verilir, id olarak görüntülenir
    private int id;

    @Column(name = "name")
    private String name;

    //İki tabloyu (brand ve model) birbirine bağladığın anahtar değişken : id olduğu için  ilişki yazılırken olduğun classtan-->diğer classa yazılır
    @OneToMany(mappedBy = "brand") // Brand tablosunda id bir kez varken , Model tablosunda bu id birden çok kez var
            // mappedBy = "brand"     // Model'deki brand ile ilişkilendirilir
    List<Model> models; // bir brand(markanın) birden çok modeli olabilir ; nissanın : qashqai , juke , ... gibi




/*     Lombok sayesinde @Data , @NoArgsConstructor ,@AllArgsConstructor aşağıdaki kodları otomatik yazar
    public Brand() {
    }

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

 */
}
