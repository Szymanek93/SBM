package com.szymansky.SBM;

import com.szymansky.SBM.Entity.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SbmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbmApplication.class, args);
	}


}
