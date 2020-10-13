package be.abis.exercise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.TrainingService;

@Controller
public class ExerciseController 
{	
	@Autowired
	TrainingService myTrainingService; 
	@Autowired
	CourseService myCourseService;
	
	Login savedLogin = new Login();
	Course savedCourse = new Course();
	Person connectedPerson = new Person();
					
	//**********************************************************************************************************
	@GetMapping("/exercise")
	public ModelAndView showExercise()
	{
		Map<String, Object> exerciseModel = new HashMap<String, Object>();
		
		Person thirdPersonId = myTrainingService.findPerson(3);
		String thirdPersonName = thirdPersonId.getFirstName()+ " " +thirdPersonId.getLastName();
				
		exerciseModel.put("courseTitle", myTrainingService.getCourseService().findCourse(7900).getLongTitle());
		exerciseModel.put("thirdPersonName", thirdPersonName);
				
		return new ModelAndView("exercise", exerciseModel);
	}
	//**********************************************************************************************************
	
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
		
		connectedPerson = myTrainingService.findPerson(savedLogin.getEmailAddress(), savedLogin.getPassword());
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/welcome");	
		
		return new ModelAndView(redirectView);
	}
	
	//**********************************************************************************************************
	
	@GetMapping("/welcome")
	public ModelAndView showWelcomePage()
	{
		Map<String, Object> welcomeModel = new HashMap<String, Object>();
						
		welcomeModel.put("firstName", connectedPerson.getFirstName());
				
		return new ModelAndView("welcome", welcomeModel);
	}
	
	//**********************************************************************************************************
	
	@GetMapping("/personadmin")
	public ModelAndView showPersonAdmin()
	{
		Map<String, Object> personAdminModel = new HashMap<String, Object>();
			
		return new ModelAndView("personadmin", personAdminModel);
	}
		
	//**********************************************************************************************************

	@GetMapping("/searchcourse")
	public ModelAndView showCourseScreen()
	{
		Map<String, Object> showCourseFormModel = new HashMap<String, Object>();
		showCourseFormModel.put("courseInput", new Course());
		int numberOfCourses=0;

		if (savedCourse.getCourseId() != null && !(savedCourse.getCourseId().isEmpty()))
		{
			showCourseFormModel.put("listCourses", myCourseService.findCourse(Integer.parseInt(savedCourse.getCourseId())));
			numberOfCourses = 1;
		}
		else if (savedCourse.getShortTitle() != null && !(savedCourse.getShortTitle().isEmpty()))		
		{
			showCourseFormModel.put("listCourses", myCourseService.findCourse(savedCourse.getShortTitle()));
			numberOfCourses = 1;
		}
		else
		{
			List<Course> listCourses = null;
			listCourses = myCourseService.findAllCourses();	
			numberOfCourses = listCourses.size();
			showCourseFormModel.put("listCourses", listCourses);
		}	
		
		showCourseFormModel.put("numberOfCourses", numberOfCourses);
		return new ModelAndView("searchcourse", showCourseFormModel);		
	}
	
	@PostMapping("/searchcourse")
	public ModelAndView submitCourseSelection(Course inputCourse)
	{
		savedCourse.setCourseId(inputCourse.getCourseId());
		savedCourse.setShortTitle(inputCourse.getShortTitle());
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/searchcourse");	
		
		return new ModelAndView(redirectView);
	}
}
	


