/*
File Name: Discussion6.java
Author: Michael Portiera
Date: 25 Jun 2019
Purpose: Demonstrate the difference between recursive and iterative algorithms
*/

public class Discussion6 {
    public static class ComplexNumber {
        private double realPart;
        private double imaginaryPart;
        public ComplexNumber(double re, double im) {
            realPart = re;
            imaginaryPart = im;
        }
        //function to multiply two complex numbers
        public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
            double realPart = (a.realPart * b.realPart) - (a.imaginaryPart * b.imaginaryPart);
            double imaginaryPart = (a.realPart * b.imaginaryPart) + (a.imaginaryPart * b.realPart);
            return new ComplexNumber(realPart, imaginaryPart);
        }
        public double getRealPart() {
            return realPart;
        }
        public double getImaginaryPart() {
            return imaginaryPart;
        }
        @Override
        public String toString() {
            return realPart + " + " + imaginaryPart + "i"; //display the complex number in a readable format
        }
    }
    public static ComplexNumber iterativePower(ComplexNumber z, int p) {
        ComplexNumber w = new ComplexNumber(1, 0); // 1 as a complex number
        //multiply z by itself p times
        for (int i = 0; i < p; i++) {
            w = ComplexNumber.multiply(w, z);
        }
        //return the final product
        return w;
    }
    public static ComplexNumber recursivePower(ComplexNumber z, int p) {
        if (p == 0) return new ComplexNumber(1, 0); //check for the first base case
        if (p == 1) return z; //check fo the second base case
        return ComplexNumber.multiply(z, recursivePower(z, p-1)); //make a recursive call
    }
    public static void main(String[] args) {
        ComplexNumber z = new ComplexNumber(-0.70710678118, 0.70710678118); //create the complex number
        ComplexNumber w = null; //will be used to store the result of each function
        long startTime, endTime; //will be used to time each function
        
        //make sure all classes are loaded before timing each version
        iterativePower(z, 1);
        recursivePower(z, 1);
        System.nanoTime();
        System.out.println("");
        
        //print what function will be run
        System.out.println("z to the 5000th power (iterative):");
        startTime = System.nanoTime(); //start a timer
        w = iterativePower(z, 5000); //calculate z to the 5000th power
        endTime = System.nanoTime(); //end timer
        long iterativeTime = endTime - startTime; //calculate the time taken
        System.out.println(w); //print the result
        System.out.println("calculation took " + iterativeTime + "ns"); //print the time taken
        
        System.out.println("");
        
        //print what function will be run
        System.out.println("z to the 5000th power (recursive):");
        startTime = System.nanoTime(); //start a timer
        w = recursivePower(z, 5000); //calculate z to the 5000th power
        endTime = System.nanoTime(); //end timer
        long recursiveTime = endTime - startTime; //calculate the time taken
        System.out.println(w); //print the result
        System.out.println("calculation took " + recursiveTime + "ns"); //print the time taken
        
        System.out.println("");
        
        //calculate how much longer the recusive function took (2 decimal places)
        double factor = Math.round(100.0 * recursiveTime / (double) iterativeTime) / 100.0;
        
        //print how much longer the recursive function took
        System.out.println("recursive takes " + factor + " times longer");
    }
}
