package com.test.Swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableConfigurationProperties

public class SwaggerConfiguration {
	
	@Value("${spring.profile.active:}")
	String environment;

	@Value("${swaggerUI.enable:true}")
	boolean enbaleSwaggerUI;
	
	@Value("${swaggerUI.psgURL:}")
	String psgURL;
	
	ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title("Product service").description("Product service is responsible to save in DB").license("")
		     .version("1,1.0").build();
	}
	
	@Bean
	public  Docket customeImplementation()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				  .select()
				  .apis(RequestHandlerSelectors.basePackage("com.test.Swagger"))
				  .build().apiInfo(apiInfo())
				  //.directModelSubstitute(org.joda)
				  //.directModelSubstitute(org.joda.time.DateTime.class,java.util.Date.class)
				  .apiInfo(apiInfo())
				  .enable(enbaleSwaggerUI);
	
	}
	
	
	
	
	
}
