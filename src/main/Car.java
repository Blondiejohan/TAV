package main;
import org.mockito.*;

//implementation of the car class

public class Car implements carInterface {

	
	
	
	//position initialization
	private UltrasonicSensor1 sensor1;
	private Ultrasonicsensor2 sensor2;
	private MovementController movementController;

	//constructor which initialize all needed objects
	//the car object is needed as precondition to most of the test cases
	
	public Car(int location, boolean parked, int[] ultrasonicSensor1, int[] ultrasonicSensor2) throws WrongInputException{
		
		setSensor1(new UltrasonicSensor1(ultrasonicSensor1));
		setSensor2(new Ultrasonicsensor2(ultrasonicSensor2));
		
		//position is an object containing an integer location, a counter and a boolean parked
		movementController = new MovementController(location,0,parked);
		
	}
	

		//moveForward implementation
	// used for the implementation of moveForward, moveBackwards
	@Override
	public Position moveForward() throws NoSensorInputException, WrongInputException {
		
		int parkingSpot = 0;
		
		//if the car is not parked
		if(!movementController.getPosition().isParked()){
		//if the car is within the ranges
		if(movementController.getPosition().getLocation() < 500 && movementController.getPosition().getLocation()>= 0){
		//move forward
			movementController.accelerate();
			
			if(isEmpty()>100){
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
		int lastValueSensor1= sensor1.getUltrasonicArray1()[0];		//sensor arrays are initialized with the last value at position 1
		int lastValueSensor2 = sensor2.getUltrasonicArray2()[0];	
		
		//array check
		for(int i = 0;i<sensor1.getUltrasonicArray1().length;i++){
			if(sensor1.getUltrasonicArray1()[i] <0 || sensor1.getUltrasonicArray1()[i]>200){		//ultrasonic sensor1 out of boundary check
				throw new NoSensorInputException("Bad sensor input");	
			}
		}
		for(int i = 0;i<sensor2.getUltrasonicArray2().length;i++){						//ultrasonic sensor 2 out of boundary check 
			if(sensor2.getUltrasonicArray2()[i] <0 || sensor2.getUltrasonicArray2()[i]>200){
				throw new NoSensorInputException("bad sensor input");
			}
		}
		
		for(int i = 0;i<5;i++){			/*requirements implementation of 5 times filtering through averaging
			data filtering: the sensor is "disabled" if the current value is less/greater 
			than the last value +/- 10 */
			if(sensor1.getUltrasonicArray1()[i]+10 < lastValueSensor1 || sensor2.getUltrasonicArray2()[i]-10 > lastValueSensor1){
				sensorBool1 = false;	
			}
			//data filtering for ultrasonic sensor 2
			if(sensor2.getUltrasonicArray2()[i]+10 < lastValueSensor2 || sensor2.getUltrasonicArray2()[i]-10 > lastValueSensor2){
				sensorBool2 = false;
			}
			//data is stored in arrays
			sumSensor1 += sensor1.getUltrasonicArray1()[i];
			sumSensor2 += sensor2.getUltrasonicArray2()[i];
			
			lastValueSensor2 = sensor2.getUltrasonicArray2()[i];
			lastValueSensor1 = sensor1.getUltrasonicArray1()[i];
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
	public void moveBackward() {
		
		if(!movementController.getPosition().isParked()){	//if the car is not parked at a certain position
			
		
		if(movementController.getPosition().getLocation() <=500 && movementController.getPosition().getLocation()> 0){	//boundary check
			try {
				movementController.getPosition().setLocation(movementController.getPosition().getLocation()-1);	//it sets the location to N-1
			} catch (WrongInputException e) {			//try & catch method
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	
	}
	//park implementation method
	@Override
	public void park() throws NoSensorInputException, WrongInputException {
		
		while(movementController.getPosition().getLocation()<500){		//if the car detects less than 5 parking spaces
		//during the track position
			moveForward();						//it moves forward
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
			if(spot.getSize()>max){
				bestSpot = spot;
			}
			
		}
		
		movementController.setBestSpot(bestSpot);
		
		
		movementController.getPosition().setParked(true);
		// To-do
		// Advanced parking maneuver using bestPosition.
		
		//outside the while loop means the car has found
	}											//a suitable place to park --> it parks;
	
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
	public UltrasonicSensor1 getSensor1() {
		return sensor1;
	}
	public void setSensor1(UltrasonicSensor1 sensor1) {
		this.sensor1 = sensor1;
	}
	public Ultrasonicsensor2 getSensor2() {
		return sensor2;
	}
	public void setSensor2(Ultrasonicsensor2 sensor2) {
		this.sensor2 = sensor2;
	}
	public MovementController getMovementController() {
		return movementController;
	}
	public void setMovementController(MovementController movementController) {
		this.movementController = movementController;
	}

	
}
