package tom;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="joc")
public class Joc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "letter",nullable = false)
    private String letter;

    public Joc(){
    }

    public Joc(String letter){
        this.letter=letter;
    }

    public Joc(Integer id, String letter) {
        this.id = id;
        this.letter = letter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
}
