package vehicle;

import java.util.ArrayList;
/*
* File: Vehicle.java
* Author: Mia Bae
* Date: May 24, 2019
* Purpose:  Week 2, Super Keyword discussion post.
*/
public class Vehicle {
    static class Car {
        private String make; // make of the car
        private String model; // model of the car
	
				Car(String make, String model) {
            this.make = make;
            this.model = model;
				} 
				
				String getMake() {
            return this.make;
				} // end getter method
				
        String getModel() {
            return this.model;
        } // end getter method
				
				@Override
				public String toString() {
            return "The make of the vehicle is " + make + ", the model is " + model + ".";
				} // end toString() method
		} // end class Car
		
		static class Year extends Car {
        private int Year; // year of the car
	
        Year(String make, String model, int Year) {
            super(make,model);
            this.Year = Year;
				}
				
				int getYear() {
            return this.Year;
				} // end getter method
	
				@Override
				public String toString() {
            return super.toString() + " The vehicle was made in " + Integer.toString(getYear()) + ".";
				} // end toString() method
		} // end subclass Year

		public static void main(String[] args) {
			  ArrayList<Car> garage = new ArrayList<>();
				garage.add(new Car("Toyota", "Camry"));
				garage.add(new Car("Ford", "Focus"));
				garage.add(new Year("Honda", "Accord", 2018));
				garage.add(new Year("Hyundai", "Elantra", 2014));
				
				// Print all cars in garage
				for (Car car : garage)
          System.out.println(car); // the toString() method is called by default
    } // end public static void main
} // end public class Vehicle