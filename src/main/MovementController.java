package main;

public class MovementController implements MovementControllerInterface {

	private boolean parked;
	
	public MovementController(boolean park) throws WrongInputException {
		
		this.parked=park;
	}

	@Override
	public boolean accelerate(int location) throws WrongInputException {
		//if the car is within the ranges
		if(location < 500 && location>= 0){
		//move car
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean reverse(int location) throws WrongInputException {
		if(location < 500 && location>= 0){
			//move car
				return true;
			}else{
				return false;
			}
	}


	

	public boolean isParked() {
		return this.parked;
	}

	public void setParked(boolean parked) {
		this.parked = parked;
	}

	

}
