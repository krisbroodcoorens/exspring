package be.abis.exercise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import be.abis.exercise.model.Person;
import be.abis.exercise.service.AbisTrainingService;
import be.abis.exercise.service.TrainingService;

@Controller
public class ExerciseController 
{	
	@Autowired
	TrainingService myTrainingService; 
	
	@GetMapping(value={"/", "/exercise"})
	public ModelAndView showExercise()
	{
		Map<String, Object> exerciseModel = new HashMap<String, Object>();
		
		Person thirdPersonId = myTrainingService.findPerson(3);
		String thirdPersonName = thirdPersonId.getFirstName()+ " " +thirdPersonId.getLastName();
				
		exerciseModel.put("courseTitle",myTrainingService.getCourseService().findCourse(7900).getLongTitle());
		exerciseModel.put("thirdPersonName", thirdPersonName);
				
		return new ModelAndView("exercise", exerciseModel);
	}
}

