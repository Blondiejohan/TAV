package main;

public class Position {

	private int location;
	private int spaceCounter;

	public Position(int roadLocation, int spaceCounter){
		setLocation(roadLocation);
		setCounter(spaceCounter);
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getCounter() {
		return this.spaceCounter;
	}

	public void setCounter(int spaceCounter) {
		this.spaceCounter = spaceCounter;
	}
}
