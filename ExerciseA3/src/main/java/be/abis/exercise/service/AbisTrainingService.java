package be.abis.exercise.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import be.abis.exercise.exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.PersonRepository;

@Service
@Repository
public class AbisTrainingService implements TrainingService {

	@Autowired
	private PersonRepository myPersonRepo;
	@Autowired
	private CourseService myCourseService;
	
	@Override
	public Person findPerson(int id) {
		// TODO Auto-generated method stub
		return myPersonRepo.findPerson(id);
	}

	@Override
	public ArrayList<Person> getAllPersons() {
		// TODO Auto-generated method stub
		return myPersonRepo.getAllPersons();
	}

	@Override
	public List<Course> showFollowedCourses(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException {
		// TODO Auto-generated method stub
	}

	public List<Course> findAllCourses() {
		// TODO Auto-generated method stub
		return myCourseService.findAllCourses();
	}

	public Course findCourse(int id) {
		// TODO Auto-generated method stub
		return myCourseService.findCourse(id);
	}

	public Course findCourse(String shortTitle) {
		// TODO Auto-generated method stub
		return myCourseService.findCourse(shortTitle);
	}

	@Override
	public void addPerson(Person p) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CourseService getCourseService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCourseService(CourseService courseService) {
		// TODO Auto-generated method stub
		
	}
}
