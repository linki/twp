import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import twp.generator.metamodel.Field;
import twp.generator.metamodel.Forward;
import twp.generator.metamodel.Message;
import twp.generator.metamodel.Protocol;
import twp.generator.metamodel.Sequence;
import twp.generator.metamodel.Specification;
import twp.generator.metamodel.Struct;
import twp.generator.metamodel.Union;

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
		
		enrichModel(metamodel);
		generateCode(metamodel);
		
		// verify
		int numErrors = parser.getNumberOfSyntaxErrors();
		if (numErrors == 0) {
			System.err.println("File is a valid TWP3 specification.");
		} else {
			System.err.println("File doesn't contain a valid TWP3 specification. (" + numErrors + " Errors)");
		}
		
	}
	
	private static void enrichModel(Specification spec) {
		HashMap<String, Integer> global = new HashMap<String, Integer>();
		// 0 - struct, 1 - regex, 2 - sequence, 3 - union
		for (Struct struct:spec.structs) 
			if (struct.id != -1)
				global.put(struct.name, 1);
			else
				global.put(struct.name, 0);
		for (Protocol p:spec.protocols) {
			HashMap<String, Integer> local = new HashMap<String, Integer>();
			for (Struct struct:p.structs) 
				if (struct.id != -1)
					local.put(struct.name, 1);
				else
					local.put(struct.name, 0);
			for (Sequence seq:p.sequences) 
				local.put(seq.name, 2);
			for (Union un:p.unions) 
				local.put(un.name, 3);
			// add additional information to the fields of the message
			for (Message m:p.messages)
				for (Field f:m.fields)
					setField(f, global, local);
			for (Struct struct:p.structs)
				for (Field f:struct.fields)
					setField(f, global, local);
		}
	}
	
	private static void setField(Field f, HashMap<String, Integer> global, HashMap<String, Integer> local) {
		// 0 - struct, 1 - regex, 2 - sequence, 3 - union
		if (!f.type.anyDefinedBy && !f.type.primitive) {
			if (global.containsKey(f.type.name)) {
				if (global.get(f.type.name) == 0)
					f.type.struct = true;
				else
					f.type.registered = true;
			} else if (local.containsKey(f.type.name)) {
				switch (local.get(f.type.name)) {
					case 0:
						f.type.struct = true;
						break;
					case 1:
						f.type.registered = true;
						break;
					case 2:
						f.type.sequence = true;
						break;
					case 3:
						f.type.union = true;
						break;
				}
			} else {
				// throw error - type unknown
			}
		}
	}
	
	private static void generateCode(Specification spec) {
		StringTemplateGroup stg;
		String dir = "src/twp/generated/";
		String dirX = "src/twpx/protocol/";
		try {
			stg = new StringTemplateGroup(new FileReader("templates/javacode.stg"));
			for (Protocol protocol:spec.protocols) {
				StringTemplate st = stg.getInstanceOf("protocol");
				st.setAttribute("protocol", protocol);
				writeToFile(dir + protocol.bigName + "Protocol.java", st.toString());
				st = stg.getInstanceOf("server");
				st.setAttribute("protocol", protocol);
				writeToFile(dir + protocol.bigName + "Server.java", st.toString());
				st = stg.getInstanceOf("handler");
				st.setAttribute("protocol", protocol);
				writeToFile(dir + protocol.bigName + "Handler.java", st.toString());
				st = stg.getInstanceOf("protocolX");
				st.setAttribute("protocol", protocol);
				checkDir(dirX + protocol.smallName + "/");
				writeToFile(dirX + protocol.smallName + "/" + protocol.bigName + "Protocol.java", st.toString());
				for (Message message:protocol.messages) {
					st = stg.getInstanceOf("message");
					st.setAttribute("protocol", protocol);
					st.setAttribute("message", message);
					writeToFile(dir + protocol.bigName + message.bigName + ".java", st.toString());
					st = stg.getInstanceOf("messageX");
					st.setAttribute("protocol", protocol);
					st.setAttribute("message", message);
					writeToFile(dirX + protocol.smallName + "/" + protocol.bigName + message.bigName + ".java", st.toString());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void checkDir(String dir) {
		File file = new File(dir);
		if (!file.exists())
			file.mkdirs();
	}
	
	private static void writeToFile(String filename, String content) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			out.write(content);
			out.close();
		} catch (IOException e) { 
			System.out.println("Couldn't write " + filename);
		}	
	}
}