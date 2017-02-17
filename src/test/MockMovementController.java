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

@RunWith (MockitoJUnitRunner.class)
public class MockMovementController implements MovementControllerInterface {
	
	@Mock
	Position startingPoint;
	
	@Mock
	Position position;
	
	@Mock
	ParkingSpot bestSpot;
	
	@Mock
    Car testCar;
	
	@Mock
	UltrasonicSensor mockSensor;
	
	@Mock
	MovementControllerInterface actuator;
	
    @Before
    public void setUp() throws WrongInputException, NoSensorInputException{
    	initMocks(this);
		testCar = new Car(0,false);
		position = new Position(0,false);
		bestSpot = new ParkingSpot(0,0);
		
		//mockSensor = new UltrasonicSensor(tmpArr1,tmpArr2);  // This already have a sensor, if you mock the car you can just use Car.setSensor when you have created
		// a new one.
		
		//1. Car starts at the beggining of the street - DONE
    	startingPoint = mock(Position.class); //create Position mock object
    	when(startingPoint.getLocation()).thenReturn(0); //car is in position 0
    	
    	//2. Car moves forward - JUST TESTING PURPOSES
    	for (int i=0; i<500; i++){
    		testCar = mock(Car.class);
    		position = mock(Position.class);
    	when(testCar.moveForward()).thenReturn(getPosition());
    	when(position.getLocation()).thenReturn(i);
    	System.out.println(position.getLocation());
    	}
    	
    	
    	//bestSpot = mock(ParkingSpot.class); //create bestSpot mock object
    	//when(bestSpot.getSize()).thenReturn(3);
    	//when(bestSpot.getSize()).thenReturn(5);
    
    }
    
    @After
	public void tearDown() throws Exception {
	}

    private void initMocks(MockMovementController mockMovementController) throws WrongInputException, NoSensorInputException {
    	//mockMovementController.accelerate();
    	//mockMovementController.startingPoint.equals(0);
    	
    	//mockMovementController.bestSpot.getSize();
		
	}

	@Test
    public void testMovementForward() throws WrongInputException{
		
		//test if car is on the starting point - DONE
		assertSame(startingPoint.getLocation(),0);
		
		//test if car moves forward from 0 to 500
		for (int i=0; i<500; i++){
		assertEquals(i, position.getLocation());
		//verify(position, times(500)).equals(i);
		}
		
		
		//assertEquals(bestSpot.getSize(),i);
        //verify(startingPoint, times(1)).getLocation();
        //verify(bestSpot, times(1)).getSize();
    }
    
	
    
   	@Override
   	public void accelerate() throws WrongInputException{
   		getPosition().setLocation(getPosition().getLocation()+1);
		
		}
   		
   	
   	@Override
   	public void reverse() throws WrongInputException {
   		getPosition().setLocation(getPosition().getLocation()-1);
		
}
   	public Position getPosition() {
		return position;
	}
	//position setter 
	public void setPosition(Position position) {
		this.position = position;
	}

	public ParkingSpot getBestSpot() {
		return bestSpot;
	}

	public void setBestSpot(ParkingSpot bestSpot) {
		this.bestSpot = bestSpot;
	}
   	
}



