package com.aaron.spreadsheet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests the spreadsheet class
 * 
 * @author aaron
 *
 */
public class SpreadsheetTest {

	/**
	 * Test the main() method of spreedsheet
	 * 
	 * @throws Exception
	 */
	@Test public void testSpreadsheet() throws Exception
	{
		String data = "3 2\nA2\n4 5 *\nA1\nA1 B2 / 2 +\n3\n39 B1 B2 * /";
		InputStream testInput = new ByteArrayInputStream( data.getBytes("UTF-8") );
		OutputStream outputStream = new FileOutputStream("testoutput");
		PrintStream testOutput = new PrintStream(outputStream);
		InputStream oldIn = System.in;
		PrintStream oldOut = System.out;
		try {
		    System.setIn( testInput );
		    System.setOut(testOutput);
			Spreadsheet.main(new String[0]);
			FileInputStream fileInputStream = new FileInputStream("testoutput");
			BufferedReader in = new BufferedReader(new InputStreamReader(fileInputStream));
			
			Assert.assertEquals(in.readLine(), "3 2");
			Assert.assertEquals(in.readLine(), "20.00000");
			Assert.assertEquals(in.readLine(), "20.00000");
			Assert.assertEquals(in.readLine(), "20.00000");
			Assert.assertEquals(in.readLine(), "8.66667");
			Assert.assertEquals(in.readLine(), "3.00000");
			Assert.assertEquals(in.readLine(), "1.50000");
		} finally {
		    System.setIn( oldIn );
		    System.setOut(oldOut);
		}
	}
	
	/**
	 * Test the main() method of spreedsheet
	 * 
	 * @throws Exception
	 */
	@Test public void testSpreadsheetCycle() throws Exception
	{
		String data = "3 2\nA2\nA3\nB1 B1 +\nA1 B2 / 2 +\n3\n39 B1 B2 * /";
		InputStream testInput = new ByteArrayInputStream( data.getBytes("UTF-8") );
		OutputStream outputStream = new FileOutputStream("errorOutput");
		PrintStream testOutput = new PrintStream(outputStream);
		InputStream oldIn = System.in;
		PrintStream oldErr = System.err;
		try {
		    System.setIn( testInput );
		    System.setErr(testOutput);
			Spreadsheet.main(new String[0]);
			FileInputStream fileInputStream = new FileInputStream("errorOutput");
			BufferedReader in = new BufferedReader(new InputStreamReader(fileInputStream));
			
			Assert.assertEquals(in.readLine(), "Cyclic dependency detected");

		} finally {
		    System.setIn( oldIn );
		    System.setOut(oldErr);
		}
	}
}
