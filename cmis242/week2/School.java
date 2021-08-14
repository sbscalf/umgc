public class School {
  public static void main (String args []) {
    Name n = new Name ();
    Name m = new Name ("Jane", "Doe");
    Student st = new Student ("Harry", "Potter", 38);
    System.out.println ("Student: >" + n + "<");
    System.out.println ("Student: >" + m + "<");
    System.out.println ("Student: >" + st + "<");
    System.out.println ("bye");
  } // end main method
} // end class School

class Name {
  String name = "Fred";
 
  public Name () { }
 
  public Name (String f, String l) {
    name = f + " " + l;
  } // end String String constructor
 
  public String toString () {return name;}
} // end class Name

class Person {
} // end class Person

class Student extends Person {
  Name n = new Name ("a", "b");
  int credits;
 
  public Student (String fn, String ln, int cr) {
    credits = cr;
    n = new Name (fn, ln);
  } // end String,String,int constructor
 
  public String toString () {
    return n + " " + credits;
  } // end method toString
} // end class Student

class Employee extends Person {
} // end class Employee