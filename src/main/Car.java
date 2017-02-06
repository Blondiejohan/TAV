package main;

public class Car implements carInterface {


	private int ultrasonicSensor1;
	private int ultrasonicSensor2;
	private boolean parked;
	
	public Position position;

	public Car(int location, boolean parked, int ultrasonicSensor1, int ultrasonicSensor2) throws WrongInputException{
		setUltrasonicSensor1(ultrasonicSensor1);
		setUltrasonicSensor2(ultrasonicSensor2);
		setParked(parked);
		position = new Position(location,0);
		
	}

	public void setPosition(int pos, int counter) {
		position.setLocation(pos);
		position.setCounter(counter);
		
	}
	
	public Position getPosition() {
		return position;
	}

	

	public void setLocation(int location) throws WrongInputException {
		if(location<0 || location>500){
			throw new WrongInputException("Input is wrong");
		}else{
			position.setLocation(location);
		}
		
	}

	public boolean getParked() {
		return this.parked;
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
	public Position moveForward() {
		
		if(position.getLocation() < 500 && position.getLocation()>= 0){
			position.setLocation(position.getLocation()+1);
			position.setCounter(position.getCounter()+1);
		}
		return getPosition();
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
		while(getPosition().getCounter()<5){
			if(getPosition().getLocation()<500){
				moveForward();	
			}else{
				setParked(false);
				return false;
				
			}
		}
		setParked(true);
		return true;
	}

	@Override
	public boolean unPark() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Position whereIs() {
		return getPosition();
	}

	
}
