package main;

public class UltrasonicSensor implements UltrasonicSensorInterface{
	//ultrasonic sensor array where distance is stored
	private int[] ultrasonicArray;

		public UltrasonicSensor (){
			
			int[] tmpArr3 = {1,1,1,1,1};
			
			setUltrasonicArray(tmpArr3);
			
		}
		
		
		
	//ultrasonic sensor1 getter
		//
	public int[] getUltrasonicArray() {
		
		return ultrasonicArray;
	}
	
	//ultrasonic sensor1 setter
		//
	public void setUltrasonicArray(int[] inputArray1) {
		this.ultrasonicArray = inputArray1;
	}



}
