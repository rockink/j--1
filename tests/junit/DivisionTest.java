
package junit;

import junit.framework.TestCase;
import pass.Division;

public class DivisionTest extends TestCase {
	
	private Division division;
	
	protected void setUp() throws Exception {
			super.setUp();
			division = new Division();
	}
	
	protected void tearDown() throws Exception {
			super.tearDown();
	}
	
	public void testDivide() {
			this.assertEquals(division.divide(0, 24), 0);
			this.assertEquals(division.divide(24, 1), 24);
			this.assertEquals(division.divide(72, 3), 24);
	}
}
