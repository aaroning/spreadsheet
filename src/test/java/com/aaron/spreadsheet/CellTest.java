package com.aaron.spreadsheet;


import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests the cell class
 * 
 * @author aaron
 *
 */
public class CellTest {
	
	/**
	 * Test the cell class
	 */
	@Test public void testCell()
	{
		Cell cell = new Cell("A1 B1 C2 55");
		ArrayList<String> result = cell.getCellReferences();
		Assert.assertEquals(result.size(), 3);
		Assert.assertTrue(result.contains("A1"));
		Assert.assertTrue(result.contains("B1"));
		Assert.assertTrue(result.contains("C2"));
	}

}
