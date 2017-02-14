package main;

public class UltrasonicSensor1 implements UltrasonicSensorInterface{

	private int[] ultrasonicArray1;
	//ultrasonic sensor array where distance is stored

	public UltrasonicSensor1 (int[] inputArray1){
		this.setUltrasonicArray1(inputArray1);
	}

	//ultrasonic sensor1 getter
		//
	public int[] getUltrasonicArray1() {
		return ultrasonicArray1;
	}
	//ultrasonic sensor1 setter
		//
	public void setUltrasonicArray1(int[] ultrasonicArray1) {
		this.ultrasonicArray1 = ultrasonicArray1;
	}

}
