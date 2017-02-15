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
	

	MovementControllerInterface move;
	
	@Mock
	private Position position;
	
	@Mock
	private ParkingSpot bestSpot;
	
	@Mock
    Car testCar;
    
    @Before
    public void create() throws WrongInputException{
    	//Position position1 = new Position(0,0,false);
    	int[] tmpArr3 = {1,1,1,1,1};
		int[][] tmpArr1 = new int[501][5];
		int[][] tmpArr2 = new int[501][5];
		for(int i = 0 ; i<500 ; i++){
			tmpArr1[i] = tmpArr3;
			tmpArr2[i] = tmpArr3;
		}
		testCar = new Car(0,false,tmpArr1, tmpArr2);
		//move.setLocation(0);
    	move = mock(MovementControllerInterface.class); //create MoveController mock object
    	
    	when(move.accelerate()).thenReturn();
    	
    }

    @Test
    public void testMovementForward(){
        //assertEquals(0,move.getLocation());
        
        //verify(move, times(1)).getLocation();
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



