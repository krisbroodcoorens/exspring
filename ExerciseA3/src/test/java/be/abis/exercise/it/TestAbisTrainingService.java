package be.abis.exercise.it;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;
import be.abis.exercise.service.AbisCourseService;
import be.abis.exercise.service.AbisTrainingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAbisTrainingService {

	@Autowired
	AbisTrainingService myAbisTrainingService;

	@Test
	public void findPersonWithId1() 
	{
		Person myPerson = myAbisTrainingService.findPerson(1);
		assertEquals(1,myPerson.getPersonId());
	}
}
