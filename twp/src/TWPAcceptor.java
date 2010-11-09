import java.io.*;
import java.util.Iterator;

import org.antlr.runtime.*;

// use `ant grammar` in project root to update parser and lexer files

public class TWPAcceptor {
	
	public static void main(String[] args) throws IOException, RecognitionException {
		
		if (args.length < 1) {
			System.err.println("Please provide a TWP3 specification file.");
			return;
		}
		
		// load a file
		FileInputStream fis = new FileInputStream(args[0]); 
		
		// create a CharStream that reads from standard input
		ANTLRInputStream input = new ANTLRInputStream(fis);
	
		// create a lexer that feeds off of input CharStream
		TDLLexer lexer = new TDLLexer(input);
	
		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		// create a parser that feeds off the tokens buffer
		TDLParser parser = new TDLParser(tokens);
	
		// begin parsing at rule specification
		parser.specification();
		
		// verify
		int numErrors = parser.getNumberOfSyntaxErrors();
		if (numErrors == 0) {
			System.err.println("File is a valid TWP3 specification.");
		} else {
			System.err.println("File doesn't contain a valid TWP3 specification. (" + numErrors + " Errors)");
		}
		
	}
}