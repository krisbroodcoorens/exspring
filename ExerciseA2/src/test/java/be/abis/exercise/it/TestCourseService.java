package be.abis.exercise.it;

import static org.junit.Assert.*;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.service.AbisCourseService;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCourseService {

	@Autowired
	CourseService myCourseService;

	@Test
	public void findCourseWithId7900() 
	{
		assertEquals("7900",myCourseService.findCourse("7900"));
	}
		
	@Test
	public void checkIfPriceCourseIsHigherThan400() 
	{
		assertThat(myCourseService.findCourse("7900").getPricePerDay(), Matchers.greaterThan((400.0)));
	}
}