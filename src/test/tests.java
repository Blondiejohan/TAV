//package test;
//
//import static org.junit.Assert.*;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//
//import main.Car;
//import main.NoSensorInputException;
//import main.ParkingSpot;
////import main.ParkingSpot;
//import main.Position;
//import main.WrongInputException;
//public class tests {
//	
//	Car testCar;
//	
//	
//	// here we set up a car with standard values that is used for each test.
//	
//	
//	@Before
//	public void setUp() throws WrongInputException{
//		
//		
//		testCar = new Car(0,false);
//	}
//	
//	// Tests for moveForward.
//	
//	
//	@Test //when isEmpty returns a value >100
//	public void testMoveForwardLess() throws NoSensorInputException, WrongInputException{
//		int[] tmpArr1 = {1,1,1,1,1};
//		testCar.getSensor1().setUltrasonicArray(tmpArr1);
//		testCar.getSensor2().setUltrasonicArray(tmpArr1);
//		testCar.moveForward();
//		assertEquals(0,testCar.getCounter()[0]);
//	}
//
//	// This tests moving forward when there is a free spot open.
//	@Test
//	public void testMoveForwardMore() throws NoSensorInputException, WrongInputException{
//		int[] tmpArr1 = {120,120,120,120,120};
//		testCar.getSensor1().setUltrasonicArray(tmpArr1);
//		testCar.getSensor2().setUltrasonicArray(tmpArr1);
//		testCar.moveForward();
//		assertEquals(1,testCar.getCounter()[0]);
//
//	}
//	
//	// This tests the normal move forward to make sure the methods behaves correctly
//	@Test
//	public void testMoveForward() throws NoSensorInputException, WrongInputException{
//		int[] tmpArr1 = {1,1,1,1,1};
//		testCar.getSensor1().setUltrasonicArray(tmpArr1);
//		testCar.getSensor2().setUltrasonicArray(tmpArr1);
//		testCar.moveForward();
//		assertEquals(1,testCar.getMovementController().getLocation());
//	}
//	
//	
//	// This tests if the starting location when creating the car in a low incorrect value.
//	@Test(expected=WrongInputException.class)
//	public void testMoveForwardLowInput() throws WrongInputException, NoSensorInputException{
//		testCar.getMovementController().setLocation(-5);
//		testCar.moveForward();
//	
//	}
//	
//	// This tests if the starting location when creating the car in a high incorrect value.
//	@Test(expected=WrongInputException.class)
//	public void testMoveForwardHighInput() throws WrongInputException, NoSensorInputException{
//		testCar.getMovementController().setLocation(501);
//		testCar.moveForward();
//	}
//	
//	
//	//Tests for isEmpty
//	
//	// This tests if the isEmpty method behaves correctly under normal circumstances.
//	@Test
//	public void testIsEmpty() throws WrongInputException, NoSensorInputException{
//		int distance = testCar.isEmpty();
//		assertEquals(1,distance);
//	}
//	
//	// This tests if isempty works as intended when one sensor is giving bad values
//	@Test
//	public void testIsEmptyNr1Broken() throws WrongInputException, NoSensorInputException{
//		int[] tmpArr = {9,30,70,20,80};
//		
//		testCar.getSensor1().setUltrasonicArray(tmpArr);
//		assertEquals(1,testCar.isEmpty());
//	}
//	
//	// This tests if isempty works as intended when the other sensor sensor is giving bad values
//
//	@Test
//	public void testIsEmptyNr2Broken() throws WrongInputException, NoSensorInputException{
//int[] tmpArr = {9,30,70,20,80};
//		
//		testCar.getSensor2().setUltrasonicArray(tmpArr);		
//		assertEquals(1,testCar.isEmpty());
//	}
//	
//	
//	
//	// This tests if the sensors are giving non consistent values
//	@Test(expected=NoSensorInputException.class)
//	public void testIsEmptyNoise() throws NoSensorInputException, WrongInputException{
//		int[] tmpArr1 = new int[]{40,10,50,20,1};
//		int[] tmpArr2 = new int[]{30,10,80,100,5};
//		testCar.getSensor1().setUltrasonicArray(tmpArr1);
//		testCar.getSensor2().setUltrasonicArray(tmpArr2);
//		testCar.isEmpty();
//	}
//	
//	// This tests if the sensors is giving bad input for negative number for sensor 1
//	@Test(expected=NoSensorInputException.class)
//	public void testIsEmptyLowInput1() throws WrongInputException, NoSensorInputException{
//		int[] tmpArr1 = {-1,-1,-1,-1,-1};
//		int[] tmpArr2 = {40,10,50,20,1};
//		testCar.getSensor1().setUltrasonicArray(tmpArr1);
//		testCar.getSensor2().setUltrasonicArray(tmpArr2);
//		testCar.isEmpty();
//	}
//	// This tests if the sensors is giving bad input for negative number for sensor 2
//	@Test(expected=NoSensorInputException.class)
//	public void testIsEmptyLowInput2() throws WrongInputException, NoSensorInputException{
//		int[] tmpArr1 = {-1,-1,-1,-1,-1};
//		int[] tmpArr2 = {40,10,50,20,1};
//		testCar.getSensor1().setUltrasonicArray(tmpArr2);
//		testCar.getSensor2().setUltrasonicArray(tmpArr1);
//		testCar.isEmpty();
//	}
//	
//	// This tests if the sensor1 is giving bad input for number above 200
//	@Test(expected=NoSensorInputException.class)
//	public void testIsEmptyHighInput1() throws WrongInputException, NoSensorInputException{
//		int[] tmpArr1 = {201,201,201,201,201};
//		int[] tmpArr2 = {40,10,50,20,1};
//		
//		testCar.getSensor1().setUltrasonicArray(tmpArr2);
//		testCar.getSensor2().setUltrasonicArray(tmpArr1);
//		testCar.isEmpty();
//	}
//	
//	// This tests if the sensor2 is giving bad input for number above 200
//		@Test(expected=NoSensorInputException.class)
//		public void testIsEmptyHighInput2() throws WrongInputException, NoSensorInputException{
//			int[] tmpArr1 = {201,201,201,201,201};
//			int[] tmpArr2 = {40,10,50,20,1};
//			
//			testCar.getSensor1().setUltrasonicArray(tmpArr1);
//			testCar.getSensor2().setUltrasonicArray(tmpArr2);
//			testCar.isEmpty();
//		}
//	//Test for the correct input detected by the sensors
//	@Test
//	public void testIsEmptyCorrectInput() throws WrongInputException, NoSensorInputException {
//		int[] tmpArr1 = {100,95,97,101,100};
//		int[] tmpArr2 = {99,98,102,105,101};
//		testCar.getSensor1().setUltrasonicArray(tmpArr1);
//		testCar.getSensor2().setUltrasonicArray(tmpArr2);
//		assertEquals(99, testCar.isEmpty());
//		
//	}
//	
//	//tests for moveBackward
//	
//	// This tests if the method move backward behaves correctly under normal run.
//	@Test
//	public void testMoveBackward() throws WrongInputException{
//		testCar.getMovementController().setLocation(5);
//		testCar.moveBackward();
//		assertEquals(4,testCar.getMovementController().getLocation());
//	}
//	
//	// This tests that the car cant move backwards when there is no street
//	@Test
//	public void testMoveBackwardTooMutch() throws WrongInputException{
//		testCar.getMovementController().setLocation(0);
//		testCar.moveBackward();
//		assertEquals(0,testCar.getMovementController().getLocation());
//	}
//	
////	// This tests if the input values is incorrect with too small number
////	@Test(expected=WrongInputException.class)
////	public void testMoveBackwardsLowInput() throws WrongInputException{
////		testCar.getMovementController().getPosition().setLocation(-5);
////		testCar.moveBackward();
////	}
////	
////	// This tests if the input values is incorrect with too big number
////	@Test(expected=WrongInputException.class)
////	public void testMoveBackwardsHighInput() throws WrongInputException{
////		testCar.getMovementController().getPosition().setLocation(501);
////		testCar.moveBackward();
////	}
//		
//	//tests for Park
//	
//	
//	// This tests if the method parks correctly during normal run
//	@Test
//	public void testPark() throws WrongInputException, NoSensorInputException{
//		testCar.getMovementController().setLocation(1);
//		testCar.setBestSpot(new ParkingSpot(3,5));
//		testCar.park();
//		assertTrue(testCar.getMovementController().isParked());
//	}
//	
//	// This tests if the car can move forward while it is parked
//	@Test
//	public void testParkMoveForward() throws WrongInputException, NoSensorInputException{
//		testCar.getMovementController().setParked(true);
//		testCar.moveForward();
//		assertEquals(0,testCar.getMovementController().getLocation());
//	}
//	
//	// This tests if the car can move backward while it is parked
//	@Test
//	public void testParkMoveBackward() throws WrongInputException{
//		testCar.getMovementController().setLocation(1);
//		testCar.getMovementController().setParked(true);
//		testCar.moveBackward();
//		assertEquals(1,testCar.getMovementController().getLocation());
//	}
//	
//	
//	
//	//Tests for unPark
//	
//	// This tests if the method behaves correctly when the car is not parked
//	@Test
//	public void testUnPark(){
//		testCar.unPark();
//		assertFalse(testCar.getMovementController().isParked());
//	}
//	
//	// This tests if the method fails when out of position
//		@Test(expected=WrongInputException.class)
//		public void testUnParkOutOfPosition() throws WrongInputException{
//			testCar.getMovementController().setLocation(-5);
//			testCar.unPark();
//		}
//	
//	//Tests for whereIs
//	
//	// Tests if current value of location is a legal one
//	@Test
//	public void testWhereIsLocation(){
//		int position = testCar.whereIs();
//		assertTrue(position>=0 && position<=500);
//	}
//	
//	
//	// Tests if position is not null
//	@Test
//	public void testWhereIsNotNull(){
//		int position = testCar.whereIs();
//		assertNotNull(position);
//	}
//	
//}
//
//
//
//
//
