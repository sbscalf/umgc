package week6recursiveiterative;
/*
* File Name: Week6RecursiveIterative.java
* Author: Mia Bae
* Date: June 24, 2019
* Purpose:  Week 6 - Recursive methods vs. iterative methods
*/
class Week6RecursiveIterative {
    int n = 3;
public static int recursivepowerOf3(int n) {
    if (n == 0) {
        return 1;
    }//end if
   return recursivepowerOf3(n-1)*3;
}//end recursive method

public static int iterativepowerOf3(int n) {
      int product = 0;
      for (int i = 1; i <= n; i ++)
         product = (i*i*i);
      return product;
   }//end iterative method

   public static void main(String args[]) {
      System.out.println("Power of 3 --");
      System.out.println("Recursive: 3 to the power of 3 = " + recursivepowerOf3(3));
      System.out.println("Iterative: 3 to the power of 3 = " + iterativepowerOf3(3));
    }//end public static void main
}//end class Week6RecrusiveIterative