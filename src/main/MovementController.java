package main;

public class MovementController implements MovementControllerInterface {

	private int location;
	private boolean parked;
	
	public MovementController(int loc, boolean park) throws WrongInputException {
		this.location=loc;
		this.parked=park;
	}

	@Override
	public void accelerate() throws WrongInputException {
		//if the car is within the ranges
				
		setLocation(getLocation()+1);
	}

	@Override
	public void reverse() throws WrongInputException {
		setLocation(getLocation()-1);
		
	}


	public int getLocation() {
		return this.location;
	}
	//check boundaries
	public void setLocation(int location) throws WrongInputException {
		//if we are not within the track limits
		if(location < 0 || location > 500){
			throw new WrongInputException("Input is wrong");
		}else{
			this.location=location;
		}
	}

	

	public boolean isParked() {
		return this.parked;
	}

	public void setParked(boolean parked) {
		this.parked = parked;
	}

}
