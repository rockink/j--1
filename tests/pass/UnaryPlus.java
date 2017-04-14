
package pass;

import java.lang.Integer;
import java.lang.System;

//Accepts 1 int (a) as command-line argument and prints +a to STDOUT

public class UnaryPlus {
	
	public int unaryPlusOp(int x) {
		return +x;
	}
	
	public static void main(String[] args) {
		UnaryPlus unaryPlus = new UnaryPlus();
		int a = Integer.parseInt(args[0]);
        System.out.println("+" + a + " = " + unaryPlus.unaryPlusOp(a)); 	
	}
}