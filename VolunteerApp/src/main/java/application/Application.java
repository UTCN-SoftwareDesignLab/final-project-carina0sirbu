package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import service.email.SendEmail;

@EnableJpaRepositories(basePackages = {"repository"})
@SpringBootApplication(scanBasePackages = {"application", "controller", "dto", "model", "repository", "service", "config"})
@EntityScan(basePackages = {"model"})
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(application.Application.class, args);

//        SendEmail email = new SendEmail();
//        email.send("natalia.sirbu996@gmail.com", "anamariahan.hanna@gmail.com", "Parola123#", "Cum mergergerherth cu proiectul? haa");
    }
}
