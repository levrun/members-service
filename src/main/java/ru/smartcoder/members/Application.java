package ru.smartcoder.members;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.smartcoder.members.model.Member;
import ru.smartcoder.members.repository.MembersRepository;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(MembersRepository repository) {
        return (evt) -> Arrays.asList(
                "Alexei,Sergei,Bob,Karl,Donald,Jack,Kelly,Mickey".split(","))
                .forEach(
                        a -> {
                            Member member = new Member();
                            member.setLastName(a);
                            member.setFirstName(a);
                            member.setZip("123-145");
                            member.setDateOfBirth(LocalDate.parse("1999-01-12"));
                            repository.save(member);
                        });
    }

}
