
package pass;

import java.lang.Integer;
import java.lang.System;

//Accepts two ints (a and b) as command-line arguments and prints a^b to STDOUT

public class BitwiseExclusiveOr {
	
	public int bitwiseExclusiveOrOp(int x, int y) {
		return x ^ y;
	}
	
	public static void main(String[] args) {
		BitwiseExclusiveOr bitwiseExcusiveOr = new BitwiseExclusiveOr();
		int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        System.out.println(a + "^" + b + " = " + bitwiseExcusiveOr.bitwiseExclusiveOrOp(a, b)); 	
	}
}