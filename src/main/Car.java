package main;
import org.mockito.*;

//implementation of the car class

public class Car implements carInterface {

	//position initialization
	private UltrasonicSensor sensor;
	private MovementController movementController;

	//constructor which initialize all needed objects
	//the car object is needed as precondition to most of the test cases
	
	public Car(int location, boolean parked, int[][] ultrasonicSensor1, int[][] ultrasonicSensor2) throws WrongInputException{
		
		setSensor(new UltrasonicSensor(ultrasonicSensor1,ultrasonicSensor2));
		
		
		//position is an object containing an integer location, a counter and a boolean parked
		setMovementController(new MovementController(location,0,parked));
		
	}
	

		//moveForward implementation
	// used for the implementation of moveForward, moveBackwards
	@Override
	public Position moveForward() throws NoSensorInputException, WrongInputException {
		
		int parkingSpot = 0;
		
		//if the car is not parked
		if(!movementController.getPosition().isParked()){
		
		//move forward
			if(getMovementController().getPosition().getLocation() < 500 && getMovementController().getPosition().getLocation()>= 0){
			movementController.accelerate();
			
			if(isEmpty()>100){ //it means that the spot is empty
				movementController.getPosition().setCounter(parkingSpot, 1);
				parkingSpot++;
			}else{
				movementController.getPosition().setCounter(parkingSpot, 0);
				parkingSpot++;
			}
		}//if it detects spacing places it saves them in the counter
		
		}
		return movementController.getPosition();
	}
	//isEmpty implementation method
	//used for isEmpty test cases
	@Override
	public int isEmpty() throws WrongInputException, NoSensorInputException {
		int sumSensor1 = 0;		//sensors declaration
		int sumSensor2 = 0;
		int result = 0;
		boolean sensorBool1=true;	// sensors are active
		boolean sensorBool2=true;
		int lastValueSensor1= sensor.getUltrasonicArray1(getMovementController().getPosition().getLocation())[0];		//sensor arrays are initialized with the last value at position 1
		int lastValueSensor2 = sensor.getUltrasonicArray2(getMovementController().getPosition().getLocation())[0];	
		
		//array check
		for(int i = 0;i<sensor.getUltrasonicArray1(getMovementController().getPosition().getLocation()).length;i++){
			if(sensor.getUltrasonicArray1(getMovementController().getPosition().getLocation())[i] <0 || sensor.getUltrasonicArray1(getMovementController().getPosition().getLocation())[i]>200){		//ultrasonic sensor1 out of boundary check
				throw new NoSensorInputException("Bad sensor input");	
			}
		}
		for(int i = 0;i<sensor.getUltrasonicArray2(getMovementController().getPosition().getLocation()).length;i++){						//ultrasonic sensor 2 out of boundary check 
			if(sensor.getUltrasonicArray2(getMovementController().getPosition().getLocation())[i] <0 || sensor.getUltrasonicArray2(getMovementController().getPosition().getLocation())[i]>200){
				throw new NoSensorInputException("bad sensor input");
			}
		}
		
		for(int i = 0;i<5;i++){			/*requirements implementation of 5 times filtering through averaging
			data filtering: the sensor is "disabled" if the current value is less/greater 
			than the last value +/- 10 */
			if(sensor.getUltrasonicArray1(getMovementController().getPosition().getLocation())[i]+10 < lastValueSensor1 || sensor.getUltrasonicArray1(getMovementController().getPosition().getLocation())[i]-10 > lastValueSensor1){
				sensorBool1 = false;	
			}
			//data filtering for ultrasonic sensor 2
			if(sensor.getUltrasonicArray2(getMovementController().getPosition().getLocation())[i]+10 < lastValueSensor2 || sensor.getUltrasonicArray2(getMovementController().getPosition().getLocation())[i]-10 > lastValueSensor2){
				sensorBool2 = false;
			}
			//data is stored in arrays
			sumSensor1 += sensor.getUltrasonicArray1(getMovementController().getPosition().getLocation())[i];
			sumSensor2 += sensor.getUltrasonicArray2(getMovementController().getPosition().getLocation())[i];
			
			lastValueSensor2 = sensor.getUltrasonicArray2(getMovementController().getPosition().getLocation())[i];
			lastValueSensor1 = sensor.getUltrasonicArray1(getMovementController().getPosition().getLocation())[i];
		}
		sumSensor1 = sumSensor1/5; //average calculation
		sumSensor2 = sumSensor2/5;
		//if the sensors are "enabled" we put the sum of values into result variable
		if(sensorBool1 && sensorBool2){
			result += sumSensor1;
			result += sumSensor2;
			result /=2;
		}else if(!sensorBool1 && sensorBool2){
			result += sumSensor2;
		}else if(sensorBool1 && !sensorBool2){
			result += sumSensor1;
		}else if(!sensorBool1 && !sensorBool2){
			throw new NoSensorInputException("No reliable data");
		}
		return result;
	}
	//implementation of moveBackward method
	@Override
	public void moveBackward() throws WrongInputException {
		
		if(!movementController.getPosition().isParked()){	//if the car is not parked at a certain position
			
		
		if(movementController.getPosition().getLocation() <=500 && movementController.getPosition().getLocation()> 0){	//boundary check
			
				movementController.getPosition().setLocation(movementController.getPosition().getLocation()-1);	//it sets the location to N-1
			} 
		
		}
	
	}
	//park implementation method
	@Override
	public void park() throws NoSensorInputException, WrongInputException {
		while(movementController.getPosition().getLocation()<500){		//if the car detects less than 5 parking spaces
		//during the track position
			moveForward(); //it moves forward500
		}
		
		ParkingSpot[] parkingSpots = new ParkingSpot[]{new ParkingSpot(0,0)};
		int tmpSize=0;
		int parkingSpotsPosition = 0;
		for(int pos : movementController.getPosition().getCounter()){
			if(pos==0){
				if(tmpSize>=3){
					parkingSpots[parkingSpotsPosition] = new ParkingSpot(tmpSize,pos-1);
					parkingSpotsPosition++;
				}
				tmpSize = 0;
			}else{
				tmpSize++;
			}
		}
		
		int max = 0;
		ParkingSpot bestSpot = new ParkingSpot(0,0);
		
		for(ParkingSpot spot : parkingSpots){
			if(spot.getSize()<max || max == 0){
				if(spot.getLocation()>bestSpot.getLocation()){
					bestSpot = spot;
				}
			}
		}
		
		movementController.setBestSpot(bestSpot);
		
		
		movementController.getPosition().setParked(true);
		
		
		ParkingSpot bestSpotPark = movementController.getBestSpot();
		// To-do
		// Advanced parking maneuver in the parking spot bestSpotPark
		
	}
	
	//unPark implementation - it sets the parked method to false and gets the position.
	//used for the unPark test method.
	@Override
	public void unPark() {
		
		//To-do
		// Advanced unParking maneuver.
		movementController.getPosition().setParked(false);
	}
	// whereIs method implementation - it returns the position of the car
	//it uses the class Position
	@Override
	public Position whereIs() {
		return movementController.getPosition();
	}
	public UltrasonicSensor getSensor() {
		return sensor;
	}
	public void setSensor(UltrasonicSensor sensor) {
		this.sensor = sensor;
	}

	public MovementController getMovementController() {
		return movementController;
	}
	public void setMovementController(MovementController movementController) {
		this.movementController = movementController;
	}

	
}
