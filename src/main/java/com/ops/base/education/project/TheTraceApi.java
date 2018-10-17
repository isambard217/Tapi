package com.ops.base.education.project;
import com.ops.base.education.project.Service.storage.StorageProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class TheTraceApi {
	public static void main(String[] args) {
		SpringApplication.run(TheTraceApi.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
}
