//------------------------------------------------------------------------
//Class Name: QuadTest
//Author:     Jason McMullen and Adam Reese
//Due Date:   10/02/13
//
//Program Description: QuadTest is the class that runs the overall program.
//This class does not contain the main method, but it makes all the 
//necesssary calls to other classes/methods to make all expected 
//calculations for each quadratic expression being read in. This class
//also contains the methods responsible for parsing each string 
//of data being read in from Lab1 Class.
//------------------------------------------------------------------------
   
//files needed to print out to a txt file
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

/**
* QuadTest is the class that runs the overall program. This class
* does not contain the main method, but it makes all the necesssary
* calls to other classes/methods to make all expected calculations
* for each quadratic expression being read in. This class also 
* contains the methods responsible for parsing each string of data
* being read in from Lab1 Class.
*/

public class QuadTest extends Quadratic{
   
   public void quadTestDriver(String line, int count)throws IOException{
   
      //used to format decimal places in variables of type double
      DecimalFormat pattern = new DecimalFormat("0.##");
      
      //objects needed to print to an outside txt file. The "true" in the
      //parameter is needed to add onto a txt file without erasing the contents
      //inside everytime the program trys to write to it
      FileWriter fileOut = new FileWriter("Output.txt", true);
      PrintWriter pw = new PrintWriter(fileOut);
      
      //print to screen a bried description to what this program will do
      intro();
      
      parse(line, count, pw, pattern);
         
      //close the file that was currently being written to
      pw.close();
   
   }//end quadTestDriver method
   
   /**
   * The intro method gives the user a brief description of what 
   * this program will do. 
   */
   
   public void intro(){
   
      System.out.println("This program will read in a file and break apart the lines "
      + "to individual primative data types. After that it will send the data to the "
      + "QuadTest class where it will be parsed and converted to numerical data type. "
      + "Once converted the numbers will be sent to Quadratic where they will become "
      + "the coeffecients to a quadratic equation. From there many calculations will be"
      + " preformed on the expressions and outputted to an outside txt file.");
   
   }//end intro method
   
   /**
   * The calculations method creates a clone of the first quadratic expression and 
   * determines if that clone is an aliase of the first. It will then determine if
   * the coefficients stored in both of them are equal to each other.
   */
   
   public static void calculations(Quadratic quad1, DecimalFormat pattern, PrintWriter pw){
      
      //creating a clone of quad1
      Object quadClone;
      quadClone = (Quadratic)quad1.clone();
      
      //print out the expression in quadratic form
      pw.println("\t" + Quadratic.toString(((Quadratic)quadClone), pattern));

      //check to see if the two objects share the same memory location
      if(!(quadClone == quad1)){
      
         pw.print("The first quadratic and clone of are not aliases, ");
      
      }//end if
      else{
      
         pw.print("The first quadratic and clone are aliases, ");
      
      }//end else
      
      //check to see if the two objects have the same coefficients
      if(!(quad1.equals(((Quadratic)quadClone)))){
      
         pw.println("and are equal to each other.");
      
      }//end if
      else{
      
         pw.println("and do not equal each other.");
      
      }//end else
      
   }//end calculations method
   
   /**
   * The parse method is reponsible for converting incoming lines of text
   * from the main method into tokens which then is converted further into
   * primitve data types. After each piece of data has been successfully converted,
   * this is an error check to make sure the appropriate data is being read in, 
   * the method creates quadratic objects and sends the data to its appropriate
   * place. 
   */
   
