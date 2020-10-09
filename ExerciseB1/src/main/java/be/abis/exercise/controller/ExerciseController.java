package be.abis.exercise.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.AbisTrainingService;
import be.abis.exercise.service.TrainingService;

@Controller
public class ExerciseController 
{	
	@Autowired
	TrainingService myTrainingService; 
	
	private Login savedLogin = new Login();
	
	//@GetMapping("/exercise")
	//public ModelAndView showExercise()
	//{
	//	Map<String, Object> exerciseModel = new HashMap<String, Object>();
	//	
	//	Person thirdPersonId = myTrainingService.findPerson(3);
	//	String thirdPersonName = thirdPersonId.getFirstName()+ " " +thirdPersonId.getLastName();
	//			
	//	exerciseModel.put("courseTitle", myTrainingService.getCourseService().findCourse(7900).getLongTitle());
	//	exerciseModel.put("thirdPersonName", thirdPersonName);
	//			
	//	return new ModelAndView("exercise", exerciseModel);
	//}	
	
	@GetMapping("/login")
	public ModelAndView showLoginForm()
	{
		Map<String, Object> loginModel = new HashMap<String, Object>();
				
		loginModel.put("loginInput", new Login());
		
		return new ModelAndView("login", loginModel);	
	}	
	
	@PostMapping("/login")
	public ModelAndView submitLoginForm(Login inputLogin)
	{
		savedLogin.setEmailAddress(inputLogin.getEmailAddress());
		savedLogin.setPassword(inputLogin.getPassword());
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/welcome");	
		
		return new ModelAndView(redirectView);
	}	
	
	@GetMapping("/welcome")
	public ModelAndView showWelcomePage()
	{
		Map<String, Object> welcomeModel = new HashMap<String, Object>();
		
		Person connectedPerson = myTrainingService.findPerson(savedLogin.getEmailAddress(), savedLogin.getPassword());
						
		welcomeModel.put("firstName", connectedPerson.getFirstName());
				
		return new ModelAndView("welcome", welcomeModel);
	}

	@PostMapping("/welcome")
	public ModelAndView logoutButton()
	{
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/login");	
		
		return new ModelAndView(redirectView);
	}
}

