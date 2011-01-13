package twp.application;

public class CalculatorOperation {

	public static final int UNARY_OP = 0;
	public static final int BINARY_OP = 1;
	public static final int CONSTANT = 2;
	public static final int PREFIX = 0;
	public static final int INFIX = 1;
	public static final int POSTFIX = 2;
	
	public String host;
	public int port;
	public int pCount;
	public String operator;
	public int position;
	
	public CalculatorOperation(String host, int port, String operator, int paramCount, int position) {
		this.host = host; this.port = port; this.operator = operator;
		this.pCount = paramCount; this.position = position;
	}
	
	public boolean equals(CalculatorOperation other) {
		if (other.operator.equals(operator) &&
			other.pCount == pCount &&
			other.position == position)
			return true;
		return false;
	}
}
