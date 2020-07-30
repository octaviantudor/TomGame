package tom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="raspuns")
public class Raspuns implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRaspuns;

    @Column(name = "idJucator",nullable = false)
    private Integer idJucator;

    @Column(name = "idJoc",nullable = false)
    private Integer idJoc;

    @Column(name = "oras",nullable = false)
    private String oras;
    @Column(name = "tara",nullable = false)
    private String tara;
    @Column(name = "mare",nullable = false)
    private String mare;

    public Raspuns(){

    }

    public Raspuns( Integer idJucator,String oras,String tara,String mare) {

        this.idJucator = idJucator;
        this.oras=oras;
        this.tara=tara;
        this.mare=mare;

    }


    public Integer getIdJoc() {
        return idJoc;
    }

    public void setIdJoc(Integer idJoc) {
        this.idJoc = idJoc;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getMare() {
        return mare;
    }

    public void setMare(String mare) {
        this.mare = mare;
    }

    public Integer getIdRaspuns() {
        return idRaspuns;
    }

    public void setIdRaspuns(Integer idRaspuns) {
        this.idRaspuns = idRaspuns;
    }

    public Integer getIdJucator() {
        return idJucator;
    }

    public void setIdJucator(Integer idJucator) {
        this.idJucator = idJucator;
    }

    @Override
    public String toString() {
        return "Raspuns{" +
                ", idJucator=" + idJucator +
                ", idJoc=" + idJoc +
                ", oras='" + oras + '\'' +
                ", tara='" + tara + '\'' +
                ", mare='" + mare + '\'' +
                '}';
    }
}
