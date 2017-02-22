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
   
	
	
    @Before
    public void setUp() throws WrongInputException, NoSensorInputException{
		testCar = new Car(0,false);
		
    }
		@Test
		public void testParking() throws WrongInputException, NoSensorInputException{
		sensor1 = mock(UltrasonicSensor.class);
		sensor2 = mock(UltrasonicSensor.class);
		movement = mock(MovementController.class);
		testCar.setSensor1(sensor1);
		testCar.setSensor2(sensor2);
		testCar.setMovementController(movement);
		
		

		boolean parked = false;
		
		currentPosition=0;
		
		
		when(movement.getLocation()).thenReturn(currentPosition);
		when(movement.isParked()).thenReturn(parked);
		
		

		
		int[] tmp1 = {20,20,20,20,20};
		int[] tmp2 = {120,120,120,120,120};
		int[] badSensor = {1,38,183,45,135};
		
		
		when(sensor1.getUltrasonicArray()).thenReturn(tmp1);
		when(sensor2.getUltrasonicArray()).thenReturn(tmp1);
		
		while (currentPosition<500){
			
			//System.out.println(currentPosition);
			System.out.println("before");
			currentPosition++;
			testCar.moveForward();
			System.out.println("after");
			//testCar.getMovementController().setLocation(currentPosition);
			//System.out.print(testCar.getMovementController().getLocation()+" ");
			//int test=testCar.getMovementController().getLocation();
		
			
			//at location 0 the parking is not available
			if(currentPosition >= 0 && currentPosition <= 15){
				//when(sensor.isEmpty()).thenReturn(20);
				when(sensor1.getUltrasonicArray()).thenReturn(tmp1);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp1);
			}
			else if(currentPosition > 15 &&  currentPosition <= 18){
				//when(sensor.isEmpty()).thenReturn(120);
				when(sensor1.getUltrasonicArray()).thenReturn(tmp2);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp2);
				}
			else if(currentPosition > 18 && currentPosition <= 40){
				//when(sensor.isEmpty()).thenReturn(20);
				when(sensor1.getUltrasonicArray()).thenReturn(badSensor);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp1);
				}
			else if(currentPosition > 40 && currentPosition <= 44){
				//when(sensor.isEmpty()).thenReturn(120);
				when(sensor1.getUltrasonicArray()).thenReturn(tmp2);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp2);
				}
			else if(currentPosition > 44 && currentPosition <= 80 ){
				//when(sensor.isEmpty()).thenReturn(20);
				when(sensor1.getUltrasonicArray()).thenReturn(tmp1);
				when(sensor2.getUltrasonicArray()).thenReturn(badSensor);
			}
			else if(currentPosition > 80 && currentPosition<=85){
				//when(sensor.isEmpty()).thenReturn(120);
				when(sensor1.getUltrasonicArray()).thenReturn(tmp2);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp2);
			}
			else if(currentPosition > 85 && currentPosition < 500){
				//when(sensor.isEmpty()).thenReturn(20);
				when(sensor1.getUltrasonicArray()).thenReturn(tmp1);
				when(sensor2.getUltrasonicArray()).thenReturn(tmp1);
			}
			
			
			
			
		}
		

		
		testCar.park();
		//when(movement.getBestSpot()).thenReturn(new ParkingSpot(60,3));
		ParkingSpot bestSpot = testCar.getBestSpot();
		//System.out.println("bestSpot location :"+bestSpot.getLocation());
		
		
		//when(movement.isParked()).thenReturn(true);
		
		assertEquals(testCar.whereIs(),testCar.getMovementController().getLocation());
		//assertTrue(movement.isParked());
		
	//preform advanced parking
		
		}
		
		
		@Test(expected=NoSensorInputException.class)
		public void testUnparkingWithBadsensors() throws WrongInputException, NoSensorInputException{
		
			
			sensor1 = mock(UltrasonicSensor.class);
			sensor2 = mock(UltrasonicSensor.class);
			//movement = mock(MovementController.class);
			testCar.setSensor1(sensor1);
			testCar.setSensor2(sensor2);
			//testCar.setMovementController(movement);
			
			
			
			int[] tmp1 = {20,20,20,20,20};
			int[] tmp2 = {120,120,120,120,120};
			int[] badSensor1 = {1,38,183,45,135};
			
			
			testCar.unPark();
			
			
			when(sensor1.getUltrasonicArray()).thenReturn(tmp1);
			when(sensor2.getUltrasonicArray()).thenReturn(tmp1);
			
			while (currentPosition<500){
				
				//System.out.println(currentPosition);
				testCar.moveForward();
				currentPosition++;
				
				if(currentPosition >= 0 && currentPosition <= 15){
					//when(sensor.isEmpty()).thenReturn(20);
					when(sensor1.getUltrasonicArray()).thenReturn(badSensor1);
					when(sensor2.getUltrasonicArray()).thenReturn(badSensor1);
				}	
			}	
		}
		
		
		
		@Test(expected=NoSensorInputException.class)
		public void testUnparkingWithBadinput() throws WrongInputException, NoSensorInputException{
		
			
			sensor1 = mock(UltrasonicSensor.class);
			sensor2 = mock(UltrasonicSensor.class);
			//movement = mock(MovementController.class);
			testCar.setSensor1(sensor1);
			testCar.setSensor2(sensor2);
			//testCar.setMovementController(movement);
			
			
			
			int[] tmp1 = {20,20,20,20,20};
			int[] tmp2 = {120,120,120,120,120};
			int[] badSensor = {999,999,999,999,999};
			
			testCar.unPark();
			
			
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
		
		
		@Test(expected=NoSensorInputException.class)
		public void testUnparkingWithBadinput2() throws WrongInputException, NoSensorInputException{
		
			
			sensor1 = mock(UltrasonicSensor.class);
			sensor2 = mock(UltrasonicSensor.class);
			//movement = mock(MovementController.class);
			testCar.setSensor1(sensor1);
			testCar.setSensor2(sensor2);
			//testCar.setMovementController(movement);
			
			
			
			int[] tmp1 = {20,20,20,20,20};
			int[] tmp2 = {120,120,120,120,120};
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
