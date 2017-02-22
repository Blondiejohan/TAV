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
public class MockUnparkingWithBadInput1 {
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
			// and then returns sensor values above 200
			@Test(expected=NoSensorInputException.class)
			public void testUnparkingWithBadsensors() throws WrongInputException, NoSensorInputException{
			
				
				sensor1 = mock(UltrasonicSensor.class); // mocking of sensors
				sensor2 = mock(UltrasonicSensor.class);

				testCar.setSensor1(sensor1); // setting mocked sensors to testCar
				testCar.setSensor2(sensor2);

				
				
				
				int[] tmp1 = {20,20,20,20,20};
				int[] tmp2 = {120,120,120,120,120};
				int[] badSensor1 = {1,38,183,45,135}; // too high sensor values
				
				
				testCar.unPark(); //unparks
				
				
				when(sensor1.getUltrasonicArray()).thenReturn(tmp1);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp1);
				
				// while the position is less than 500, move forward
				// with too high sensor values and crash
				while (currentPosition<500){ 
				
				
					

					testCar.moveForward();
					currentPosition++;
					
					if(currentPosition >= 0 && currentPosition <= 15){

						when(sensor1.getUltrasonicArray()).thenReturn(badSensor1);
						when(sensor2.getUltrasonicArray()).thenReturn(badSensor1);
					}	
				}	
			}
			
}