package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Car;
import main.NoSensorInputException;
import main.Position;
import main.WrongInputException;
public class tests {
	
	Car testCar;
	
	// here we set up a car with standard values that is used for each test.
	@Before
	public void setUp() throws WrongInputException{
		int[] tmpArr1 = {1,1,1,1,1};
		int[] tmpArr2 = {1,1,1,1,1};
		testCar = new Car(0,false,tmpArr1,tmpArr2);
	}

	
	
	// Tests for moveForward.
	
	// This tests the normal move forward too make sure the methods behaves correctly
	@Test
	public void testMoveForward() throws NoSensorInputException, WrongInputException{
		testCar.moveForward();
		assertEquals(1,testCar.getPosition().getLocation());
	}
	
	// This tests if we are trying to keep moving the car when the road ends.
	@Test
	public void testMoveForwardTooMutch() throws WrongInputException, NoSensorInputException{
		testCar.setPosition(500,0);
		testCar.moveForward();
		assertEquals(500,testCar.getPosition().getLocation());
	}
	
	// This tests if the starting location when creating the car in a low incorrect value.
	@Test(expected=WrongInputException.class)
	public void testMoveForwardLowInput() throws WrongInputException, NoSensorInputException{
		testCar.setPosition(-5,0);
		testCar.moveForward();
	}
	
	// This tests if the starting location when creating the car in a high incorrect value.
	@Test(expected=WrongInputException.class)
	public void testMoveForwardHighInput() throws WrongInputException, NoSensorInputException{
		testCar.setPosition(501,0);
		testCar.moveForward();
	}
	
	
	
	//Tests for isEmpty
	
	// This tests if the isEmpty method behaves correctly under normal circumstances.
	@Test
	public void testIsEmpty() throws WrongInputException, NoSensorInputException{
		int distance = testCar.isEmpty();
		assertEquals(1,distance);
	}
	
	// This tests if the sensors are giving non consistent values
	@Test(expected=NoSensorInputException.class)
	public void testIsEmptyNoise() throws NoSensorInputException, WrongInputException{
		int[] tmpArr1 = {40,10,50,20,1};
		int[] tmpArr2 = {30,10,80,100,5};
		testCar.setUltrasonicSensor1(tmpArr1);
		testCar.setUltrasonicSensor2(tmpArr2);
		testCar.isEmpty();
	}
	
	// This tests if the sensors is giving bad input for negative number
	@Test(expected=NoSensorInputException.class)
	public void testIsEmptyLowInput() throws WrongInputException, NoSensorInputException{
		int[] tmpArr1 = {-1,-1,-1,-1,-1};
		int[] tmpArr2 = {-1,-1,-1,-1,-1};
		testCar.setUltrasonicSensor1(tmpArr1);
		testCar.setUltrasonicSensor2(tmpArr2);
		testCar.isEmpty();
	}
	
	// This tests if the sensors is giving bad input for number above 200
	@Test(expected=NoSensorInputException.class)
	public void testIsEmptyHighInput() throws WrongInputException, NoSensorInputException{
		int[] tmpArr1 = {201,201,201,201,201};
		int[] tmpArr2 = {201,201,201,201,201};
		testCar.setUltrasonicSensor1(tmpArr1);
		testCar.setUltrasonicSensor2(tmpArr2);
		testCar.isEmpty();
	}
	
	
	//tests for moveBackward
	
	// This tests if the method move backards behaves correcltly under normal run.
	@Test
	public void testMoveBackward() throws WrongInputException{
		testCar.setPosition(5,0);
		testCar.moveBackward();
		assertEquals(4,testCar.getPosition().getLocation());
	}
	
	// This tests that the car cant move backwards when there is no street
	@Test
	public void testMoveBackwardTooMutch() throws WrongInputException{
		testCar.setPosition(0,0);
		testCar.moveBackward();
		assertEquals(0,testCar.getPosition().getLocation());
	}
	
	// This tests if the input values is incorrect with too small number
	@Test(expected=WrongInputException.class)
	public void testMoveBackwardsLowInput() throws WrongInputException{
		testCar.getPosition().setLocation(-5);
		testCar.moveBackward();
	}
	
	// This tests if the input values is incorrect with too big number
	@Test(expected=WrongInputException.class)
	public void testMoveBackwardsHighInput() throws WrongInputException{
		testCar.getPosition().setLocation(501);
		testCar.moveBackward();
	}
	
	
	
	//tests for Park
	
	
	// This tests if the method parks correctly during normal run
	@Test
	public void testPark() throws WrongInputException, NoSensorInputException{
		testCar.setPosition(5, 5);
		testCar.park();
		assertTrue(testCar.getPosition().isParked());
	}
	
	// This tests if the car can move forward while it is parked
	@Test
	public void testParkMoveForward() throws WrongInputException, NoSensorInputException{
		testCar.getPosition().setParked(true);
		testCar.moveForward();
		assertEquals(0,testCar.getPosition().getLocation());
	}
	
	// This tests if the car can move backward while it is parked
	@Test
	public void testParkMoveBackward() throws WrongInputException{
		testCar.setPosition(1, 0);
		testCar.getPosition().setParked(true);
		testCar.moveBackward();
		assertEquals(1,testCar.getPosition().getLocation());
	}
	
	
	//Tests for unPark
	
	// This tests if the method behaves correctly during normal run.
	@Test
	public void testUnPark(){
		testCar.unPark();
		assertFalse(testCar.getPosition().isParked());
	}
	
	
	//Tests for whereIs
	
	// Tests if current value of location is a legal one
	@Test
	public void testWhereIsLocation(){
		Position position = testCar.whereIs();
		assertTrue(position.getLocation()>=0 && position.getLocation()<=500);
	}
	
	// Tests if current value of parkingCounter is a legal one
		@Test
		public void testWhereIsParkingCounter(){
			Position position = testCar.whereIs();
			assertTrue(position.getCounter()>=0 && position.getCounter()<=5);
		}
	
	// Tests if position is not null
	@Test
	public void testWhereIsNotNull(){
		Position position = testCar.whereIs();
		assertNotNull(position);
	}
	
}





