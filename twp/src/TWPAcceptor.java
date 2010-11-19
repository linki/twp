import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import twp.generator.metamodel.Specification;

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
		TDLParser.specification_return r = parser.specification();
		
		Specification metamodel = r.spec;
		
		System.out.println(metamodel.protocols.get(0).name);
		
		StringTemplateGroup stg = new StringTemplateGroup("generator", "templates/");
		StringTemplate st = stg.getInstanceOf("protocol");
		
		st.setAttribute("protocol", metamodel.protocols.get(0));
		System.out.println(st.toString());
		
		// verify
		int numErrors = parser.getNumberOfSyntaxErrors();
		if (numErrors == 0) {
			System.err.println("File is a valid TWP3 specification.");
		} else {
			System.err.println("File doesn't contain a valid TWP3 specification. (" + numErrors + " Errors)");
		}
		
	}
}