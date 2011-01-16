package twp.application;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import twp.generated.Expression;
import twp.generated.Parameters;
import twp.generated.Term;

public class CalculatorFormulaGenerator {

	private HashMap<String, CalculatorOperation> ops = new HashMap<String, CalculatorOperation>();
	
	private int index;
	
	public Expression generate(String formula) throws Exception {
		checkIfAllOperationsAreRegistered(formula);
		formula = formula.replace(" ", "");
		List<String> tokens = tokenize(formula);
		System.out.println(tokens);
		index = 0;
		List<Object> groups = removeParenthesis(tokens);
		System.out.println(groups);
		groups = prioritize(groups);
		System.out.println(groups);
		groups = finalSort(groups);
		System.out.println(groups);
		Expression expr = convert(groups);
		return expr;
	}
	
	/**
	 * Creates an Expression for the given operator based on the registered operations.
	 * @param operator
	 * @return Expression
	 * @throws UnknownHostException
	 */
	private Expression createExpression(String operator) throws UnknownHostException {
		CalculatorOperation op = ops.get(operator);
		InetAddress addr = InetAddress.getByName(op.host);
		return new Expression(addr.getAddress(), op.port, new Parameters());
	}
	
	/**
	 * Converts the created hierarchy of operators and values into an Expression
	 * which can be used by the Calculator protocol implementation.
	 * @param list of string (operators), doubles and further lists 
	 * @return Expression
	 * @throws UnknownHostException
	 */
	private Expression convert(List<Object> groups) throws UnknownHostException {
		Expression expr = null;
		Parameters params = new Parameters();
		for (Object obj:groups) {
			if (obj instanceof List) 
				params.add(new Term(convert((List<Object>) obj)));
			else if (obj instanceof Double) 
				params.add(new Term(new twp.generated.Double((Double) obj)));
			else 
				expr = createExpression((String) obj);
		}
		expr.setArguments(params);
		return expr;
	}
	
	/**
	 * This is the last step before the actual conversion.
	 * It limits the size of each list to a maximum of 3 elements
	 * and adds theirfore additional hierarchy layers.
	 * @param list with string, doubles and lists
	 * @return a ready to convert list with all elements of the original formula
	 * @throws Exception
	 */
	private List<Object> finalSort(List<Object> list) throws Exception {
		List<Object> temp = new ArrayList<Object>();
		for (Object current:list) {
			// first sort the contained lists
			if (current instanceof List)
				temp.add(finalSort((List<Object>) current));
			else 
				temp.add(current);
		}
		List<Object> groups = new ArrayList<Object>();
		if (temp.size() >= 3 && 
			(temp.get(0) instanceof String ||
			temp.get(temp.size() - 1) instanceof String)) {
			// check if the first or last element is an operator
			throw new Exception("Currently are just infix binary operators supported.");
		}
		if (temp.size() <= 3)
			// the list is shorter than 3, 
			// so it just contains an unary operator and a value or a constant
			groups = temp;
		else {
			// every list should have just 3 elements: 2 values and 1 operator
			for (int i = 0; i < temp.size(); i++) {
				if (groups.size() == 3) {
					List<Object> l = new ArrayList<Object>();
					l.addAll(groups);
					groups.clear();
					groups.add(l);
				}
				groups.add(temp.get(i));
			}
		}
		return groups;
	}
	
