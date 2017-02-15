package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import main.Car;
import main.MovementController;
import main.MovementControllerInterface;
import main.Position;
import main.WrongInputException;


public class MockMovementController {
	
	Car testCar;
    //Position position;
    
    @Mock
    Position move;
    
    @Before
    public void setup() throws WrongInputException{
    	move = mock(Position.class); //create MoveController mock object
    	when(move.getLocation()).thenReturn(0);
    	
    	testCar.getMovementController().getPosition();
    }
    
    

    @Test
    public void testMovementForward(){
        assertEquals(0,testCar.getMovementController().getPosition());
        
        verify(move, times(1)).getLocation();
    }
    
}


