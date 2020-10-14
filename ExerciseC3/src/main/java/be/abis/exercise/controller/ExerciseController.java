package be.abis.exercise.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.service.AbisTrainingService;
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
	Person connectedPerson = new Person();	
	Course savedCourse = new Course();
	Person savedPerson = new Person();
	int newPersonId = 0; 
			
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
	//*** LOGIN SCREEN - login.html ****************************************************************************
	
	@GetMapping("/login")
	public ModelAndView showLoginForm()
	{
		Map<String, Object> loginModel = new HashMap<String, Object>();
				
		loginModel.put("loginInput", new Login());
		
		return new ModelAndView("login", loginModel);	
	}	
	
	@PostMapping("/login")
	public ModelAndView submitLoginForm(Login inputLogin,  BindingResult resultLogin)  
	{
		savedLogin.setEmailAddress(inputLogin.getEmailAddress());
		savedLogin.setPassword(inputLogin.getPassword());
		connectedPerson = myTrainingService.findPerson(savedLogin.getEmailAddress(), savedLogin.getPassword());
		
		System.out.println("ConnectedPerson: " +connectedPerson);
		System.out.println("inputLogin: " +inputLogin);
		
		if (connectedPerson != null)
		{
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl("/welcome");				
			return new ModelAndView(redirectView);	
		}
		else
		{
			RedirectView redirectView = new RedirectView();
			resultLogin.reject("global", "Login not valid. Please try again");
			redirectView.setUrl("/login");		
			return new ModelAndView(redirectView);	
		}
	}

	//*** WELCOME SCREEN - welcome.html **********************************************************************
	
	@GetMapping("/welcome")
	public ModelAndView showWelcomePage()
	{
		Map<String, Object> welcomeModel = new HashMap<String, Object>();
						
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
	
	//*** PERSON ADMINISTRATION SCREEN - personadmin.html *********************************************
	
	@GetMapping("/personadmin")
	public ModelAndView showPersonAdmin()
	{
		Map<String, Object> personAdminModel = new HashMap<String, Object>();
		return new ModelAndView("personadmin", personAdminModel);
	}
	
	@PostMapping("/personadmin")
	public ModelAndView backToLogin()
	{
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/welcome");	
		
		return new ModelAndView(redirectView);
	}
	
	//*** CHANGE PASSWORD SCREEN - changepasw.html **********************************************************
	
	@GetMapping("/changepasw")
	public ModelAndView changePassword()
	{
		Map<String, Object> changePasswordScreenModel = new HashMap<String, Object>();
		Person passwordInput = new Person();
		System.out.println (connectedPerson.getFirstName());
		changePasswordScreenModel.put("currentPassword", connectedPerson.getPassword());
		changePasswordScreenModel.put("passwordInput", passwordInput);
		return new ModelAndView("changepasw", changePasswordScreenModel);
	}
	
	@PostMapping("/changepasw")
	public ModelAndView submitChangedPassword(Person passwordInput) throws IOException
	{
		System.out.println (connectedPerson.getFirstName());
		myTrainingService.changePassword(connectedPerson, passwordInput.getPassword());
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/personadmin");
		return new ModelAndView(redirectView);
	}
	
	//*** ADD PERSON SCREEN - addperson.html ****************************************************************
	
	@GetMapping("/addperson")
	public ModelAndView showAddPerson()
	{
		Map<String, Object> showAddPersonModel = new HashMap<String, Object>();
		Address inputAddress = new Address();
		Company inputCompany = new Company();
		Person inputPerson = new Person();		
		newPersonId = myTrainingService.getMaxPersonId() + 1;
		showAddPersonModel.put("newPersonId", newPersonId);
		showAddPersonModel.put("personInput", inputPerson);
		return new ModelAndView("addperson", showAddPersonModel);
	}
	
	@PostMapping("/addperson")
	public ModelAndView submitAddPerson(@Valid @ModelAttribute("personInput") Person personInput,  BindingResult resultAddPerson) throws IOException
	{
		if (resultAddPerson.hasErrors())
		{
			return new ModelAndView("addPerson");
		}
		else
		{
			personInput.setPersonId(newPersonId);
			myTrainingService.addPerson(personInput);
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl("/personadmin");
			return new ModelAndView(redirectView);
		}
	}
	
	//*** SEARCH COURSE(S) SCREEN - searchcourse.html*******************************************************

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
	public ModelAndView submitCourseSelection(Course searchCourse)
	{
		savedCourse.setCourseId(searchCourse.getCourseId());
		savedCourse.setShortTitle(searchCourse.getShortTitle());
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/searchcourse");	
		
		return new ModelAndView(redirectView);
	}
	
	//*** SEARCH PERSON(S) SCREEN - searchperson.html ******************************************************
	
	@GetMapping("/searchperson")
	public ModelAndView showPersonScreen()
	{
		Map<String, Object> showPersonFormModel = new HashMap<String, Object>();
		showPersonFormModel.put("searchPerson", new Person());
		int numberOfPersons=0;

		if (savedPerson.getPersonId() != 0)
		{
			showPersonFormModel.put("listPersons", myTrainingService.findPerson(savedPerson.getPersonId()));
			numberOfPersons = 1;
		}
		else
		{
			List<Person> listPersons = null;
			listPersons = myTrainingService.getAllPersons();	
			numberOfPersons = listPersons.size();
			showPersonFormModel.put("listPersons", listPersons);
		}	
		
		showPersonFormModel.put("numberOfPersons", numberOfPersons);
		return new ModelAndView("searchperson", showPersonFormModel);		
	}
	
	@PostMapping("/searchperson")
	public ModelAndView submitPersonSelection(Person searchPersonId)
	{
		Map<String, Object> submitPersonsSelectionModel = new HashMap<String, Object>();
		
		savedPerson.setPersonId(searchPersonId.getPersonId());
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/searchperson");	
		
		return new ModelAndView(redirectView);
	}
	
	//*** REMOVE PERSON SCREEN - removeperson.html******************************************************
	
	@GetMapping("/removeperson")
	public ModelAndView showRemovePersonScreen()
	{
		Map<String, Object> showRemovePersonFormModel = new HashMap<String, Object>();
		showRemovePersonFormModel.put("removePerson", new Person());
		int numberOfPersons=0;

		if (savedPerson.getPersonId() != 0)
		{
			showRemovePersonFormModel.put("listPersons", myTrainingService.findPerson(savedPerson.getPersonId()));
			numberOfPersons = 1;
		}
		else
		{
			List<Person> listPersons = null;
			listPersons = myTrainingService.getAllPersons();	
			numberOfPersons = listPersons.size();
			showRemovePersonFormModel.put("listPersons", listPersons);
		}	
		
		showRemovePersonFormModel.put("numberOfPersons", numberOfPersons);
		return new ModelAndView("removeperson", showRemovePersonFormModel);		
	}
	
	@PostMapping("/removeperson")
	public ModelAndView submitRemovePersonSelection(Person searchPersonId, String selectedPersonId)
	{
		Map<String, Object> submitRemovePersonsSelectionModel = new HashMap<String, Object>();
		
		savedPerson.setPersonId(searchPersonId.getPersonId());
		
		if (selectedPersonId != null)
		{
			myTrainingService.deletePerson(Integer.parseInt(selectedPersonId));
		}
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/removeperson");	
		
		return new ModelAndView(redirectView);
	}

	//**********************************************************************************************************
}

