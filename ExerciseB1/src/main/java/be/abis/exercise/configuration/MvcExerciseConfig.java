package be.abis.exercise.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcExerciseConfig implements WebMvcConfigurer 
{
	@Override
	public void addViewControllers(ViewControllerRegistry myRegistry)
	{
		myRegistry.addViewController("/").setViewName("exercise.html");
	}
}
