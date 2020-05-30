package com.test.Swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@EnableConfigurationProperties
public class SwaggerController {
	
	@Value("${swaggerUI.enable:true}")
	boolean enbaleSwaggerUI;
	
	
	@GetMapping(value="/")
	public String index()
	{
		return (enbaleSwaggerUI? "redirect:swagger-ui.html": "");
	}

}
