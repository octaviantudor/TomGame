package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tom.repository.Hibernate.HibernateSessionFactory;

@ComponentScan("tom")

@SpringBootApplication

public class StartRestServices {
    public static void main(String[] args) {
        HibernateSessionFactory.initialize();

        SpringApplication.run(StartRestServices.class, args);

    }
}
