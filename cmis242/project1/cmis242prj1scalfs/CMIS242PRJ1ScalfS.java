package cmis242prj1scalfs;

import java.io.*;
import java.util.*;

/**
 * This program outputs a table of employee information with annual 
 * salaries appended to the given information.
 * 
 * @author Samuel Scalf
 * @version 1.3
 * @since 2019-05-25
 */
public class CMIS242PRJ1ScalfS {
  
  /*
   * NESTED STATIC CLASSES
  */
  
  static class Employee {
    private String name;
    private int monthlySalary;
    private String jobTitle;
    
    Employee(String name, int monthlySalary) {
      this(name,monthlySalary,"Employee");
    } // end constructor Employee(name,monthlySalary)
    
    Employee(String name, int monthlySalary, String jobTitle) {
      this.name = name;
      this.monthlySalary = monthlySalary;
      this.jobTitle = jobTitle;
    }  // end constructor Employee(name,monthlySalary,jobTitle)
    
    String getName() {
      return this.name;
    } // end String getName()
    
    double getMonthlySalary() {
      return (double)(this.monthlySalary);
    } // end int getMonthlySalary()
    
    String getJobTitle() {
      return this.jobTitle;
    } // end String getJobTitle()
    
    // Calculates and returns annualSalary as int
    double getAnnualSalary() {
      return (double)(this.monthlySalary * 12);
    } // end int getAnnualSalary()
    
    @Override
    public String toString() {
      return String.format("%-19.17s%-13.11s$%-13.2f%36s$%9.2f",
        this.getName(), this.getJobTitle(), this.getMonthlySalary(), "", 
        this.getAnnualSalary());
    } // end public String toString()
    
  } // end static class Employee
  
  static class Salesman extends Employee {
    private int sales;
    
    Salesman(String name,int monthlySalary,int sales) {
      super(name,monthlySalary,"Salesman");
      this.sales = sales;
    } // end constructor Salesman(name,monthlySalary,sales)
    
    double getSales() {
      return (double)(this.sales);
    }
    
    double getBonus() {
      double commission = this.getSales() * 0.2;
      return (commission > 20000) ? 20000 : commission;
    } // end double getCommission()
    
    @Override
    double getAnnualSalary() {
      return super.getAnnualSalary() + this.getBonus();
    } // end double getAnnualSalary()
    
    @Override
    public String toString() {
      return String.format("%-19.16s%-13.11s$%-13.2f$%-11.2f%-11s$%-12.2f$%9.2f",
        this.getName(), this.getJobTitle(), this.getMonthlySalary(),
        this.getSales(), "", this.getBonus(), this.getAnnualSalary());
    } // end public String toString()
    
  } // end static class Salesman extends Employee
  
  static class Executive extends Employee {
    private int stock;
    
    Executive(String name, int monthlySalary, int stock) {
      super(name,monthlySalary,"Executive");
      this.stock = stock;
    } // end constructor Executive(name,monthlySalary,stock)
		
		double getStock() {
			return (double)(this.stock);
		} // end int getStock()
		
		double getBonus() {
      return (this.stock > 50) ? 30000.0 : 0.0;
    } // end double getCommission()
    
    @Override
    double getAnnualSalary() {
      return super.getAnnualSalary() + this.getBonus();
    } // end double getAnnualSalary()
    
    @Override
    public String toString() {
      return String.format("%-19.16s%-13.11s$%-13.2f%-12s$%-10.2f$%-12.2f$%9.2f",
        this.getName(), this.getJobTitle(), this.getMonthlySalary(),
        "", this.getStock(), this.getBonus(), this.getAnnualSalary());
    } // end public String toString()
		
  } // end static class Executive extends Employee
  
  static class FiscalTable {
		private final String year;
		private final ArrayList<Employee> employees;
		
		FiscalTable (String year, String sourceFile) {
			this.year = year;
			this.employees =
				new ArrayList<>(createEmployees(fillArray(year,sourceFile)));
		} // end FiscalTable (String year, String sourceFile)
		
