package main;

//implementation of the car Interface class

public class Car implements carInterface {

	//ultrasonic sensor arrays where distance is stored
	private int ultrasonicSensor1[];
	private int ultrasonicSensor2[];
	
	//position initialization
	private Position position;

	//constructor which initialize all needed objects
	
	public Car(int location, boolean parked, int[] ultrasonicSensor1, int[] ultrasonicSensor2) throws WrongInputException{
		setUltrasonicSensor1(ultrasonicSensor1);
		setUltrasonicSensor2(ultrasonicSensor2);
		position = new Position(location,0,parked);
		
	}
	//position setter 
	public void setPosition(int pos, int counter) throws WrongInputException {
		position.setLocation(pos);
		position.setCounter(counter);
		
	}
	//position getter
	public Position getPosition() {
		return position;
	}
	//ultrasonic sensor1 getter
	public int[] getUltrasonicSensor1() {
		return ultrasonicSensor1;
	}
	//ultrasonic sensor1 setter
	public void setUltrasonicSensor1(int[] ultrasonicSensor1) {
		this.ultrasonicSensor1 = ultrasonicSensor1;
	}
	//ultrasonic sensor2 getter
	public int[] getUltrasonicSensor2() {
		return ultrasonicSensor2;
	}
	//ultrasonic sensor2 setter
	public void setUltrasonicSensor2(int[] ultrasonicSensor2) {
		this.ultrasonicSensor2 = ultrasonicSensor2;
	}

		//moveForward implementation
	@Override
	public Position moveForward() throws NoSensorInputException, WrongInputException {
		
		if(!getPosition().isParked()){
		
		if(position.getLocation() < 500 && position.getLocation()>= 0){
		
				position.setLocation(position.getLocation()+1);
		}
		if(isEmpty()>100){
					position.setCounter(position.getCounter()+1);
				}else{
					position.setCounter(0);
		}
		}
		return getPosition();
	}
	//isEmpty implementation method
	@Override
	public int isEmpty() throws WrongInputException, NoSensorInputException {
		int sumSensor1 = 0;		//sensors declaration
		int sumSensor2 = 0;
		int result = 0;
		boolean sensor1=true;	// sensors are active
		boolean sensor2=true;
		int lastValueSensor1= ultrasonicSensor1[0];		//sensor arrays are initialized with the last value at position 1
		int lastValueSensor2 = ultrasonicSensor2[0];
		
		//array check
		for(int i = 0;i<ultrasonicSensor1.length;i++){
			if(ultrasonicSensor1[i] <0 || ultrasonicSensor1[i]>200){		//ultrasonic sensor1 out of boundary check
				throw new NoSensorInputException("Bad sensor input");	
			}
		}
		for(int i = 0;i<ultrasonicSensor1.length;i++){						//ultrasonic sensor 2 out of boundary check 
			if(ultrasonicSensor2[i] <0 || ultrasonicSensor2[i]>200){
				throw new NoSensorInputException("bad sensor input");
			}
		}
		
		for(int i = 0;i<5;i++){			/*requirements implementation of 5 times filtering through averaging
			data filtering: the sensor is "disabled" if the current value is less/greater 
			than the last value +/- 10 */
			if(ultrasonicSensor1[i]+10 < lastValueSensor1 || ultrasonicSensor1[i]-10 > lastValueSensor1){
				sensor1 = false;	
			}
			//data filtering for ultrasonic sensor 2
			if(ultrasonicSensor2[i]+10 < lastValueSensor2 || ultrasonicSensor2[i]-10 > lastValueSensor2){
				sensor2 = false;
			}
			//data is stored in arrays
			sumSensor1 += ultrasonicSensor1[i];
			sumSensor2 += ultrasonicSensor2[i];
			
			lastValueSensor2 = ultrasonicSensor2[i];
			lastValueSensor1 = ultrasonicSensor1[i];
		}
		sumSensor1 = sumSensor1/5; //average calculation
		sumSensor2 = sumSensor2/5;
		//if the sensors are "enabled" we put the sum of values into result variable
		if(sensor1 && sensor2){
			result += sumSensor1;
			result += sumSensor2;
			result /=2;
		}else if(!sensor1 && sensor2){
			result += sumSensor2;
		}else if(sensor1 && !sensor2){
			result += sumSensor1;
		}else if(!sensor1 && !sensor2){
			throw new NoSensorInputException("No reliable data");
		}

		return result;
	}
	//implementation of moveBackward method
	@Override
	public void moveBackward() {
		
		if(!getPosition().isParked()){	//if the car is not parked at a certain position
			
		
		if(position.getLocation() <=500 && position.getLocation()> 0){	//boundary check
			try {
				position.setLocation(position.getLocation()-1);	//it sets the location to N-1
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
		while(getPosition().getCounter()<5){		//if the car detects less than 5 parking spaces
			if(getPosition().getLocation()<500){	//during the track position
				moveForward();						//it moves forward
			}else{
				getPosition().setParked(false);		//spaces<5 therefore the car cannot park
				break;
			}
		}
		
		// To-do
		// Advanced parking maneuver
		
		getPosition().setParked(true);			//outside the while loop means the car has found
	}											//a suitable place to park --> it parks;
	
	//unPark implementation - it sets the parked method to false and gets the position.
	@Override
	public void unPark() {
		
		//To-do
		// Advanced unParking maneuver.
		getPosition().setParked(false);
	}
	// whereIs method implementation - it returns the position of the car
	@Override
	public Position whereIs() {
		return getPosition();
	}

	
}
