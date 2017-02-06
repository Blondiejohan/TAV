package main;

public class Position {

	private int location;
	private boolean parked;

	public Position(int roadLocation, boolean parked){
		setLocation(roadLocation);
		setParked(parked);
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public boolean isParked() {
		return parked;
	}

	public void setParked(boolean parked) {
		this.parked = parked;
	}
}
