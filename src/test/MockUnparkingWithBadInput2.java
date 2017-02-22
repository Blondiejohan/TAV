package test;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import main.Car;
import main.MovementController;
import main.NoSensorInputException;
import main.UltrasonicSensor;
import main.WrongInputException;
//@RunWith (MockitoJUnitRunner.class)
public class MockUnparkingWithBadInput2 {
	Car testCar;
    
	   UltrasonicSensor sensor1;
	 
	   UltrasonicSensor sensor2;
	    
	   MovementController movement;
	  
	  int currentPosition;
	  
	  @Before
	    public void setUp() throws WrongInputException, NoSensorInputException{
			testCar = new Car(0,false);	
	    }
	
	@Test(expected=NoSensorInputException.class)
	public void testUnparkingWithBadinput2() throws WrongInputException, NoSensorInputException{
	
		
		sensor1 = mock(UltrasonicSensor.class);
		sensor2 = mock(UltrasonicSensor.class);
		//movement = mock(MovementController.class);
		testCar.setSensor1(sensor1);
		testCar.setSensor2(sensor2);
		//testCar.setMovementController(movement);
		
		
		
		int[] tmp1 = {20,20,20,20,20};
		int[] badSensor = {-5,-5,-5,-5,-5};
		
		testCar.unPark();
		
		
		when(sensor1.getUltrasonicArray()).thenReturn(tmp1);
		when(sensor2.getUltrasonicArray()).thenReturn(tmp1);
		
		while (currentPosition<500){
			
			//System.out.println(currentPosition);
			testCar.moveForward();
			currentPosition++;
			
			if(currentPosition >= 0 && currentPosition <= 15){
				//when(sensor.isEmpty()).thenReturn(20);
				when(sensor1.getUltrasonicArray()).thenReturn(tmp1);
				when(sensor2.getUltrasonicArray()).thenReturn(badSensor);
			}	
		}	
	}

}

