/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package writeemails;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * File: WriteEmails.java
 * Author: Dr. Robertson
 * Date: January 1, XXXX
 * Purpose: This program reads
 * emails from a file and then writes
 * the emails to a file.

 */
public class WriteEmails {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         BufferedReader inputStream = null;
          BufferedWriter outputStream = null;

        String fileLine;
        try {
            inputStream = new BufferedReader(new FileReader("EmailAddresses.txt"));
             outputStream = new BufferedWriter(new FileWriter("EmailCopy.txt"));

            System.out.println("Email Addresses:");
            // Read one Line using BufferedReader
            while ((fileLine = inputStream.readLine()) != null) {
                 outputStream.write(fileLine + "\r\n");
            }
        } catch (IOException io) {
            System.out.println("File IO exception" + io.getMessage());
        }finally {
            // Need another catch for closing 
            // the streams          
            try {               
                if (inputStream != null) {
                inputStream.close();
                if (outputStream != null) {
                    outputStream.close();
                }
            }                
            } catch (IOException io) {
                System.out.println("Issue closing the Files" + io.getMessage());
            }
        
        }
    }

}
