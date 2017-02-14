package main;
//this class holds all details of the position object
//it serves all the tests where position is needed: whereIs
public class Position {

	private int location;
	private int[] spaceCounter;
	private boolean parked;
	
//Position object constructor.
	public Position(int roadLocation, int i, boolean parked) throws WrongInputException{
		setLocation(roadLocation);
		spaceCounter = new int[]{0};
		
	}

	public int getLocation() {
		return location;
	}
	//check boundaries
	public void setLocation(int location) throws WrongInputException {
		//if we are not within the track limits
		if(location<0 || location>500){
			throw new WrongInputException("Input is wrong");
		}else{
			this.location=location;
		}
	}

	public int[] getCounter() {
		return this.spaceCounter;
	}

	public void setCounter(int pos, int j) {
		this.spaceCounter[pos] = j;
	}

	public boolean isParked() {
		return parked;
	}

	public void setParked(boolean parked) {
		this.parked = parked;
	}
}
