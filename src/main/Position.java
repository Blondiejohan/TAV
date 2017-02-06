package main;

public class Position {

	private int location;
	private int spaceCounter;
	private boolean parked;
	

	public Position(int roadLocation, int spaceCounter, boolean parked) throws WrongInputException{
		setLocation(roadLocation);
		setCounter(spaceCounter);
		
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) throws WrongInputException {
		if(location<0 || location>500){
			throw new WrongInputException("Input is wrong");
		}else{
			this.location=location;
		}
	}

	public int getCounter() {
		return this.spaceCounter;
	}

	public void setCounter(int spaceCounter) {
		this.spaceCounter = spaceCounter;
	}

	public boolean isParked() {
		return parked;
	}

	public void setParked(boolean parked) {
		this.parked = parked;
	}
}
