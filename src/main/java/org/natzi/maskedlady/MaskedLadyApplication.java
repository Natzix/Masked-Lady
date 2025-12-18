package org.natzi.maskedlady;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class MaskedLadyApplication {
	public static void main(String[] args) {
		SpringApplication.run(MaskedLadyApplication.class, args);
	}
}
