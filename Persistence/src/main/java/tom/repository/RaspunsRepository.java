package tom.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import tom.Raspuns;
import tom.repository.Hibernate.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

@Component
public class RaspunsRepository implements InterfaceRaspunsRepository {
    @Override
    public List<Raspuns> getAll() {
        List<Raspuns>raspunsuri=new ArrayList<>();
        try(Session session=HibernateSessionFactory.sessionFactory.openSession()){
            Transaction transaction=null;
            try{
                transaction=session.beginTransaction();
                String query="from tom.Raspuns";
                raspunsuri=session.createQuery(query).list();
                transaction.commit();
            }catch (RuntimeException ex){
                if(transaction!=null)
                    transaction.rollback();
                ex.printStackTrace();
            }
        }
        return raspunsuri;
    }

    @Override
    public void save(Raspuns raspuns) {
        try(Session session= HibernateSessionFactory.sessionFactory.openSession()){
            Transaction transaction=null;
            try{
                transaction=session.beginTransaction();
                System.out.println("Am ajuns aici cu parnaia");
                session.save(raspuns);
                transaction.commit();
            }catch (RuntimeException ex){
                if(transaction!=null)
                    transaction.rollback();
            }
        }
    }
}
