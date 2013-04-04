package com.aaron.spreadsheet;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * A test class for the RPN calculator
 * 
 * @author aaron
 *
 */
public class RpnCalculatorTest {

	/**
	 * Test the rpn calculator 
	 */
	@Test public void testRpnCalculator()
	{
		Assert.assertEquals(RpnCalculator.evalRpn("2 3 +"), new Float(5));
		Assert.assertEquals(RpnCalculator.evalRpn("7 1 -"), new Float(6));
		Assert.assertEquals(RpnCalculator.evalRpn("4 3 *"), new Float(12));
		Assert.assertEquals(RpnCalculator.evalRpn("10 2 /"), new Float(5));
		Assert.assertEquals(RpnCalculator.evalRpn("20 3 / 2 +"), new Float(8.666666));
		Assert.assertEquals(RpnCalculator.evalRpn("39 8.666666 3 * /"), new Float(1.5000001));			
	}
}
