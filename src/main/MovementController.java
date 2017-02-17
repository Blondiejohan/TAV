package main;

public class MovementController implements MovementControllerInterface {

	private Position position;
	private ParkingSpot bestSpot;


	
	public MovementController(int location, boolean parked) throws WrongInputException {
		setPosition(new Position(location,parked));
		setBestSpot(new ParkingSpot(0,0));
	}

	@Override
	public void accelerate() throws WrongInputException {
		//if the car is within the ranges
				
		position.setLocation(position.getLocation()+1);
		
	}

	@Override
	public void reverse() throws WrongInputException {
		position.setLocation(position.getLocation()-1);
		
	}

	//position getter
	public Position getPosition() {
		return position;
	}
	//position setter 
	public void setPosition(Position position) {
		this.position = position;
	}

	public ParkingSpot getBestSpot() {
		return bestSpot;
	}

	public void setBestSpot(ParkingSpot bestSpot) {
		this.bestSpot = bestSpot;
	}

}
