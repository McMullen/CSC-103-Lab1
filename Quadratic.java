//-----------------------------------------------------------
//Class Name: Quadratic
//Author:     Jason McMullen and Adam Reese
//Due Date:   10/02/13
//
//Program Description: The Quadratic class is responsible for
//any of data manipulation for each quadratic object. This 
//class contains methods that calculate the roots, number of
//roots, solves for y with a specified x-value, scales the 
//coefficients, finds out if two quadratic objects are equal
//to each other or are alises, and adds two quadratics 
//expressions together.
//-----------------------------------------------------------

import java.text.DecimalFormat;

/**
* The Quadratic class is responsible for any of data manipulation
* for each quadratic object. This class contains methods that
* calculate the roots, number of roots, solves for y with a
* specified x-value, scales the coefficients, finds out if two
* quadratic objects are equal to each other or are alises, and
* adds two quadratics expressions together.
*/

public class Quadratic implements Cloneable{

   /**
   * Coefficient for the first term of the quadratic expression.
   */
   double coefA;
   
   /**
   * Coefficient for the second term of the quadratic expression.
   */
   double coefB;
   
   /**
   * Coefficient for the third term of the quadratic expression.
   */
   double coefC;
 
   /**
   * This is the default constructor. Its purpose is to set
   * all the instance variables to 0 in the case the secondary
   * constructor is not used or called properly.
   */
   
   public Quadratic(){
   
      coefA = 0;
      coefB = 0;
      coefC = 0;
            
   }//end default constructor
      
   /**
   * When a new Quadratic object is made it creates the object with the
   * following coefficents
   */    
   
   public Quadratic(double coefA, double coefB, double coefC){
   
      this.coefA = coefA;
      this.coefB = coefB;
      this.coefC = coefC;
   
   }//end non-default constructor
   
   /**
   * getCoefA returns the value of the first coefficient
   * stored in a quadratic object.
   */
   
   public double getCoefA(){
   
      return coefA;
   
   }//end getCoefA method

   /**
   * getCoefB returns the value of the second coefficient
   * stored in a quadratic object.
   */  
   
   public double getCoefB(){
   
      return coefB;
   
   }//end getCoefB

   /**
   * getCoefC returns the value of the third coefficient
   * stored in a quadratic object.
   */   
   
   public double getCoefC(){
   
      return coefC;
   
   }//end getCoefC

   /**
   *  This method evaluates the quadratic with a specified value for x and
   *  then returns the resulting y-value.
   */  
   
   public double evalExpression(int xValue){
   
      double answer;
      
      answer = (coefA * (Math.pow(xValue, 2))) + (coefB * xValue) + coefC;
   
      return answer;
   
   }//end evalExpression method

   /**
   * This method computes and returns the sum of two different quadratic 
   * equations. The sum is calculated by adding the coefficients of the
   * two incoming quadratic expressions. After adding the coefficients
   * the method creates a new quadratic object and stores those variables
   * inside it.
   */  
   
   public static Quadratic sum(Quadratic quad1, Quadratic quad2){
      
      //adding each term of both expressions
      double firstTerm = quad1.getCoefA() + quad2.getCoefA();
      double secondTerm = quad1.getCoefB() + quad2.getCoefB();
      double thirdTerm = quad1.getCoefC() + quad2.getCoefC();
      
      //creating a new quadratic object and sending its constructor the
      //newly calculated coefficients
      Quadratic quadratic = new Quadratic(firstTerm, secondTerm, thirdTerm);
      
      //returns the new object to the calling method
      return quadratic;
   
   }//end sum method

   /**
   * This method scales the coefficents for the quadratic equation by
   * the a specified amount sent into the method. After the coeffiecents
   * have been scalled a new Quadratic object is created and returned to
   * the calling method.
   */  
   
   public static Quadratic scale(Quadratic quadx, double scalex){
      
      Quadratic quadratic = new Quadratic(quadx.coefA * scalex, 
                       quadx.coefB * scalex, quadx.coefC * scalex);
                       
      return quadratic;
   
   }//end scale method

   /**
   * This method returns the number of real roots for the equation if any exist. 
   * If there are an infinite number of roots then it will  return a 3. The calling
   * method will have to find a way to handle the and interpret the 3 as a way of
   * conveying there are an infinite amount of roots.
   */  
   
   public int getRootNum(){
   
      int answer = 0;
      
      if(coefA != 0){
      
         answer = 2;
      
      }//end first if
      else{
      
         if(coefB !=0){
         
            answer = 1;
         
         }//end second if
         else{
         
            if(coefC != 0){
            
               answer = 0;
            
            }//end third if
            else{
            
               answer = 3;
            
            }//end third else
         
         }//end second else
      
      }//end first else
      
      return answer;
   
   }//end getRootNum method