   public static void parse(String linex, int count, PrintWriter pw, DecimalFormat pattern){
     
      try{
      
         StringTokenizer tokenizer = new StringTokenizer(linex);
         
         //the first three numbers will be the coefficients for
         //the first expression
         double coefA = Double.parseDouble(tokenizer.nextToken());
         double coefB = Double.parseDouble(tokenizer.nextToken());
         double coefC = Double.parseDouble(tokenizer.nextToken());
         
         //after the first set of coefficients have been parsed
         //will now store them in a quadratic object so that I may
         //reuse these variables
         Quadratic quad1 = new Quadratic(coefA, coefB, coefC);
         
         //the fourth piece of data being read in is the x-value
         //that we will be evaluating for y
         int xValue = Integer.parseInt(tokenizer.nextToken());
         
         //the fifth piece of data being read in will be used to
         //scale the coefficients for each quadratic object made
         //from this line from the txt file
         double scale = Double.parseDouble(tokenizer.nextToken());
         
         //the last set of numbers will be the coefficients for
         //the second and final quadratic object from this line
         //from the txt file
         coefA = Double.parseDouble(tokenizer.nextToken());
         coefB = Double.parseDouble(tokenizer.nextToken());
         coefC = Double.parseDouble(tokenizer.nextToken());
         
         //after the coefficients have been parsed a new quadratic
         //object is created to store them for later use
         Quadratic quad2 = new Quadratic(coefA, coefB, coefC);
         
         //create a new Quadratic object and store the value for the summation
         //of the first and second quadratic expressions
         Quadratic sumQuadratic = new Quadratic();
         sumQuadratic = Quadratic.sum(quad1, quad2);
         
         //printout the details of the program                     
         output(quad1, quad2, sumQuadratic, scale, pattern, xValue, scale, count, pw);

      }//end try block
      
      //catch exception if any of these values have been omitted from the line
      //being currently read in
      catch(Exception e){
      
         pw.println(linex);
         pw.println("This line contains invalid data. It will be ignored.");
      
      }//end catch block     
   
   }//end parse method
   
   /**
   * The output method is very similar to what one would expect from a 
   * toString method. Output prints out all the relevant data stored
   * inside each quadratic. Data such as: the coefficients, scale, the
   * quadratic expression of the sum of two quadratics,  and the number 
   * of possible roots along with their values if any should exist. The
   * method is also responsible for calling on the calculations method
   * to print out the relevant data for the clone of the first quadratic
   * expression.
   */
   
   public static void output(Quadratic quad1, Quadratic quad2, Quadratic sum, double scale,
                      DecimalFormat pattern, int xValue, double scalex, int testNum,
                      PrintWriter pw){
                  
      //print out the header
      pw.println("**********************************************************");
      pw.println("Test #" + testNum);
      pw.println("------------");
      pw.println("");
      
      //stating the first quadratic equation
      pw.println("The first quadratic is:");
      pw.println("\t" + Quadratic.toString(quad1, pattern));
      
      //solve for y with specific x
      pw.println("The values of the first quadratic expression with x = " + 
                        pattern.format(xValue) + " is:" + "\t" + 
                        pattern.format(quad1.evalExpression(xValue)));
                        
      pw.println("");
      
      //scale the coefficients of the quadratic equation
      pw.println("The first quadratic after scaling with R = " + pattern.format(scalex));
      pw.println("\t" + Quadratic.toString(Quadratic.scale(quad1, scalex), pattern));
                        
      pw.println("");
      
      //-------------------------------------------------------------------------------------------
      
      //check to see how many roots need to be printed out. If getRootNum returns 3, then there
      //are inifite amount of roots for the line is the x-axis itself
      if(quad1.getRootNum() == 3){
      
         pw.println("There is an inifite amount of roots.");
      
      }//end if
      else{
      
         pw.println("Number of roots: " + "\t" + quad1.getRootNum());
      
      }//end else
      
      //-------------------------------------------------------------------------------------------
      
      //check to see how many values for the root need to be printed out. Similar to the above
      //check, instead of the amount this grouping prints the actual value
      if(quad1.getRootNum() == 2){
      
         pw.println("Value of the roots:");
         pw.println("\t" + "Value of root1:" + "\t" + quad1.getRootOne(pattern));
         pw.println("\t" + "Value of root2:" + "\t" + quad1.getRootTwo(pattern));
      
      }//end if
      else{
      
         if(quad1.getRootNum() == 1)
         {
         
            pw.println("Value of the root:" + "\t" + quad1.getRootOne(pattern));
         
         }//end if
      
      }//end else
      
      //-------------------------------------------------------------------------------------------
      
      pw.println("");
      
      //print the second expression in quadtratic form
      pw.println("The second quadratic is:");
      pw.println("\t" + Quadratic.toString(quad2, pattern));
      
      pw.println("");
      
      //print the sum of the two expression in quadratic form
      pw.println("The quadratic which is the sum of the first and second quadratics:");
      pw.println("\t" + Quadratic.toString(sum, pattern));
      
      pw.println("");
      
      //print the clone of the first quadratic
      pw.println("A clone of the first quadratic is:");
      calculations(quad1, pattern, pw);
      
      //separation for next test
      pw.println("\n"); 
   
   }//end output method

}//end class QuadTest
