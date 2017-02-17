package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import main.Car;
import main.MovementController;
import main.MovementControllerInterface;
import main.NoSensorInputException;
import main.ParkingSpot;
import main.Position;
import main.UltrasonicSensor;
import main.WrongInputException;
import main.carInterface;

@RunWith (MockitoJUnitRunner.class)
public class MockNormalPark {
	
	
    Car testCar;
	
	
    @Before
    public void setUp() throws WrongInputException, NoSensorInputException{
    	//initMocks(this);
		testCar = new Car(0,false);
		
		//mockSensor = mock(UltrasonicSensor.class);
		//when(mockSensor.getUltrasonicArray1()
    }
		@Test
		public void testNormalPark() throws WrongInputException, NoSensorInputException{
		testCar = mock(carInterface.class);
		
		for (int i=0; i<500; i++){
			
			//at location 0 the parking is not available
			if(i > 0 && i < 15){
				when(testCar.isEmpty()).thenReturn(120);
				}
			else if(i > 15 &&  i < 18){
				when(testCar.isEmpty()).thenReturn(20);
				}
			else if(i > 18 && i < 40){
				when(testCar.isEmpty()).thenReturn(120);
				}
			else if(i > 40 && i < 44){
				when(testCar.isEmpty()).thenReturn(20);
				}
			else if(i > 44 && i < 80 ){
				when(testCar.isEmpty()).thenReturn(120);
			}
			else if(i > 80 && i<85){
				when(testCar.isEmpty()).thenReturn(20);
			}
			else if(i > 85 && i < 500){
				when(testCar.isEmpty()).thenReturn(120);
			}
			testCar.moveForward();
			
		}
	
		int bestSpot = testCar.getMovementController().getBestSpot().getLocation();
		
		for(int i = 500 ; i <bestSpot ; i--){
			testCar.moveBackward();
		}		
	//preform advanced parking
		}
}
