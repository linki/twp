package twp.application;

import java.util.HashMap;

import twp.generated.Parameters;

public class CalculatorFormulaGenerator {

	private HashMap<String, CalculatorOperation> ops = new HashMap<String, CalculatorOperation>();
	
	public Parameters generate(String formula) throws Exception {
		Parameters params = new Parameters();
		checkIfAllOperationsAreRegistered(formula);
		formula = formula.replace(" ", "");
		for (int i=0; i < formula.length(); i++) {
			
		}
		return params;
	}
	
	private boolean checkIfAllOperationsAreRegistered(String formula) throws Exception {
		// TODO: longer operators can contain shorter operators
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
			Parameters params = gen.generate("(6! + 3)*4*5");
			System.out.println(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

