package main;

public class ParkingSpot {
	
	private int location;
	private int size;

	public ParkingSpot(int location, int size){
		this.setLocation(location);
		this.setSize(size);
	}

	// getting the location
	public int getLocation() {
		return location;
	}
	// setting the location
	public void setLocation(int location) {
		this.location = location;
	}
	// getting the size of the parking spot
	public int getSize() {
		return size;
	}
	// setting the size of the parking spot
	public void setSize(int size) {
		this.size = size;
	}

}
