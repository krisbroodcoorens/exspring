package be.abis.exercise.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.abis.exercise.exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;

@Service
public class AbisTrainingService implements TrainingService 
{

	@Autowired
	private PersonRepository myPersonRepository;
	@Autowired
	private CourseService myCourseService;
	
    @Override
    public ArrayList<Person> getAllPersons() {
        return myPersonRepository.getAllPersons();
    }
	
	@Override
	public Person findPerson(int id) {
		// TODO Auto-generated method stub
		return myPersonRepository.findPerson(id);
	}

    @Override
    public Person findPerson(String emailAddress, String passWord) {
    	// TODO Auto-generated method stub
    	return myPersonRepository.findPerson(emailAddress, passWord);
    }
	
    @Override
    public void addPerson(Person p) throws IOException {
        myPersonRepository.addPerson(p);
    }

    @Override
    public void deletePerson(int id) {
        myPersonRepository.deletePerson(id);
    }
    
    @Override
    public void changePassword(Person myPerson, String newPassword) throws IOException {
        myPersonRepository.changePassword(myPerson, newPassword);
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

	@Override
	public Course findCourse(int id) {
		// TODO Auto-generated method stub
		return myCourseService.findCourse(id);
	}

	@Override
	public CourseService getCourseService() {
		// TODO Auto-generated method stub
		return myCourseService;
	}

	@Override
	public void setCourseService(CourseService courseService) {
		// TODO Auto-generated method stub
		this.myCourseService = courseService;
	}
}
