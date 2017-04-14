
package junit;

import junit.framework.TestCase;
import pass.BitwiseAnd;

public class BitwiseAndTest extends TestCase {
	
	private BitwiseAnd bitwiseAnd;
	
	protected void setUp() throws Exception {
			super.setUp();
			bitwiseAnd = new BitwiseAnd();
	}
	
	protected void tearDown() throws Exception {
			super.tearDown();
	}
	
	public void testBitwiseAndOp() {
			this.assertEquals(bitwiseAnd.bitwiseAndOp(60, 13), 12);
	}
}
