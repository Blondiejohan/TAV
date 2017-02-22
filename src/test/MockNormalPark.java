package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import main.Car;
import main.MovementController;
import main.NoSensorInputException;
import main.ParkingSpot;
import main.UltrasonicSensor;
import main.WrongInputException;

//@RunWith (MockitoJUnitRunner.class)
public class MockNormalPark {
	
	
    Car testCar;
    
   UltrasonicSensor sensor1;
 
   UltrasonicSensor sensor2;
    
   MovementController movement;
  
  int currentPosition;
   
	
	// Set up the test car
    @Before
    public void setUp() throws WrongInputException, NoSensorInputException{
		testCar = new Car(0,false);
		
    }
    	// This test scenario uses moveForward, isEmpty and Park. 
		// It goes through a normal run with normal values for isEmpty, not empty and also bad values
		// to park the car in the best spot
		@Test
		public void testParking() throws WrongInputException, NoSensorInputException{
		sensor1 = mock(UltrasonicSensor.class); // mocking of the two sensors
		sensor2 = mock(UltrasonicSensor.class);
		movement = mock(MovementController.class);
		testCar.setSensor1(sensor1); // setting the mocked sensor values to testCar
		testCar.setSensor2(sensor2);
		testCar.setMovementController(movement);
		

		boolean parked = false; // not parked
		
		currentPosition=0; // starts at position 0 of the street
		
		
		when(movement.getLocation()).thenReturn(currentPosition);
		when(movement.isParked()).thenReturn(parked);
		
		

		
		int[] tmp1 = {20,20,20,20,20}; // the value 20 means 1m parking spot is taken
		int[] tmp2 = {120,120,120,120,120}; // the value 120 means 1m parking spot is free
		int[] badSensor = {1,38,183,45,135}; // irregular sensor values
		
		
		when(sensor1.getUltrasonicArray()).thenReturn(tmp1); // replacing the mocked sensor values with 20 and 120
		when(sensor2.getUltrasonicArray()).thenReturn(tmp1);
		
		//as long as the car position is less than 500 keep moving forward and return the mocked sensor values
		while (currentPosition<500){
			
			testCar.moveForward();
			currentPosition++;
			int test=testCar.getMovementController().getLocation();
		
			
			if(currentPosition >= 0 && currentPosition <= 15){ // from position 0 to 15 on the street, sensor 1 and 2 will have the values of tmp1
				when(sensor1.getUltrasonicArray()).thenReturn(tmp1);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp1);
			}
			else if(currentPosition > 15 &&  currentPosition <= 18){ // from position 16 to 18 on the street, sensor 1 and 2 will have the values of tmp2
				when(sensor1.getUltrasonicArray()).thenReturn(tmp2);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp2);
				}
			else if(currentPosition > 18 && currentPosition <= 40){ // from position 19 to 40 on the street, sensor 1 will have bad values and sensor 2 will have the values of tmp1
				when(sensor1.getUltrasonicArray()).thenReturn(badSensor);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp1);
				}
			else if(currentPosition > 40 && currentPosition <= 44){ // from position 41 to 43 on the street, sensor 1 and sensor 2 will have the values of tmp2
				when(sensor1.getUltrasonicArray()).thenReturn(tmp2);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp2);
				}
			else if(currentPosition > 44 && currentPosition <= 80 ){ // from position 45 to 80 on the street, sensor 1 will have the values of tmp1 and sensor 2 will have bad values
				when(sensor1.getUltrasonicArray()).thenReturn(tmp1);
				when(sensor2.getUltrasonicArray()).thenReturn(badSensor);
			}
			else if(currentPosition > 80 && currentPosition<=85){ // from position 81 to 85 on the street, sensor 1 and sensor 2 will have the values of tmp2
				when(sensor1.getUltrasonicArray()).thenReturn(tmp2);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp2);
			}
			else if(currentPosition > 85 && currentPosition < 500){ // from position 86 to 500 on the street, sensor 1 and sensor 2 will have the values of tmp1
				when(sensor1.getUltrasonicArray()).thenReturn(tmp1);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp1);
			}
			
			
			
			
		}
		

		// parking the car in the best spot 
		testCar.park();
		//when(movement.getBestSpot()).thenReturn(new ParkingSpot(60,3));
		ParkingSpot bestSpot = testCar.getBestSpot();
		//System.out.println("bestSpot location :"+bestSpot.getLocation());
		
		
		when(movement.isParked()).thenReturn(true);
		
		assertEquals(testCar.whereIs(),testCar.getMovementController().getLocation());
		assertTrue(movement.isParked());
		
		}
		
		
}