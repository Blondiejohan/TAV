package main;

public interface carInterface {

	/**
	 * 
	  The method checks whether the car is within the track range and it moves one meter forward.
	  
	  Pre-condition: The car is between 0 and 499 meters.

	  Post-condition:  If conditions are met, the car has moved one meter further.
	  
	  Test-cases: 	testMoveForward(): Move forward when position>0 && position<499
	  				testMoveForwardLowInput(): Move forward when position<0
	  				testMoveForwardTooMutch(): Move forward when position>500
	  				testMoveForwardHighInput(): Move forward when position=500
	 * @throws WrongInputException 
	

	*/
	public Position moveForward() throws NoSensorInputException, WrongInputException;
	
	/**

	  The car queries two ultrasonic sensors to get the distance from objects in order to establish if there is an empty parking space.

	  Pre-condition: Both sensors are fully functional in delivering distance

	  Post-condition: Distance is delivered after noise filtering.
	  
	  Test-cases:	testIsEmpty(): 
	  				testIsEmptyNoise(): Sensors values>0 && Sensor values<200
	  				testIsEmptyLowInput(): Sensors values<0
	  				testIsEmptyHighInput(): Sensors values>200
	

	*/
	public int isEmpty() throws WrongInputException, NoSensorInputException;
	
	/**

	  The method moves the car one meter back
	  
	  Pre-condition: The car has to be within defined ranges

	  Post-condition: The car has moved one meter back
	  
	  Test-cases: 	testMoveBackward(): Move backwards when position = 5
					testMoveBackwardTooMutch(): Move backwards when position = 0
					testMoveBackwardsLowInput(): Move backwards when position < 0
					testMoveBackwardsHighInput(): Move backwards when position > 500
					Move backwards when parked
	 * @return 

	*/
	public void moveBackward();
	
	/**

	  The car moves to the start of the free parking spot or until it reaches the end of the street detects the next free parking spot. 

	  Pre-condition: The car has detected a five meter parking spot

	  Post-condition: The car is parked
	  
	  Test-cases:	testPark(): Car can park
	  				testParkMoveForward(): Car is parked and can move forward
	  				testParkMoveBackward(): Car is parked and can move backward
	 * @throws WrongInputException 
	 * @throws NoSensorInputException 

	*/
	public void park() throws NoSensorInputException, WrongInputException;
	/**

	  The parked car exits the parking slot and continues the navigation.

	  Pre-condition: The car is already parked

	  Post-condition: The car has completely exited the parking slot and it is again in circulation.
	  
	  Test-cases:	Car is parked
	  				Car can move forward
	  				Car can move backward

	*/
	public void unPark();
	
	/**

	  This method checks the position of the car on the street and its situation.

	  Pre-condition: The car is within the defined ranges (0-500)

	  Post-condition: Car position and situation remained unmodified
	  
	  Test-cases:	testWhereIsLocation(): Car position >= 0 && car position <=500
	  				testWhereIsNotNull(): Car position doesn't have a null value
	  				testWhereIsParkingCounter(): Car detects parking
	 * @return 

	*/
	public Position whereIs();
	
}