	/**
	 * Converts strings with double values to doubles and 
	 * adds additional hierarchy layers for unary operators and
	 * constants to keep their priority higher than binary operators.
	 * @param list with string and string lists
	 * @return list containing string, doubles and further lists
	 * @throws Exception
	 */
	private List<Object> prioritize(List<Object> list) throws Exception {
		List<Object> groups = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++)  {
			if (list.get(i) instanceof List) {
				groups.add(prioritize((List<Object>) list.get(i)));
			} else if (list.get(i) instanceof String) {
				String current = (String) list.get(i);
				if (current.matches("[0-9.]+"))
					groups.add(Double.parseDouble(current));
				else {
					CalculatorOperation op = ops.get(current);
					if (op.pCount == CalculatorOperation.CONSTANT) {
						List<Object> l = new ArrayList<Object>();
						l.add(current);
						groups.add(l);
					} else if (op.pCount == CalculatorOperation.UNARY_OP) {
						if (op.position == CalculatorOperation.POSTFIX) {
							// remove the last entry from groups and put it
							// together with the current operator into a new list
							Object o = groups.get(groups.size() - 1);
							List<Object> l = new ArrayList<Object>();
							l.add(o);
							l.add(current);
							groups.remove(o);
							groups.add(l);
						} else {
							// the operator is a prefix operator
							// so we have to look forward for the next value
							int j = i + 1;
							while(j < list.size()) {
								Object temp = list.get(j);
								if (temp instanceof String) {
									if (((String) temp).matches("[0-9.]+"))
										break;
									CalculatorOperation co = ops.get((String) temp);
									if (co.pCount != CalculatorOperation.UNARY_OP ||
										co.position != CalculatorOperation.PREFIX)
										throw new Exception("The prefix operator " + current + " is followed by the non prefix operator " + (String) temp);
								} else
									// we found the next value and stop here
									break;
								j++;
							}
							// now we create backwards multiple sublists 
							List<Object> l = new ArrayList<Object>();
							Object z = list.get(j);
							// but first initialize the value
							if (z instanceof String && ((String) z).matches("[0-9.]+"))
								z = Double.parseDouble((String) z);
							else if (z instanceof List)
								z = prioritize((List<Object>) z);
							l.add(z);
							for (int k = j-1; k >= i; k--) {
								l.add(0, list.get(k));
								List<Object> temp = new ArrayList<Object>();
								temp.add(l);
								l = temp;
							}
							groups.add(l.get(0));
							// at the end go ahead with the last used index j
							i = j;
						}
					} else 
						groups.add(current);
				}
			}
		}
		return groups;
	}
	
	/**
	 * Removes the existing parenthesis and replaces them with
	 * lists to retain the hierarchy.
	 * @param a list with string tokens
	 * @return a list which contains string and list with strings
	 */
	private List<Object> removeParenthesis(List<String> tokens) {
		List<Object> groups = new ArrayList<Object>();
		while (index < tokens.size()) {
			if (tokens.get(index).equals("(")) {
				// '(' starts a sublist
				index++;
				groups.add(removeParenthesis(tokens));
			} else if (tokens.get(index).equals(")")) {
				// ')' ends a sublist
				index++;
				return groups;
			} else {
				// normal entry of a list
				groups.add(tokens.get(index));
				index++;
			}
		}
		return groups;
	}
	
	/**
	 * Creates a token list out of the given formula string.
	 * @param string to tokenize
	 * @return a list with string tokens
	 */
	private List<String> tokenize(String formula) {
		List<String> tokens = new ArrayList<String>();
		StringBuffer buffer = new StringBuffer();
		for (int i=0; i < formula.length(); i++) {
			String current = formula.substring(i, i+1);
			if (current.matches("[()]")) {
				tokens.add(current);
				continue;
			} 
			if (current.matches("[0-9.]")) {
				buffer.append(current);
				if (i < (formula.length() - 1)) {
					if (!formula.substring(i+1, i+2).matches("[0-9.]")) {
						// look ahead if more numbers will follow
						tokens.add(buffer.toString());
						buffer = new StringBuffer();
					}
				} else 
					// last character
					tokens.add(buffer.toString());
				continue;
			}
			// the character must be part of an operator 
			buffer.append(current);
			for (String operator:ops.keySet()) {
				if (buffer.toString().equals(operator)) {
					tokens.add(buffer.toString());
					buffer = new StringBuffer();
				}
			}
		}
		return tokens;
	}
	
	/**
	 * Checks whether all used operators are registered and if the used number
	 * of opening and closing parenthesis is equal. 
	 * @param formula
	 * @return true if the checks revealed no error
	 * @throws Exception
	 */
	private boolean checkIfAllOperationsAreRegistered(String formula) throws Exception {
		// TODO: longer operators can contain shorter operators
		int open = 0, close = 0;
		for (int i = 0; i < formula.length(); i++) {
			if (formula.charAt(i) == '(') {
				open ++;
				continue;
			}
			if (formula.charAt(i) == ')')
				close++;
		}
		if (open != close)
			throw new Exception("The number of parenthesis isn't equal.");
		String check = formula.replaceAll("[() 0-9.]", "");
		for (String op:ops.keySet()) {
			check = check.replace(op, "");
		}
		if (!check.equals(""))
			throw new Exception("These operators are not registered: " + check);
		return false;
	}
	
	public void register(CalculatorOperation op) throws Exception {
		if (ops.containsKey(op.operator))
			throw new Exception("Operation is already registered.");
		ops.put(op.operator, op);
	}
	
	public static void main(String[] args) {
		CalculatorFormulaGenerator gen = new CalculatorFormulaGenerator();
		try {
			gen.register(new CalculatorOperation("localhost", 12347, "+", CalculatorOperation.BINARY_OP, CalculatorOperation.INFIX));
			gen.register(new CalculatorOperation("localhost", 12348, "*", CalculatorOperation.BINARY_OP, CalculatorOperation.INFIX));
			gen.register(new CalculatorOperation("localhost", 12349, "!", CalculatorOperation.UNARY_OP, CalculatorOperation.POSTFIX));
			gen.register(new CalculatorOperation("localhost", 12349, "PI", CalculatorOperation.CONSTANT, CalculatorOperation.POSTFIX));
			gen.register(new CalculatorOperation("localhost", 12348, "sin", CalculatorOperation.UNARY_OP, CalculatorOperation.PREFIX));
			Expression params = gen.generate("1 + 1 + 1 + 1 + 1");
			System.out.println(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

