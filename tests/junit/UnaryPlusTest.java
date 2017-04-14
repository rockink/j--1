
package junit;

import junit.framework.TestCase;
import pass.UnaryPlus;

public class UnaryPlusTest extends TestCase {
	
	private  UnaryPlus unaryPlus;
	
	protected void setUp() throws Exception {
			super.setUp();
			unaryPlus = new UnaryPlus();
	}
	
	protected void tearDown() throws Exception {
			super.tearDown();
	}
	
	public void testUnaryPlusOp() {
			this.assertEquals(unaryPlus.unaryPlusOp(+5), 5);
	}
}
