package main;

public interface carInterface {

	/**
	 * 
	  This method moves the card forward one meter and checks if there is an open parking spot
	  
	  Pre-condition: car is between 0 and 500 meter of the road.

	  Post-condition: if the car is on meter 499 or less it moves one meter forward.
	  
	  Test-cases:

	*/
	public void moveForward();
	
	/**

	  Description

	  Pre-condition: Car object

	  Post-condition: distance to target.
	  
	  Test-cases:

	*/
	public int isEmpty() throws WrongInputException;
	
	/**

	  Moves the car backwards one meter.
	  
	  Pre-condition: Car object

	  Post-condition:  car object
	  
	  Test-cases:
	 * @return 

	*/
	public void moveBackward();
	
	/**

	  The car drives untill it finds a parking spot and parks there.

	  Pre-condition: Car object

	  Post-condition: boolean Parked.
	  
	  Test-cases:

	*/
	public boolean park();
	/**

	  This method drives itself out of the currect parking spot out to the road.

	  Pre-condition: Car object

	  Post-condition: boolean park.
	  
	  Test-cases:

	*/
	public boolean unPark();
	
	/**

	  This car checks the current position of the car and if it is parked or not.

	  Pre-condition: Car object

	  Post-condition: Car object
	  
	  Test-cases:
	 * @return 

	*/
	public Position whereIs();
	
}
