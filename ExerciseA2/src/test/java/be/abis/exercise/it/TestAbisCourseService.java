package be.abis.exercise.it;

import static org.junit.Assert.*;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.AbisCourseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAbisCourseService {

	@Autowired
	AbisCourseService myAbisCourseService;

	@Test
	public void findCourseWithId7900() 
	{
		Course myAbisCourse = myAbisCourseService.findCourse("7900");
		assertEquals("7900",myAbisCourse.getCourseId());
	}
		
	@Test
	public void checkIfPriceCourseIsHigherThan400() 
	{
		Course myAbisCourse = myAbisCourseService.findCourse("7900");
		assertThat(myAbisCourse.getPricePerDay(), Matchers.greaterThan((400.0)));
	}
}

