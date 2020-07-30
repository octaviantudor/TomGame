package tom.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;
import tom.Jucator;
import tom.repository.Hibernate.HibernateSessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class JucatorRepository implements InterfaceJucatorRepository {


    @Override
    public int size() {
        return 0;
    }

    @Override
    public void save(Jucator jucator) throws SQLException {
        try(Session session= HibernateSessionFactory.sessionFactory.openSession()){
            Transaction transaction=null;
            try{
                transaction=session.beginTransaction();
                session.save(jucator);
                transaction.commit();
            }catch (RuntimeException ex){
                if(transaction!=null)
                    transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public void update(Jucator entity) {

        try(Session session= HibernateSessionFactory.sessionFactory.openSession()){
            Transaction transaction=null;
            try{
                transaction=session.beginTransaction();
                session.update(entity);
                transaction.commit();
            }catch (RuntimeException ex){
                if(transaction!=null)
                    transaction.rollback();
            }
        }
    }

    @Override
    public Jucator findOne(String username,String password){
        List<Jucator>jucatori=new ArrayList<>();
        try(Session session=HibernateSessionFactory.sessionFactory.openSession()){
            Transaction transaction=null;
            try{
                transaction=session.beginTransaction();
                String query="from tom.Jucator";
                jucatori=session.createQuery(query).list();
                transaction.commit();
                for(Jucator j: jucatori){
                    if (j.getUsername().equals(username) && j.getPassword().equals(password)){
                        return j;
                    }
                }
            }catch (RuntimeException ex){
                if(transaction!=null)
                    transaction.rollback();
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Jucator findOneById(Integer id){
        List<Jucator>jucatori=new ArrayList<>();
        try(Session session=HibernateSessionFactory.sessionFactory.openSession()){
            Transaction transaction=null;
            try{
                transaction=session.beginTransaction();
                String query="from tom.Jucator";
                jucatori=session.createQuery(query).list();
                transaction.commit();
                for(Jucator j: jucatori){

                    if (j.getId().equals(id)){
                        return j;
                    }
                }
            }catch (RuntimeException ex){
                if(transaction!=null)
                    transaction.rollback();
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Jucator> findAll() {
        List<Jucator>jucatori=new ArrayList<>();
        try(Session session=HibernateSessionFactory.sessionFactory.openSession()){
            Transaction transaction=null;
            try{
                transaction=session.beginTransaction();
                String query="from tom.Jucator";
                jucatori=session.createQuery(query).list();
                transaction.commit();
            }catch (RuntimeException ex){
                if(transaction!=null)
                    transaction.rollback();
                ex.printStackTrace();
            }
        }
        return jucatori;
    }
}
