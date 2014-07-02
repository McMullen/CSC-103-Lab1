/**
**************************************************************
*Class Name: Lab1
*Author:     Jason McMullen and Adam Reese
*Due Date:   10/02/13
*
*Program Description: Lab1 contains the main method and has 
*the responsibility of running and stopping this program. 
*Lab1 also finds and reads line by line of the txt file 
*containing the data needed to operate this program.
**************************************************************
*/

//import files needed to read in the txt document
import java.io.*;
import java.util.*;

/**
* Lab1 contains the main method and has the responsibility of
* running and stopping this program. Lab1 also finds and reads
* line by line of the txt file containing the data needed to
* operate this program.
*/

public class Lab1 extends QuadTest
{
   
   /**
   * main creates objects needed to read in lines of text from
   * an associated txt file. It then sends those lines, one at a
   * time, to the Quadratic class where they will be parsed into 
   * useable pieces of data. This method contains an error check
   * to ensure the txt file exists and is in the current directory
   * of this class. After the program has finished running this 
   * program will gracefully end itself with System.exit(0); instead
   * of just terminating.
   */
   
   public static void main(String[] args){
   
      try{
         
         /**
         * fr is an object of the FileReader class
         */
         FileReader fr = new FileReader("inputCoef.txt");
         
         /**
         * fileIn is an object of the BufferedReader class
         */
         BufferedReader fileIn = new BufferedReader(fr);
         
         /**
         * count is keeping record of the amount of times the loop has been ran
         */
         int count = 1;         
         
         //inital read to start the loop
         String line = fileIn.readLine();
         
         //as long as there is more text to read from this loop
         //will continue reading and sending each lind to be parsed
         while(line != null){
            
            QuadTest test = new QuadTest();
            test.quadTestDriver(line, count);
            
            count++;
            
            line = fileIn.readLine();
         
         }//end while
         
         //closes the file being currently being read from
         fileIn.close();
   
      }//end try block
      
      //check to make sure the file exists and is in same directory
      //as the program itself
      catch(IOException e){
      
          System.out.println("That file does not exist");
        
      }//end catch
      
      //closes the program
      System.exit(0);

   }//end main method
   
}//end Lab1 class