		double getAverageSalary() {
			double sum = 0.0;
			
			for (Employee employee : this.employees) {
				sum += employee.getAnnualSalary();
			} // end for (Employee employee : employees)
			return sum / this.employees.size();
		} // end void getAverageSalary
	} // end static class FiscalTable
	
	/*
   * STATIC METHODS
  */
  
  // This method reads a csv, filtered by year, into String[] arrays,
  // and returns an ArrayList composed of the String[] arrays.
  static ArrayList<String[]> fillArray(String year, String filename) {
    ArrayList<String[]> tempArray = new ArrayList<>();
    Scanner readFile = null;
    String line;
    
    try {
      readFile = new Scanner(new File(filename));
      while (readFile.hasNextLine()) {
        line = readFile.nextLine();
        String[] values = line.split(",");
        
        // Add String[] if the year matches
        if (values[0].equals(year)) {
          tempArray.add(values);
        } // end if (values.equals(year))
        
      } // end while (readFile.hasNextLine())
      
    } catch (IOException io) {
      System.err.println("IO Exception: " + io.getMessage());
    } finally {
      if (readFile != null) {
        readFile.close();
      }
    } // end try-catch-finally
    
    return tempArray;
  } // end static String[][] fillArray(int year)
    
  // This method creates and returns an ArrayList of Employee objects
  // from an supplied String[] array.
  static ArrayList<Employee> createEmployees(ArrayList<String[]> employeeArray) {
    ArrayList<Employee> outputList = new ArrayList<>();
    String year, type, name;
    int monthly, special;
      
    // iterate through all entries in employeeArray
    for (String[] employee : employeeArray) {
      // set up fields for Employee creation
      //year = employee[0];
      type = employee[1];
      name = employee[3] + " " + employee[2];
      monthly = Integer.parseInt(employee[4]);
      special = Integer.parseInt(employee[5]);
        
      // create Employees based on jobTitle and add to ArrayList
      switch (type) {
        case "Salesman":
          outputList.add(new Salesman(name,monthly,special));
          break;
        case "Executive":
          outputList.add(new Executive(name,monthly,special));
          break;
        default:
          outputList.add(new Employee(name,monthly));
      } // end switch (type)
    } // end for (String[] employee : employeeArray)
      
    return outputList;
  } // end static ArrayList<Employee> createEmployees(String[][] employeeArray)
    
  // Creates and returns a horizontal "line" of a desired length
  static void separator(int length, char ch) {
    char[] chars = new char[length];
    Arrays.fill(chars, ch);
    String s = new String(chars);
    System.out.println(s);
  } // end static String separator(int length)
    
  // Prints company letterhead
  static void printCompany() {
    String company = "Totally Not A Scam, LLC";
    System.out.printf("\n%33s%s\n","",company);
    System.out.printf("%32s","");
		separator(company.length() + 2,'-');
		System.out.println("\n");
  } // end static void printCompany()
  
  static void printTable(FiscalTable table) {
    String headers = String.format("%-19.17s%-13.11s%-14.14s%-12s%-11s%-13s%-10.10s",
      "Employee Name","Type","Monthly Pay",
      "Sales","Stock","Bonus","Annual Pay");
		int length = headers.length();
		
		printCompany();
    System.out.printf("*** Fiscal Year %s ***\n",table.year);
    separator(length, '-');
    System.out.println(headers);
    separator(length, '-');
    table.employees.forEach(System.out::println);
		separator(length, '-');
		System.out.printf("%66sAverage Salary: $%9.2f\n\n\n","",table.getAverageSalary());
		separator(length, '~');
		System.out.println();
  } // end void printTable()
  
  public static void main(String[] args) {
    String inputCSV = "Employees.csv";
    FiscalTable fy14 = new FiscalTable("2014",inputCSV);
		FiscalTable fy15 = new FiscalTable("2015",inputCSV);
    printTable(fy14);
		printTable(fy15);
  } // end public static void main(String[] args)
  
} // end public class CMIS242PRJ1ScalfS