public class SuperSub {
	static class Storage {
		private String name;
		private int capacity;
	
		Storage(String name, int capacity) {
			this.name = name;
			this.capacity = capacity;
		} // end Storage(String name, int capacity)
	
		String getName() {
			return this.name;
		} // end String getName()
	
		int getCapacity() {
			return this.capacity;
		} // end int getCapacity()
	
		public String toString() {
			return getName() + " has a capacity of " +
				Integer.toString(getCapacity()) + "GB";
		} // end public String toString()
		
	} // end static class Storage

	static class HardDiskDrive extends Storage {
		private int spinSpeed;
	
		HardDiskDrive(String name, int capacity, int spinSpeed) {
			super(name,capacity);
			this.spinSpeed = spinSpeed;
		} // end HardDiskDrive(String name, int capacity, int spinSpeed)
	
		int getSpinSpeed() {
			return this.spinSpeed;
		} // end int getSpinSpeed()
	
		public String toString() {
			return super.toString() + " and spins at " +
				Integer.toString(getSpinSpeed()) + "RPM";
		} // end public String toString()
	
	} // end static class HardDiskDrive extends Storage

	public static void main(String[] args) {
		Storage f = new Storage("USB",8);
		Storage c = new HardDiskDrive("HDD",3000,7200);
		System.out.println(f.toString());
		System.out.println(c.toString());
	} // end public static void main(String[] args)
	
} // end public class SuperSub