package be.abis.exercise.it;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.MemoryCourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCourseRepository {
	
	CourseRepository cr;
	
	@Before
	public void setUp() {
		cr = new MemoryCourseRepository();
	}
	
	@Test
	public void numberOfCoursesInMemoryIs5() {
		int size = cr.findAllCourses().size();
		assertEquals(5,size);
	}
	
	@Test
	public void courseWithId8050isMaven() {
		String title = cr.findCourse(8050).getShortTitle();
		assertEquals("Maven",title);
	}
	
	

}
