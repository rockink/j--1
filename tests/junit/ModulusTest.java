
package junit;

import junit.framework.TestCase;
import pass.Modulus;

public class ModulusTest extends TestCase {
	
	private Modulus modulus;
	
	protected void setUp() throws Exception {
			super.setUp();
			modulus = new Modulus();
	}
	
	protected void tearDown() throws Exception {
			super.tearDown();
	}
	
	public void testModulo() {
			this.assertEquals(modulus.modulo(0, 24), 0);
			this.assertEquals(modulus.modulo(24, 12), 0);
			this.assertEquals(modulus.modulo(72, 70), 2);
	}
}
