package main;

public interface carInterface {

	/**
	 * 
	  The method checks whether the car is within the track range and it moves one meter forward.
	  
	  Pre-condition: The car is between 0 and 499 meters.

	  Post-condition:  If conditions are met, the car has moved one meter further.
	  
	  Test-cases: 1. testMoveForward(): Move forward when position>=0 && position<=499
	              2. testMoveForwardTooMutch(): Move forward when position>500
	              3. testMoveForwardLowInput(): Move forward when position<0
	              4. testMoveForwardHighInput(): Move forward when position=500
	 
	 * @throws WrongInputException 
	

	*/
	public Position moveForward() throws NoSensorInputException, WrongInputException;
	
	/**

	  The car queries two ultrasonic sensors to get the distance from objects in order to establish if there is an empty parking space.

	  Pre-condition: Both sensors are fully functional in delivering distance

	  Post-condition: Distance is delivered after noise filtering.
	  
	  Test-cases: 1. testIsEmpty(): returns the distance to the nearest object with the two sensors behave good
	              2. testIsEmptyNoise(): Sensors values>0 && Sensors values<200
	              3. testIsEmptyLowInput(): Sensors values<0
	              4. testIsEmptyHighInput(): Sensors values>200
	

	*/
	
	public void moveBackward() throws WrongInputException;
	
	/**

	  The car moves to the start of the free parking spot or until it reaches the end of the street detects the next free parking spot. 

	  Pre-condition: The car has detected a five meter parking spot

	  Post-condition: The car is parked
	  
	  Test-cases: 1. testPark(): When car detects an available parking spot, it's parking.
	              2. testParkMoveForward(): Parked car can move forward.
	              3. testParkMoveBackward(): Parked car can move backward.
	 
	 * @throws WrongInputException 
	 * @throws NoSensorInputException 

	*/
	public void park() throws NoSensorInputException, WrongInputException;
	/**

	  The parked car exits the parking slot and continues the navigation.

	  Pre-condition: The car is already parked

	  Post-condition: The car has completely exited the parking slot and it is again in circulation.
	  
	  Test-cases: 1. testUnPark() : Parked car moves forward and to the left
	              2. testUnParkOutOfPosition(): Test if car position is out of bounds

	*/
	public void unPark();
	
	/**

	  This method checks the position of the car on the street and its situation.

	  Pre-condition: The car is within the defined ranges (0-500)

	  Post-condition: Car position and situation remained unmodified
	  
	  Test-cases: 1. testWhereIsLocation(): Car position >= 0 && car position <=500
	              2. testWhereIsNotNull(): Car position doesn't have a null value
	              3. testWhereIsParkingCounter(): Car is parked
	 * @return 

	*/
	public Position whereIs();
	
}
