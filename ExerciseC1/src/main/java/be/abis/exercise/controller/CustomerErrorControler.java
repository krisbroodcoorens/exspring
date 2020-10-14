package be.abis.exercise.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class CustomerErrorControler implements ErrorController
{
	@Override
	public String getErrorPath()
	{
		return "/error";
	}
	
	@GetMapping("/error")
	public ModelAndView handleError(HttpServletRequest errorRequest)
	{
		Map<String, Object> returnErrorScreen = new HashMap<String, Object>();
		
		Object status = errorRequest.getAttribute((RequestDispatcher.ERROR_STATUS_CODE));
		System.out.println("Error with status code " +status+ " occured. Please contact helpdesk");
		returnErrorScreen.put("error", status);
		return new ModelAndView("error", returnErrorScreen);	
	}
}
