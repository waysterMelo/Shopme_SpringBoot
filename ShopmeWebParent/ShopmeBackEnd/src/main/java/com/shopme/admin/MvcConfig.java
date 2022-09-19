package com.shopme.admin;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String dir = "user-photos";
		Path pathDir = Paths.get(dir);
		String caminhoAbsoluto  = pathDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/" + dir + "/**").addResourceLocations("file:/" + caminhoAbsoluto + "/");
	}
	
	
	

}
