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
            //save admin

            log.info("Saving admin if not present:");
            if(repository.findByLogin("admin").isEmpty()){
                String admin_password = "123";
                String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(admin_password);
                User admin = new User("admin", "admin", "admin", sha256hex, "admin@mail.ru", "ADMIN");
                repository.save(admin);
            }
            log.info("-------------------------------");

            log.info("Saving user if not present:");
            if(repository.findByLogin("user").isEmpty()){
                String user_password = "321";
                String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(user_password);
                User user = new User("Adam ", "Smith", "user", sha256hex, "user@mail.ru", "USER");
                repository.save(user);
            }
            log.info("-------------------------------");

            log.info("Users found with findAll():");
            for (User usr : repository.findAll()) {
                log.info(usr.toString());
            }
            log.info("-------------------------------");
        };
    }
}
