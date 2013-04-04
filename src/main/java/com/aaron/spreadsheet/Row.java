package com.aaron.spreadsheet;

/**
 * 
 * A Row in the spreadsheet
 * 
 * @author aaron
 *
 */
public class Row {

	private final Cell[] cells;
	
	/**
	 * Create a row with the specified number of columns
	 * @param numColumns
	 */
	public Row(int numColumns) {
		cells = new Cell[numColumns];
	}

	/**
	 * Insert the cell into the specified column number in this row
	 * 
	 * @param cell
	 * @param column
	 */
	public void insertCell(Cell cell, int column)
	{
		cells[column] = cell;
	}
	
	/**
	 * Get the cell at a given column
	 * 
	 * @param column
	 * @return
	 */
	public Cell getCell(int column)
	{
		return cells[column];
	}
	
}
