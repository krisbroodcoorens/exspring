package be.abis.exercise.it;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.service.AbisTrainingService;
import be.abis.exercise.service.TrainingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAbisTrainingService {

	@Autowired
	AbisTrainingService myAbisTrainingService;

	@Test
	public void findPersonWithId() 
	{
		assertEquals(1,myAbisTrainingService.findPerson(1).getPersonId());
	}
}
