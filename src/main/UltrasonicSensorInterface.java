package main;

public interface UltrasonicSensorInterface {

	
public int isEmpty() throws WrongInputException, NoSensorInputException;
	
	/**

	  The method moves the car one meter back
	  
	  Pre-condition: The car has to be within defined ranges

	  Post-condition: The car has moved one meter back
	  
	  Test-cases: 1. testMoveBackward(): Move backwards when position>0 && position<500
	              2. testMoveBackwardTooMutch(): Move backwards when position = 0
	              3. testMoveBackwardsLowInput(): Move backwards when position < 0
	              4. testMoveBackwardsHighInput(): Move backwards when position > 500
	 
	 * @return 
	 * @throws WrongInputException 

	*/
	
}
