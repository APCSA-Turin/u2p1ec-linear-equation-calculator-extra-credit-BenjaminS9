package com.example.project;
public class LinearCalculator{
    //FOR EXTRA CREDIT 
    //you should copy and paste all of your code from the LinearCalculator class
    // but NOT printInfo(). Please update it below
    //INSTANCE VARIABLES 
    //4 INTEGER variables (name them: x1,x2,y1,y2) 
    private int x1;
    private int x2;
    private int y1;
    private int y2;


    //CONSTRUCTOR
    //1 constructor with 2 String parameters. Each parameter represents a coordinate. 
    //For example, "(1,2)" and "(3,4)" would be two parameter values 
    //You will have to parse the string into 4 integers, representing the 2 points.
    public LinearCalculator(String coord1, String coord2){ // <--add 2 string parameters to this constructor
        String noParenthesis1 = coord1.substring(1, coord1.length() - 1); //gets rid of the parenthesis in the string
        int idx1 = noParenthesis1.indexOf(","); //finds the point of separation between x and y coords
        x1 = Integer.parseInt(noParenthesis1.substring(0, idx1)); //turns x coord into an int
        y1 = Integer.parseInt(noParenthesis1.substring(idx1 + 1)); //turns y coord into an int
        //repeated for second coordinate
        String noParenthesis2 = coord2.substring(1, coord2.length() - 1); 
        int idx2 = noParenthesis2.indexOf(",");
        x2 = Integer.parseInt(noParenthesis2.substring(0, idx2));
        y2 = Integer.parseInt(noParenthesis2.substring(idx2 + 1));
    }



    //METHODS
    //getters and setters for the 4 instance variables (8 methods total) 
    public int getX1(){return x1;} //returns the value of each specific value in either coordinate
    public int getY1(){return y1;}
    public int getX2(){return x2;}
    public int getY2(){return y2;}
    public void setX1(int newX1){x1 = newX1;} //sets a new value to one of the coordinates
    public void setY1(int newY1){y1 = newY1;}
    public void setX2(int newX2){x2 = newX2;}
    public void setY2(int newY2){y2 = newY2;}


    //distance() -> returns a double. 
    //calculates the distance between the two points to the nearest HUNDREDTH and returns the value.
    public double distance(){
        int xDistance = x2 - x1; //Finds "a" in pythagorean theorem
        int yDistance = y2 - y1; //Finds "b" in pyhtagorean theorem
        double pointDistance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2)); //Uses pythagorean theorem to solve for c
        pointDistance = roundedToHundredth(pointDistance);
        return pointDistance;
    }
    //yInt() -> returns a double.
    //calculates the y intercept of the equation and returns the value to the nearest HUNDREDTH
    //if y-int if undefined, should return -999.99
    public double yInt(){
        double yInt;
        int times = Math.abs(x1);
        if (slope() == -999.99) { //Checks if slope exists
            yInt = -999.99;
        } else if (x1 < 0) {
            yInt = roundedToHundredth(y1 + slope() * times); //uses equation b=y+mx derived from slope-intercept form with a negative y-intercept y=mx-b
        } else if (x1 > 0) {
            yInt = roundedToHundredth(y1 - slope() * times); //uses equation b=y-mx derived from slope-intercept form with a positive y-intercept y=mx+b
        } else { //returns 0.0 if x1 is equal to zero
            yInt = 0.0;
        }
        return yInt;
    }

    //slope() -> returns a double. 
    //calculates the slope of the equations and returns the value to the nearest HUNDREDTH
    //if slope is undefined, should return -999.99
    public double slope(){
        if (x2 - x1 == 0) { //checks if denominator would be 0 as it is impossible to divide by zero
            return -999.99; //if denominator is zero, slope would be undefined
        } else {
            double slope = (double) (y2 - y1) / (x2 - x1); //slope is difference of y over difference of x
            slope = roundedToHundredth(slope);
            return slope;
        }
    }

    //equations() -> returns a String.
    //calculates the final equation in y=mx+b form and returns the string
    //if the equation has no slope, the equation should return -> "undefined"
    //HINT: You may need other custom methods to decrease the amount of code in the equations() method
    public String equation(){
        double slope = slope();
        double yInt = yInt();
        if (slope == -999.99) { //checks if slope exists
            return "undefined";
        } else if (yInt < 0){ //for when the y-intercept is a negative value
            return "y=" + slope + "x" + yInt; //uses slope and yInt methods to construct equation y=mx+b
        } else if (yInt == 0) { //checks if y-intercept is 0
            return "y=" + slope + "x";
        } else if (slope == 0) { //checks if slope is 0
            return "y=" + yInt;
        } else { //for when the y-intercept is a positive value
            return "y=" + slope + "x+" + yInt;
        }
    }


    //roundedToHundredth(double x)-> returns double
    //calculates the input to the nearest hundredth and returns that value
    public double roundedToHundredth(double x){
        x = Math.round(x * 100) / 100.0; //numbers after hundredths place are cut off
        return x;
    }

    //You will need to concatenate to the string 
    //the results from findSymmetry() and Midpoint()
    public String printInfo(){
        String str = "The two points are: (" + x1 + "," + y1 + ")";
        str += " and " + "(" + x2 + "," + y2 + ")";
        str += "\nThe equation of the line between these points is: " + equation();
        str += "\nThe slope of this line is: " + slope();
        str += "\nThe y-intercept of the line is: " + yInt();
        str += "\nThe distance between the two points is: " + distance();
        str += "\n"+findSymmetry();
        str +="\n"+Midpoint();
        return str;
    }

    //findSymmetry()-> returns a string 
    //the method should determine if there is symmetry between the two points
    // there should be  4 return statements 
    // return "Symmetric about the x-axis";
    // return "Symmetric about the y-axis";
    //return "Symmetric about the origin";
    //return "No symmetry";
    public String findSymmetry(){
        if (yInt() == 0.0) { //with linear equations, any line that passes through the origin is symmetric to the origin
            return "Symmetric about the origin";
        } else if (slope() == -999.99) { //with linear equations, only vertical lines would be symmetric across the x-axis
            return "Symmetric about the x-axis";
        } else if (slope() == 0.0) { //with linear equations, only horizontal lines would be symmetric across the y-axis
            return "Symmetric about the y-axis";
        } else {
            return "No symmetry";
        }
    }

    //Midpoint()->return a string 
    //the method should calculate the midpoint between the two points
    //it should return "The midpoint of this line is: (0,0)";
    public String Midpoint(){
        double xMid = (x1 + x2) / 2.0; //midpoint is half distance between x and y values of both points
        double yMid = (y1 + y2) / 2.0;
        return "The midpoint of this line is: (" + xMid + "," + yMid + ")";
    }

}



