package be.abis.exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;

@Service
public class AbisCourseService implements CourseService {

	@Autowired
	private CourseRepository myCourseRepository;
	
	@Override
	public List<Course> findAllCourses() {
		return myCourseRepository.findAllCourses();
	}

	@Override
	public Course findCourse(int id) {
		return myCourseRepository.findCourse(id);
	}

	@Override
	public Course findCourse(String shortTitle) {
		return myCourseRepository.findCourse(shortTitle);
	}

}