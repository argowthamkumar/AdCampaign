package com.campaign;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

/*@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableWebMvc*/
@SpringBootApplication
public class Application {

	private static final Class ApplicationCLASS = Application.class;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Inside app"); 
 SpringApplication.run(ApplicationCLASS, args);
//ApplicationContext context = SpringApplication.run(ApplicationCLASS, args);
 //System.out.println(( (RESTClientExample) context.getBean("restClient")).getAllEmployees());
	}
	 
	
	@Bean
	public Filter characterEncodingFilter(){
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
	
}
