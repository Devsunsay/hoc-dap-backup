package fr.houseofcode.dap.server.mgw;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/** @author mgw **/
@SpringBootApplication
public class Application {
    /**
     * is the start of the Spring program.
     * @param args is the external parameters.
     **/
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /** Spring beginning.
     * @return the array of beanNames
     * @param ctx the context of application
     **/
    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
