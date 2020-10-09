package be.abis.exercise.it;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import be.abis.exercise.controller.ExerciseController;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.TrainingService;

@WebMvcTest(ExerciseController.class)
@RunWith(SpringRunner.class)
public class TestExerciseController 
{
	@Autowired
	MockMvc myMockMvc;
    @MockBean
    TrainingService myTrainingService;
	@MockBean
    CourseService myCourseService;
    
	@Test
	public void testShowWebPage() throws Exception 
	{ 
		myMockMvc.perform(get("/exercise"))
		.andExpect(status().is2xxSuccessful());
	}
}
