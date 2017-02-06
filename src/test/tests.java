package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Car;
import main.Position;
import main.WrongInputException;
public class tests {
	
	Car testCar;
	
	@Before
	public void setUp() throws WrongInputException{
		testCar = new Car(0,false,0,0);
	}

	
	
	// Tests for moveForward.
	@Test
	public void testMoveForward(){
		testCar.moveForward();
		assertEquals(1,testCar.getPosition().getLocation());
	}
	
	@Test
	public void testMoveForwardTooMutch() throws WrongInputException{
		testCar.setLocation(500);
		testCar.moveForward();
		assertEquals(500,testCar.getPosition().getLocation());
	}
	
	@Test(expected=WrongInputException.class)
	public void testMoveForwardLowInput() throws WrongInputException{
		testCar.setLocation(-5);
		testCar.moveForward();
	}
	
	@Test(expected=WrongInputException.class)
	public void testMoveForwardHighInput() throws WrongInputException{
		testCar.setLocation(501);
		testCar.moveForward();
	}
	
	
	
	//Tests for isEmpty
	@Test
	public void testIsEmpty() throws WrongInputException{
		testCar.setUltrasonicSensor1(1);
		testCar.setUltrasonicSensor2(1);
		int distance = testCar.isEmpty();
		assertEquals(1,distance);
	}
	@Test(expected=WrongInputException.class)
	public void testIsEmptyLowInput() throws WrongInputException{
		testCar.setUltrasonicSensor1(-1);
		testCar.setUltrasonicSensor2(-1);
		testCar.isEmpty();
	}
	
	@Test(expected=WrongInputException.class)
	public void testIsEmptyHighInput() throws WrongInputException{
		testCar.setUltrasonicSensor1(201);
		testCar.setUltrasonicSensor2(201);
		testCar.isEmpty();
	}
	
	
	//tests for moveBackward
	@Test
	public void testMoveBackward() throws WrongInputException{
		testCar.setLocation(5);
		testCar.moveBackward();
		assertEquals(4,testCar.getPosition().getLocation());
	}
	
	@Test
	public void testMoveBackwardTooMutch() throws WrongInputException{
		testCar.setLocation(0);
		testCar.moveBackward();
		assertEquals(0,testCar.getPosition().getLocation());
	}
	
	@Test(expected=WrongInputException.class)
	public void testMoveBackwardsLowInput() throws WrongInputException{
		testCar.setLocation(-5);
		testCar.moveBackward();
	}
	
	@Test(expected=WrongInputException.class)
	public void testMoveBackwardsHighInput() throws WrongInputException{
		testCar.setLocation(501);
		testCar.moveBackward();
	}
	
	//tests for Park
	@Test
	public void testPark(){
		testCar.setPosition(5, 5);
		testCar.park();
		assertTrue(testCar.getParked());
	}
	
	//Tests for unPark
	@Test
	public void testUnPark(){
		testCar.unPark();
		assertFalse(testCar.getParked());
	}
	
	//Tests for whereIs
	@Test
	public void testWhereIs(){
		Position position = testCar.whereIs();
		assertTrue(position.getLocation()>=0 && position.getLocation()<=500);
	}
	
	@Test
	public void testWhereIsNotNull(){
		Position position = testCar.whereIs();
		assertNotNull(position);
	}
	
}





