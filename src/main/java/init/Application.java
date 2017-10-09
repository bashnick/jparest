package init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws SQLException {

        Connection db = DriverManager.getConnection("jdbc:postgresql://localhost/jparest", "postgres", "12345");
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository){
        return (args) -> {
            //save users
            repository.save(new User("admin", "admin", "admin", "admin@mail.ru", "ADMIN"));
            repository.save(new User("Jack", "Bauer", "jackB", "jack@mail.ru", "USER"));
            repository.save(new User("Chloe", "O'Brian", "chloeO", "chloe@mail.ru", "USER"));

            //fetch all users
            log.info("Users found with findAll():");
            log.info("-------------------------------");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }
            log.info("");

            //fetch customers by last name
            log.info("User found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (User bauer : repository.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }
}
