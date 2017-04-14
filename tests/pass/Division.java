
package pass;

import java.lang.Integer;
import java.lang.System;

//Accepts two ints (a and b) as command-line arguments and prints a/b to STDOUT

public class Division {
	
	public int divide(int x, int y) {
		return x / y;
	}
	
	public static void main(String[] args) {
		Division division = new Division();
		int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        if(b == 0)
        	System.out.println("Division by 0 error");
        else
        	System.out.println(a + "/" + b + " = " + division.divide(a, b)); 	
	}
}