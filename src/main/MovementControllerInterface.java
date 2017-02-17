package main;

public interface MovementControllerInterface {

	
	/**
	 * 
	  This method moves the car one meter forward
	  
	  Pre-condition: The car is between 0 and 499 meters.

	  Post-condition:  the car has moved one meter forward.
	  
	  Test-cases: 
	
	 
	 
	

	*/
	public void accelerate () throws WrongInputException, NoSensorInputException;
	
	
	/**
	 * 
	  This method moves the car one meter backward
	  
	  Pre-condition: The car is between 1 and 500 meters.

	  Post-condition: the car has moved one meter bakwards.
	  
	  Test-cases: 
	  
	
	 
	

	*/
	public void reverse() throws WrongInputException, NoSensorInputException;
}
