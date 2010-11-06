import java.io.*;
import java.util.Iterator;

import org.antlr.runtime.*;

// use `ant grammar` in project root to update parser and lexer files

public class TWPTest {
	public static void main(String[] args) throws Exception {
		// load a file
		FileInputStream fis = new FileInputStream("examples/echo.twp3"); 
		
		// create a CharStream that reads from standard input
		ANTLRInputStream input = new ANTLRInputStream(fis);
	
		// create a lexer that feeds off of input CharStream
		TDLLexer lexer = new TDLLexer(input);
	
		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		// print the tokens
		@SuppressWarnings("unchecked")
		Iterator<CommonToken> iterator = tokens.getTokens().iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getText());
		}
	
		// create a parser that feeds off the tokens buffer
		TDLParser parser = new TDLParser(tokens);
	
		// begin parsing at rule r
		parser.specification();
	}
}