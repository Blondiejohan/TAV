package main;

public class Ultrasonicsensor2 implements UltrasonicSensorInterface{

	//ultrasonic sensor array where distance is stored
	private int[] ultrasonicArray2;

	public Ultrasonicsensor2 (int[] inputArray2){
		this.setUltrasonicArray2(inputArray2);
	}
	//ultrasonic sensor2 getter
	public int[] getUltrasonicArray2() {
		return ultrasonicArray2;
	}

	//ultrasonic sensor2 setter
	public void setUltrasonicArray2(int[] ultrasonicArray2) {
		this.ultrasonicArray2 = ultrasonicArray2;
	}
}
