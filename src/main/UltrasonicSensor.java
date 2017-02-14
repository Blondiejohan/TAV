package main;

public class UltrasonicSensor implements UltrasonicSensorInterface{
	//ultrasonic sensor array where distance is stored
	private int[] ultrasonicArray1;

	//ultrasonic sensor array where distance is stored
		private int[] ultrasonicArray2;

		int[][] mockArr1=null;
		int[][] mockArr2=null;

		public UltrasonicSensor (int[] inputArray1, int[] inputArray2){
			setUltrasonicArray1(inputArray1);
			setUltrasonicArray2(inputArray2);
			
		}
		
		
		//ultrasonic sensor2 getter
		public int[] getUltrasonicArray2(int pos) {
			if(mockArr2[pos]==null){
				return new int[]{0,0,0,0,0};
			}
			return mockArr2[pos];
		}

		//ultrasonic sensor2 setter
		public void setUltrasonicArray2(int[] ultrasonicArray2) {
			this.ultrasonicArray2 = ultrasonicArray2;
		}
		
		public int[][] mockarrar(){
			
			for(int i = 0 ; i<500 ; i++){
				
				mockArr1[0] = new int[]{20,20,20,20,20};
				mockArr2[0] = new int[]{20,20,20,20,20};
				
				mockArr1[1] = new int[]{120,120,120,120,120};
				mockArr2[1] = new int[]{120,120,120,120,120};
				
				mockArr1[2] = new int[]{120,120,120,120,120};
				mockArr2[2] = new int[]{120,120,120,120,120};
				
				mockArr1[3] = new int[]{120,120,120,120,120};
				mockArr2[3] = new int[]{120,120,120,120,120};
				
				mockArr1[4] = new int[]{20,20,20,20,20};
				mockArr2[4] = new int[]{20,20,20,20,20};
				
				mockArr1[5] = new int[]{20,20,20,20,20};
				mockArr2[5] = new int[]{20,20,20,20,20};
			}
			return null;
		}
	//ultrasonic sensor1 getter
		//
	public int[] getUltrasonicArray1(int pos) {
		if(mockArr1[pos]==null){
			return new int[]{0,0,0,0,0};
		}
		return mockArr1[pos];
	}
	//ultrasonic sensor1 setter
		//
	public void setUltrasonicArray1(int[] ultrasonicArray1) {
		this.ultrasonicArray1 = ultrasonicArray1;
	}

}