   /**
   * getRootOne finds the root pertaning to the - portion of
   * the quadratic equation. After finding possible roots, the method
   * returns a string interpretting its findings.
   *
   * This method uses the formula:
   * x = (-b - sqrt(b^2 -4ac)) / (2a)
   */   
   
   public String getRootOne(DecimalFormat pattern){
   
      double underRad;
      
      String answer;
      
      //if the coefficient for A is not zero, then the quadratic will
      //have 2 roots
      if(coefA != 0){
      
         underRad = Math.pow(coefB, 2) - (4 * coefA * coefC);
         
         //if there is a 0 under the radical in the formula, then the
         //roots will be an imaginary number
         if(underRad >= 0){
         
            answer = pattern.format(((coefB * (-1)) - Math.sqrt(underRad)) / (2 *coefA));
         
         }//end if
         else{
      
            answer = "The root is not a real number.";
      
         }//end else
         
       }//end first if
       else{
       
         //if the coefficients for A is 0 and B is not 0, then the quadratic
         //is actually a linear. Solve for x and there will be 1 root
         if(coefB != 0){
         
            answer = pattern.format(((-1) * coefC) / (coefB));
            
         }//end second else
         
         //if the coefficients for A and B are 0 then the quadratic is just a line
         //getNumRoot will check to see if the line is the x-axis, otherwise there
         //will be no roots
         else{
         
            answer = "There are no roots";
         
         }
        
       }//end fist else
      
      return answer;
   
   }//end getRootOne method

   /**
   * getRootTwo finds the root for the current expression using the
   * + portion of the quadratic equation. After finding possible roots, the method
   * returns a string interpretting its findings.
   *
   * This method uses the formula:
   * This method uses the formula:
   * x = (-b + sqrt(b^2 -4ac)) / (2a)
   */  
   
   public String getRootTwo(DecimalFormat pattern){
   
      double underRad;
      
      String answer;
      
      //if the coefficient for A is not zero, then the quadratic will
      //have 2 roots
      if(coefA != 0){
      
         underRad = Math.pow(coefB, 2) - (4 * coefA * coefC);
         
         //if there is a 0 under the radical in the formula, then the
         //roots will be an imaginary number
         if(underRad >= 0){
         
            answer = pattern.format(((coefB * (-1)) + Math.sqrt(underRad)) / (2 *coefA));
         
         }//end if
         else{
      
            answer = "The root is not a real number.";
      
         }//end else
         
       }//end first if
       else{
         
         //if the coefficients for A is 0 and B is not 0, then the quadratic
         //is actually a linear. Solve for x and there will be 1 root
         if(coefB != 0){
         
            answer = pattern.format(((-1) * coefC) / (coefB));
            
         }//end second else
         
         //if the coefficients for A and B are 0 then the quadratic is just a line
         //getNumRoot will check to see if the line is the x-axis, otherwise there
         //will be no roots
         else{
         
            answer = "There are no roots";
         
         }
        
       }//end fist else
      
      return answer;
   
   }//end getRootTwo method

   /**
   * equals checks to see if the two objects currently being
   * examined are alises of each other and if the data stored within
   * them are the same.
   */   
   
   public static void equals(Quadratic quad1, Quadratic quad2){
   
      Quadratic quad3;
      
      //creating quad3 and cloning quad1 into it
      quad3 = (Quadratic)quad1.clone();
      
      if(!(quad3 == quad1)){
      
         System.out.println("quad1 and quad3 are not aliases.");
      
      }//end if
      else{
      
         System.out.println("quad1 and quad3 are aliases.");
      
      }//end else
   
   }//end equals method

   /**
   * clone creates an exact copy of a quadratic object.
   */  
   
   public Object clone(){
      
      Quadratic quadClone = new Quadratic();
      
      try{
       
         quadClone = (Quadratic)super.clone();
   
      }//end try
      catch(CloneNotSupportedException exception){
      
         System.out.println("This class does not implement Cloning.");
      
      }//end catch
      
      return quadClone;
   
   }//end clone method
   
   /**
   * The toString method formats the quadratic equation for each
   * quadratic object using the coefficients stored within them.
   */
   
   public static String toString(Quadratic quadx,  DecimalFormat pattern){
   
      String q = pattern.format(quadx.getCoefA()) + "x^2 + " + 
                  pattern.format(quadx.getCoefB()) + "x + " +
                  pattern.format(quadx.getCoefC());
                  
      return q;
   
   }//end toString method

}//end class Quadratic
