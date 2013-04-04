package com.aaron.spreadsheet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * A spreadsheet that can reduce its cells using an RPN calculator
 *
 */
public class Spreadsheet 
{
	private int numberOfRows;
	private int numberOfColumns;
	private Row[] rows;
	final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	

    /**
     * Get the dimensions of the spreadsheet
     * 
     */
    private void getDimensions() throws IOException
    {
    	String firstLine = in.readLine();
    	System.out.println(firstLine);
    	String lineValues[] = firstLine.split(" ");
    	if (lineValues.length != 2)
    	{
    		throw new IllegalArgumentException("First line not correctly Formatted with spreadsheet dimensions");
    	}
        this.numberOfColumns = Integer.parseInt(lineValues[0]);
        this.numberOfRows = Integer.parseInt(lineValues[1]);
    }
    
    
    /**
     * Load the values from std in into the spreadsheet
     * 
     * @param in
     */
    private void loadValues() throws IOException
    {
    	getDimensions();
    	rows = new Row[this.numberOfRows];
    	for (int i = 0; i < rows.length; i++)	
    	{
    		rows[i] = new Row(this.numberOfColumns);
    		for (int j=0; j < this.numberOfColumns; j++)
    		{
    			Cell cell = new Cell(in.readLine());
    			rows[i].insertCell(cell, j);
    		}
    	}
    }
       
    /**
     * Reduce the spreadsheet
     */
    private void reduce()
    {
    	for (Row row: rows)
    	{
    		for (int column=0; column < numberOfColumns; column++)
    		{    			
    			reduce(row.getCell(column));    			
    		}
    	}
    }
    
    /**
     * Reduce the cell
     * 
     * @param cell
     */
    private void reduce(Cell cell)
    {
    	if (!cell.isReduced())
    	{
			if (cell.isVisited())
			{
			  System.err.println("Cyclic dependency detected");
			  System.exit(1);
			}
			cell.markVisited();
			
			// now all the references have been reduced to strings representing floating point numbers
			cell.setValue(RpnCalculator.evalRpn(reduceCellReferences(cell)));
			cell.markReduced();
    	}
    }
    
    /**
     * Reduce the references to other cells
     * 
     * @param cell
     * @return
     */
    private String reduceCellReferences(Cell cell)
    {
    	String cellValueString = (String) cell.getValue();
    	ArrayList<String> cellReferences = cell.getCellReferences();
    	for (String cellReference: cellReferences)
		{
			Cell dependentCell = find(cellReference);
			reduce(dependentCell);
			cellValueString = cellValueString.replaceAll(cellReference, String.valueOf(dependentCell.getValue()));
		}
    	return cellValueString;
    }
    
    /**
     * Find the cell with the given reference (eg. lookup cell 'C3')
     * 
     * @param cellReference
     * @return
     */
    private Cell find(String cellReference)
    {
    	int rowNumber = ALPHABET.indexOf(cellReference.substring(0, 1));
    	int columnNumber = Integer.valueOf(cellReference.substring(1)) - 1;
    	return rows[rowNumber].getCell(columnNumber);
    }
    
    /**
     * Prints the reduced spreadsheet to std out
     */
    private void print()
    {
    	for (Row row: rows)
    	{
    		for (int i = 0; i < numberOfColumns; i++)
    		{
    			Cell cell = row.getCell(i);
    			System.out.println(String.format("%.5f", cell.getValue()));
    		}
    	}
    }
    
    /**
     * Reduce a spreadsheet from the command line
     * 
     * @param args
     */
    public static void main( String[] args )
    {
    	try
    	{
    		Spreadsheet spreadsheet = new Spreadsheet();
    		spreadsheet.loadValues();
    		spreadsheet.reduce();
    		spreadsheet.print();
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
            System.err.println(e.getMessage());
    	}
    }
    

    
}
