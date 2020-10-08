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
import be.abis.exercise.service.CourseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAbisCourseService {

	@Autowired
	CourseService myCourseService;

	@Test
	public void findCourseWithId7900() 
	{
		Course myCourse = myCourseService.findCourse("7900");
		assertEquals("7900",myCourse.getCourseId());
	}
		
	@Test
	public void checkIfPriceCourseIsHigherThan400() 
	{
		Course myCourse = myCourseService.findCourse("7900");
		assertThat(myCourse.getPricePerDay(), Matchers.greaterThan((400.0)));
	}
}

