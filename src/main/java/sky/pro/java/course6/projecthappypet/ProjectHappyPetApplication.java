package sky.pro.java.course6.projecthappypet;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition
public class ProjectHappyPetApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectHappyPetApplication.class, args);
    }

}
