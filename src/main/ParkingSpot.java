package main;

public class ParkingSpot {
	
	private int location;
	private int size;

	public ParkingSpot(int location, int size){
		this.setLocation(location);
		this.setSize(size);
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
