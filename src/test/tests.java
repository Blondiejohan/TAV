package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Car;
import main.WrongInputException;
public class tests {
	
	Car testCar;
	
	@Before
	public void setUp() throws WrongInputException{
		testCar = new Car(0,false,0,0);
	}

	@Test
	public void testMoveForward(){
		testCar.moveForward();
		assertEquals(1,testCar.getLocation());
	}
	
	@Test
	public void testMoveForwardTooMutch() throws WrongInputException{
		testCar.setLocation(499);
		testCar.moveForward();
		assertEquals(500,testCar.getLocation());
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
}
