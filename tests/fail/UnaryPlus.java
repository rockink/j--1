
package fail;
import java.lang.System ;

//This program has errors and shouldn't compile.

public class UnaryPlus {
	
	public static void main (String[] args) {
			System.out.println (+'Q'); //Error, since j-- supports only type int UnaryPlus operation
	}
}