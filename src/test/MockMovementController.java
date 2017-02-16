package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
import main.WrongInputException;

@RunWith (MockitoJUnitRunner.class)
public class MockMovementController implements MovementControllerInterface {
	
	
	Position move;
	
	@Mock
	Position position;
	
	@Mock
	ParkingSpot bestSpot;
	
	@Mock
    Car testCar;
	
    @Before
    public void setUp() throws WrongInputException{
    	initMocks(this);
    	int[] tmpArr3 = {1,1,1,1,1};
		int[][] tmpArr1 = new int[501][5];
		int[][] tmpArr2 = new int[501][5];
		for(int i = 0 ; i<500 ; i++){
			tmpArr1[i] = tmpArr3;
			tmpArr2[i] = tmpArr3;
		}
		
		testCar = new Car(0,false,tmpArr1, tmpArr2);
		position = new Position(0,false);
		bestSpot = new ParkingSpot(0,0);
		
    	move = mock(Position.class); //create MoveController mock object
    	when(move.getLocation()).thenReturn(0);
    	
    	//when
    	//verify
    	System.out.println(position.getLocation());
    }

    private void initMocks(MockMovementController mockMovementController) throws WrongInputException {
    	//mockMovementController.accelerate();
    	mockMovementController.position.getLocation();
		// TODO Auto-generated method stub
		
	}

	@Test
    public void testMovementForward() throws WrongInputException{
       
		assertSame(move.getLocation(),0);
        //verify(move).;
        verify(move, times(5)).getLocation();
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



