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
		int[] tmpArr1 = {1,1,1,1,1};
		int[] tmpArr2 = {1,1,1,1,1};
		testCar = new Car(0,false,tmpArr1,tmpArr2);
	}

	
	
	// Tests for moveForward.
	@Test
	public void testMoveForward(){
		testCar.moveForward();
		assertEquals(1,testCar.getPosition().getLocation());
	}
	
	@Test
	public void testMoveForwardTooMutch() throws WrongInputException{
		testCar.setPosition(500,0);
		testCar.moveForward();
		assertEquals(500,testCar.getPosition().getLocation());
	}
	
	@Test(expected=WrongInputException.class)
	public void testMoveForwardLowInput() throws WrongInputException{
		testCar.setPosition(-5,0);
		testCar.moveForward();
	}
	
	@Test(expected=WrongInputException.class)
	public void testMoveForwardHighInput() throws WrongInputException{
		testCar.setPosition(501,0);
		testCar.moveForward();
	}
	
	
	
	//Tests for isEmpty
	@Test
	public void testIsEmpty() throws WrongInputException{
		int distance = testCar.isEmpty();
		assertEquals(1,distance);
	}
	
	
	@Test
	public void testIsEmptyNoise() throws WrongInputException{
		int[] tmpArr1 = {40,10,50,20,1};
		int[] tmpArr2 = {30,10,80,100,5};
		testCar.setUltrasonicSensor1(tmpArr1);
		testCar.setUltrasonicSensor2(tmpArr2);
		
		assertEquals(0,testCar.isEmpty());
	}
	
	@Test(expected=WrongInputException.class)
	public void testIsEmptyLowInput() throws WrongInputException{
		int[] tmpArr1 = {-1,-1,-1,-1,-1};
		int[] tmpArr2 = {-1,-1,-1,-1,-1};
		testCar.setUltrasonicSensor1(tmpArr1);
		testCar.setUltrasonicSensor2(tmpArr2);
		testCar.isEmpty();
	}
	
	@Test(expected=WrongInputException.class)
	public void testIsEmptyHighInput() throws WrongInputException{
		int[] tmpArr1 = {201,201,201,201,201};
		int[] tmpArr2 = {201,201,201,201,201};
		testCar.setUltrasonicSensor1(tmpArr1);
		testCar.setUltrasonicSensor2(tmpArr2);
		testCar.isEmpty();
	}
	
	
	//tests for moveBackward
	@Test
	public void testMoveBackward() throws WrongInputException{
		testCar.setPosition(5,0);
		testCar.moveBackward();
		assertEquals(4,testCar.getPosition().getLocation());
	}
	
	@Test
	public void testMoveBackwardTooMutch() throws WrongInputException{
		testCar.setPosition(0,0);
		testCar.moveBackward();
		assertEquals(0,testCar.getPosition().getLocation());
	}
	
	@Test(expected=WrongInputException.class)
	public void testMoveBackwardsLowInput() throws WrongInputException{
		testCar.getPosition().setLocation(-5);
		testCar.moveBackward();
	}
	
	@Test(expected=WrongInputException.class)
	public void testMoveBackwardsHighInput() throws WrongInputException{
		testCar.getPosition().setLocation(501);
		testCar.moveBackward();
	}
	
	//tests for Park
	@Test
	public void testPark() throws WrongInputException{
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





