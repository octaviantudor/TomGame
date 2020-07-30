package tom.repository.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactory {
    public static SessionFactory sessionFactory;

    public static void initialize() {
        close();
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.out.println("eroare" +
                    "" + e);
            StandardServiceRegistryBuilder.destroy( registry );
            e.printStackTrace();
        }
    }

    public static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }
}