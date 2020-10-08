package be.abis.exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;

@Service
public class AbisCourseService implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public List<Course> findAllCourses() {
		// TODO Auto-generated method stub
		return courseRepository.findAllCourses();
	}

	@Override
	public Course findCourse(int id) {
		// TODO Auto-generated method stub
		return courseRepository.findCourse(id);
	}

	@Override
	public Course findCourse(String shortTitle) {
		// TODO Auto-generated method stub
		return courseRepository.findCourse(shortTitle);
	}

}