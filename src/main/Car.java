package main;


//implementation of the car class

public class Car implements carInterface {

	//position initialization
	private UltrasonicSensor sensor1;
	private UltrasonicSensor sensor2;
	private MovementController movementController;
	private ParkingSpot bestSpot;
	private int[] parkingSpots;
	private int iterate=0;
	//constructor which initialize all needed objects
	//the car object is needed as precondition to most of the test cases
	
	public Car(int location, boolean parked) throws WrongInputException{
		
		// Creates preset standard values for the sensor.
		
		
		setSensor1(new UltrasonicSensor());
		setSensor2(new UltrasonicSensor());
		//position is an object containing an integer location, a counter and a boolean parked
		setMovementController(new MovementController(location,parked));
		setCounter(new int[501]);
		
	}
	

		public void setSensor2(UltrasonicSensor ultrasonicSensor) {
		this.sensor2 = ultrasonicSensor;
		
	}


		public void setSensor1(UltrasonicSensor ultrasonicSensor) {
			this.sensor1 = ultrasonicSensor;
		
	}


	//moveForward implementation
	// used for the implementation of moveForward, moveBackwards
	@Override
	public int moveForward() throws NoSensorInputException, WrongInputException {
		
		//if the car is not parked
		if(!movementController.isParked()){
		
		//move forward
			if(movementController.getLocation() <= 500 && movementController.getLocation()>= 0){
			System.out.println(movementController.getLocation());
			movementController.accelerate();
			int result = isEmpty();
			
			
			if(result>100){ //it means that the spot is empty
				int[] tmp = getCounter();
				
				tmp[iterate] = 1;
				iterate++;
				setCounter(tmp);
				
				

			}else{
				int[] tmp = getCounter();
				tmp[iterate] = 0;
				iterate++;
				setCounter(tmp);
				
				
			}
			
			
			
			
		}//if it detects spacing places it saves them in the counter
		
		}
		
		return getMovementController().getLocation();
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
		int lastValue1 = getSensor1().getUltrasonicArray()[0];
		int lastValue2 = getSensor2().getUltrasonicArray()[0];
		
		//array check
		for(int i = 0;i<getSensor1().getUltrasonicArray().length;i++){
			if(getSensor1().getUltrasonicArray()[i] < 0 || getSensor1().getUltrasonicArray()[i]>200){		//ultrasonic sensor1 out of boundary check
				throw new NoSensorInputException("Bad sensor input");	
			}
		}
		for(int i = 0;i<getSensor2().getUltrasonicArray().length;i++){						//ultrasonic sensor 2 out of boundary check 
			if(getSensor2().getUltrasonicArray()[i] <0 || getSensor2().getUltrasonicArray()[i]>200){
				throw new NoSensorInputException("bad sensor input");
			}
		}
		
		for(int i = 0;i<5;i++){			/*requirements implementation of 5 times filtering through averaging
			data filtering: the sensor is "disabled" if the current value is less/greater 
			than the last value +/- 10 */
			if(getSensor1().getUltrasonicArray()[i]+10 < lastValue1 || getSensor1().getUltrasonicArray()[i]-10 > lastValue1){
				sensorBool1 = false;	
				
			}
			//data filtering for ultrasonic sensor 2
			if(getSensor2().getUltrasonicArray()[i]+10 < lastValue2 || getSensor2().getUltrasonicArray()[i]-10 > lastValue2){
				sensorBool2 = false;	
				
			}
			//data is stored in arrays
			sumSensor1 += getSensor1().getUltrasonicArray()[i];
			sumSensor2 += getSensor2().getUltrasonicArray()[i];
			
			lastValue1 = getSensor1().getUltrasonicArray()[i];
			lastValue2 = getSensor2().getUltrasonicArray()[i];
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
	public UltrasonicSensor getSensor2() {
		return this.sensor2;
		
	}


	public UltrasonicSensor  getSensor1() {
		return this.sensor1;
	}


	//implementation of moveBackward method
	@Override
	public void moveBackward() throws WrongInputException {
		if(!getMovementController().isParked()){	//if the car is not parked at a certain position
			//System.out.println(movementController.getLocation());
		if(movementController.getLocation() <=500 && movementController.getLocation()> 0){	//boundary check
			movementController.reverse();	//it sets the location to N-1
			} 
		}
	}
	//park implementation method
	@Override
	public void park() throws NoSensorInputException, WrongInputException {
		
		// Create a parking spot and checks the array of open spots to find the actual parking spots and then find the optimal parking spot
		// then save it to the variable bestSpot.
		
		ParkingSpot[] parkingSpots = new ParkingSpot[200];
		int tmpSize=0;
		int parkingSpotsPosition = 0;
		int[] tmpArr = getCounter();
		for(int pos : tmpArr){
			
			if(pos==0){
				
				if(tmpSize>=3){
					parkingSpots[parkingSpotsPosition]=new ParkingSpot(tmpSize,parkingSpotsPosition);
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
			if(spot==null){
				
			}else{
				if(spot.getSize()<max || max == 0){
					if(spot.getLocation()>bestSpot.getLocation()){
						bestSpot = spot;
					}
				}
			}
				
			
		}
		
		setBestSpot(bestSpot);
		
		for(int i = 500 ; i > bestSpot.getLocation() ; i--){
			moveBackward();
			
		}		
		
		movementController.setParked(true);
		
		
		
		// To-do
		// Advanced parking maneuver in the parking spot bestSpotPark
		
	}
	
	//unPark implementation - it sets the parked method to false and gets the position.
	//used for the unPark test method.
	@Override
	public void unPark() {
		
		//To-do
		// Advanced unParking maneuver.
		movementController.setParked(false);
	}
	// whereIs method implementation - it returns the position of the car
	//it uses the class Position
	@Override
	public int whereIs() {
		return movementController.getLocation();
	}
	
	public MovementController getMovementController() {
		return movementController;
	}
	public void setMovementController(MovementController movementController) {
		this.movementController = movementController;
	}


	public ParkingSpot getBestSpot() {
		return bestSpot;
	}

	public void setBestSpot(ParkingSpot bestSpot) {
		this.bestSpot = bestSpot;
	}

	
	public int[] getCounter() {
		
		return this.parkingSpots;
	}

	public void setCounter(int[] i) {
		this.parkingSpots=i;
	}
	
	
	
}
