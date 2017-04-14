
package junit;

import junit.framework.TestCase;
import pass.BitwiseOr;

public class BitwiseOrTest extends TestCase {
	
	private BitwiseOr bitwiseOr;
	
	protected void setUp() throws Exception {
			super.setUp();
			bitwiseOr = new BitwiseOr();
	}
	
	protected void tearDown() throws Exception {
			super.tearDown();
	}
	
	public void testBitwiseOrOp() {
			this.assertEquals(bitwiseOr.bitwiseOrOp(60, 13), 61);
	}
}
