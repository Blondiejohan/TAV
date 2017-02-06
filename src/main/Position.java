package main;

public class Position {

	private int location;
	private int spaceCounter;

	public Position(int roadLocation, int spaceCounter) throws WrongInputException{
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
}
