package main;

public class Car implements carInterface {


	private int ultrasonicSensor1;
	private int ultrasonicSensor2;
	private int spaceCounter;
	private Position position;

	public Car(int location, boolean parked, int ultrasonicSensor1, int ultrasonicSensor2) throws WrongInputException{
		setUltrasonicSensor1(ultrasonicSensor1);
		setUltrasonicSensor2(ultrasonicSensor2);
		setSpaceCounter(0);
		position = new Position(location,parked);
		
	}

	public void setPosition(int pos, boolean park) {
		position.setLocation(pos);
		position.setParked(park);
		
	}
	
	public Position getPosition() {
		return position;
	}

	public int getLocation() {
		return position.getLocation();
	}

	public void setLocation(int location) throws WrongInputException {
		if(location<0 || location>500){
			throw new WrongInputException("Input is wrong");
		}else{
			position.setLocation(location);
		}
		
	}

	public boolean isParked() {
		return position.isParked();
	}

	public void setParked(boolean parked) {
		position.setParked(parked);
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
	public Position moveForward() {
		
		if(position.getLocation() < 500 && position.getLocation()>= 0){
			position.setLocation(position.getLocation()+1);
		}
		return this.position;
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
		if(position.getLocation() <=500 && position.getLocation()> 0){
			position.setLocation(position.getLocation()-1);
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
