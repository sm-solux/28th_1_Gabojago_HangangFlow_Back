package com.hangangFlow.hangangFlow;

import com.hangangFlow.hangangFlow.domain.park.ParkRepository;
import com.hangangFlow.hangangFlow.domain.user.UserRepository;
import com.hangangFlow.hangangFlow.dto.Parks;
import com.hangangFlow.hangangFlow.dto.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EntityScan(basePackageClasses = {Parks.class, User.class})
@EnableJpaRepositories (basePackageClasses = {UserRepository.class, ParkRepository.class})

public class HangangFlowBackApplication {

	public static void main(String[] args) {

		SpringApplication.run(HangangFlowBackApplication.class, args);

	}

}
