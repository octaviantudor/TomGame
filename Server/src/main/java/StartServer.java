import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tom.repository.Hibernate.HibernateSessionFactory;

public class StartServer {
    public static void main(String[] args) {
        HibernateSessionFactory.initialize();
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");
    }
}
