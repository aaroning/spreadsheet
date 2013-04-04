package com.aaron.spreadsheet;

import java.util.Arrays;
import java.util.Stack;


/**
 * An RPN calculator
 * @author aaron
 *
 */
public class RpnCalculator {

	/**
	 * Evaluate an RPN string in stack order
	 * 
	 * @param rpnString
	 * @return
	 */
	protected static float evalRpn(String rpnString)
	{
		Stack<String> rpnStack = new Stack<String>();
		rpnStack.addAll(Arrays.asList(rpnString.split(" ")));
		return evalRpnStack(rpnStack);
	}
	
	
	private static float evalRpnStack(Stack<String> rpnStack) {
		
	    String param = rpnStack.pop();
	    float x,y;
	    try  {x = Float.parseFloat(param);}
	    catch (Exception e)  {
	      y = evalRpnStack(rpnStack);  x = evalRpnStack(rpnStack);
	      if      (param.equals("+"))  x += y;
	      else if (param.equals("-"))  x -= y;
	      else if (param.equals("*"))  x *= y;
	      else if (param.equals("/"))  x /= y;
	      else throw new IllegalArgumentException();
	    }
	    return x;
	  }
}
