import tom.Jucator;
import tom.Raspuns;
import tom.repository.Hibernate.HibernateSessionFactory;
import tom.repository.JucatorRepository;
import tom.repository.RaspunsRepository;

import java.sql.SQLException;

public class main {

    public static void main(String[] args) {

        HibernateSessionFactory.initialize();
        Raspuns raspuns = new Raspuns(1,"dasda","dsadas","Dasda");

        raspuns.setIdJoc(1);
        System.out.println(raspuns);

        RaspunsRepository rp = new RaspunsRepository();

        try {
            rp.save(raspuns);
        }catch (Exception e){
            System.out.println(e);
        }


    }
}
