package kz.jusan.customerjpa.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            Customer mariyam = new Customer(
                    "Mariryam",
                    LocalDate.of(1998, Month.JANUARY, 4),
                    "marya.jamal@gmail.com"
            );

            Customer alex = new Customer(
                    "Alex",
                    LocalDate.of(2000, Month.JANUARY, 4),
                    "alex.jamal@gmail.com"
            );

            customerRepository.saveAll(
                    List.of(mariyam, alex)
            );
        };
    }
}
