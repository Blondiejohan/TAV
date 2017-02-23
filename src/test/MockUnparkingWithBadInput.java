package test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import main.Car;
import main.MovementController;
import main.NoSensorInputException;
import main.UltrasonicSensor;
import main.WrongInputException;
//@RunWith (MockitoJUnitRunner.class)
public class MockUnparkingWithBadInput {
	Car testCar;
    
	   UltrasonicSensor sensor1;
	 
	   UltrasonicSensor sensor2;
	    
	   MovementController movement;
	  
	  int currentPosition;
	  @Before
	    public void setUp() throws WrongInputException, NoSensorInputException{
			testCar = new Car(0,false);	
	    }
	  
	// This test scenario unparks the car 
	// and then returns too high sensor values making the car crash
	@Test(expected=NoSensorInputException.class)
	public void testUnparkingWithBadinput() throws WrongInputException, NoSensorInputException{
		
		sensor1 = mock(UltrasonicSensor.class); // mocking of sensors
		sensor2 = mock(UltrasonicSensor.class);
		//movement = mock(MovementController.class);
		testCar.setSensor1(sensor1); // setting mocked sensors to testCar
		testCar.setSensor2(sensor2);
		//testCar.setMovementController(movement);
		
		
		
		int[] tmp1 = {20,20,20,20,20};
		int[] badSensor = {999,999,999,999,999}; // too high sensor values
		
		testCar.unPark(); // unparks
		
		
		when(sensor1.getUltrasonicArray()).thenReturn(tmp1);
		when(sensor2.getUltrasonicArray()).thenReturn(tmp1);
		
		while (currentPosition<500){
			
			//System.out.println(currentPosition);
			testCar.moveForward();
			currentPosition++;
			
			if(currentPosition >= 0 && currentPosition <= 15){
				//when(sensor.isEmpty()).thenReturn(20);
				when(sensor1.getUltrasonicArray()).thenReturn(badSensor);
				when(sensor2.getUltrasonicArray()).thenReturn(badSensor);
			}	
		}
	}
}
