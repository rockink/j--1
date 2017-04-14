
package junit;

import junit.framework.TestCase;
import pass.BitwiseExclusiveOr;

public class BitwiseExclusiveOrTest extends TestCase {
	
	private BitwiseExclusiveOr bitwiseExclusiveOr;
	
	protected void setUp() throws Exception {
			super.setUp();
			bitwiseExclusiveOr = new BitwiseExclusiveOr();
	}
	
	protected void tearDown() throws Exception {
			super.tearDown();
	}
	
	public void testBitwiseExclusiveOrOp() {
			this.assertEquals(bitwiseExclusiveOr.bitwiseExclusiveOrOp(60, 13), 49);
	}
}
