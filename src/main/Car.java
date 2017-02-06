package main;

public class Car implements carInterface {


	private int ultrasonicSensor1[];
	private int ultrasonicSensor2[];
	private int lastValueSensor1;
	private int lastValueSensor2;
	
	private Position position;

	public Car(int location, boolean parked, int[] ultrasonicSensor1, int[] ultrasonicSensor2) throws WrongInputException{
		setUltrasonicSensor1(ultrasonicSensor1);
		setUltrasonicSensor2(ultrasonicSensor2);
		position = new Position(location,0,parked);
		
	}

	public void setPosition(int pos, int counter) throws WrongInputException {
		position.setLocation(pos);
		position.setCounter(counter);
		
	}
	
	public Position getPosition() {
		return position;
	}

	public int[] getUltrasonicSensor1() {
		return ultrasonicSensor1;
	}

	public void setUltrasonicSensor1(int[] ultrasonicSensor1) {
		this.ultrasonicSensor1 = ultrasonicSensor1;
	}

	public int[] getUltrasonicSensor2() {
		return ultrasonicSensor2;
	}

	public void setUltrasonicSensor2(int[] ultrasonicSensor2) {
		this.ultrasonicSensor2 = ultrasonicSensor2;
	}

	
	
	
	
	@Override
	public Position moveForward() {
		
		if(!getPosition().isParked()){
		
		if(position.getLocation() < 500 && position.getLocation()>= 0){
			try {
				position.setLocation(position.getLocation()+1);
			} catch (WrongInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(isEmpty()>100){
					position.setCounter(position.getCounter()+1);
				}else{
					position.setCounter(0);
				}
			} catch (WrongInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		}
		return getPosition();
	}

	@Override
	public int isEmpty() throws WrongInputException {
		int sumSensor1 = 0;
		int sumSensor2 = 0;
		int result = 0;
		boolean sensor1=true;
		boolean sensor2=true;
		lastValueSensor1 = 0;
		lastValueSensor2 = 0;
		for(int i = 0;i<ultrasonicSensor1.length;i++){
			if(ultrasonicSensor1[i] <0 || ultrasonicSensor1[i]>200){
				throw new WrongInputException("Wrong input");	
		}
		
		}
		
		for(int i = 0;i<ultrasonicSensor1.length;i++){
			if(ultrasonicSensor2[i] <0 || ultrasonicSensor2[i]>200){
				throw new WrongInputException("Wrong input");	
		}
		
		}
		
		for(int i = 0;i<5;i++){
			if(ultrasonicSensor1[i] > lastValueSensor1+10 || ultrasonicSensor1[i] < lastValueSensor1-10){
				sensor1 = false;	
			}
			
			if(ultrasonicSensor2[i] > lastValueSensor2+10 || ultrasonicSensor2[i] < lastValueSensor2-10){
				sensor2 = false;
			}
			sumSensor1 += ultrasonicSensor1[i];
			sumSensor2 += ultrasonicSensor2[i];
		}
		sumSensor1 = sumSensor1/5;
		sumSensor2 = sumSensor2/5;
		
		if(sensor1 && sensor2){
			result += sumSensor1;
			result += sumSensor2;
			result /=2;
		}else if(!sensor1 && sensor2){
			result += sumSensor2;
		}else if(sensor1 && !sensor2){
			result += sumSensor1;
		}

		
		return result;
	}

	@Override
	public void moveBackward() {
		
		if(!getPosition().isParked()){
			
		
		if(position.getLocation() <=500 && position.getLocation()> 0){
			try {
				position.setLocation(position.getLocation()-1);
			} catch (WrongInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	
	}

	@Override
	public void park() {
		while(getPosition().getCounter()<5){
			if(getPosition().getLocation()<500){
				moveForward();
			}else{
				getPosition().setParked(false);
				break;
			}
		}
		
		// To-do
		// Advanced parking maneuver
		
		getPosition().setParked(true);
	}

	@Override
	public void unPark() {
		
		
		//To-do
		// Advanced unParking maneuver.
		getPosition().setParked(false);
	}

	@Override
	public Position whereIs() {
		return getPosition();
	}

	
}
