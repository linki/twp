import org.antlr.runtime.*;
import java.io.*;

public class Test {
	public static void main(String[] args) throws Exception {

		FileInputStream fis = new FileInputStream("example.txt"); 
		
		// create a CharStream that reads from standard input
		ANTLRInputStream input = new ANTLRInputStream(fis);
	
		// create a lexer that feeds off of input CharStream
		TDLLexer lexer = new TDLLexer(input);
	
		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
	
		// create a parser that feeds off the tokens buffer
		TDLParser parser = new TDLParser(tokens);
	
		// begin parsing at rule r
		parser.specification();
	}
}