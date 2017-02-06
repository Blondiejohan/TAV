package main;

public class Car implements carInterface {

	private int location;
	private boolean parked;
	private int ultrasonicSensor1;
	private int ultrasonicSensor2;
	private int spaceCounter;

	public Car(int location, boolean parked, int ultrasonicSensor1, int ultrasonicSensor2) throws WrongInputException{
		setLocation(location);
		setParked(parked);
		setUltrasonicSensor1(ultrasonicSensor1);
		setUltrasonicSensor2(ultrasonicSensor2);
		setSpaceCounter(0);
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) throws WrongInputException {
		if(location<0 || location>500){
			throw new WrongInputException("Input is wrong");
		}else{
			this.location = location;
		}
		
	}

	public boolean isParked() {
		return parked;
	}

	public void setParked(boolean parked) {
		this.parked = parked;
	}

	public int getUltrasonicSensor1() {
		return ultrasonicSensor1;
	}

	public void setUltrasonicSensor1(int ultrasonicSensor1) {
		this.ultrasonicSensor1 = ultrasonicSensor1;
	}

	public int getUltrasonicSensor2() {
		return ultrasonicSensor2;
	}

	public void setUltrasonicSensor2(int ultrasonicSensor2) {
		this.ultrasonicSensor2 = ultrasonicSensor2;
	}

	@Override
	public void moveForward() {
		
		if(location < 500 && location>= 0){
			location++;
		}
	}

	@Override
	public int isEmpty() throws WrongInputException {
		int sum = 0;
		if(ultrasonicSensor1 <0 || ultrasonicSensor1>200 || ultrasonicSensor2 <0 || ultrasonicSensor2>200){
			throw new WrongInputException("Wrong input");
		}
		for(int i = 0;i<=5;i++){
			sum = sum+ultrasonicSensor1;
			sum = sum+ultrasonicSensor2;
		}
		sum = sum/10;
		return sum;
	}

	@Override
	public void moveBackward() {
		if(location <=500 && location> 0){
			location--;
		}
	
	}

	@Override
	public boolean park() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unPark() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Position whereIs() {
		return null;
		// TODO Auto-generated method stub
	
	}

	public int getSpaceCounter() {
		return spaceCounter;
	}

	public void setSpaceCounter(int spaceCounter) {
		this.spaceCounter = spaceCounter;
	}

	
}
