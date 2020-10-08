package be.abis.exercise.service;

import java.util.List;
import org.springframework.stereotype.Service;
import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;

@Service
public class AbisCourseService implements CourseRepository {

	@Override
	public List<Course> findAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course findCourse(int id) {
		// TODO Auto-generated method stub
		return new Course("7900","Java","Java Reskilling Training",25,550.0);
	}

	@Override
	public Course findCourse(String shortTitle) {
		// TODO Auto-generated method stub
		return new Course ("7900","Java","Java Reskilling Training",25,550.0);
	}

}