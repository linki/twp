package twp.generator;
// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammars/TDL.g 2010-11-21 00:26:46

import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

import twp.generator.metamodel.Case;
import twp.generator.metamodel.Field;
import twp.generator.metamodel.Forward;
import twp.generator.metamodel.Message;
import twp.generator.metamodel.Protocol;
import twp.generator.metamodel.Sequence;
import twp.generator.metamodel.Specification;
import twp.generator.metamodel.Struct;
import twp.generator.metamodel.Type;
import twp.generator.metamodel.Union;

public class TDLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "WHITESPACE", "COMMENT", "ID", "SEVEN", "SEVENUP", "INT", "'any'", "'defined'", "'by'", "'int'", "'string'", "'binary'", "'struct'", "'='", "'ID'", "'{'", "'}'", "'optional'", "';'", "'sequence'", "'<'", "'>'", "'union'", "'case'", "':'", "'typedef'", "'message'", "'protocol'"
    };
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int WHITESPACE=4;
    public static final int SEVEN=7;
    public static final int INT=9;
    public static final int ID=6;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__19=19;
    public static final int T__31=31;
    public static final int SEVENUP=8;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__10=10;
    public static final int COMMENT=5;

    // delegates
    // delegators


        public TDLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TDLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return TDLParser.tokenNames; }
    public String getGrammarFileName() { return "grammars/TDL.g"; }


    public static class identifier_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "identifier"
    // grammars/TDL.g:30:1: identifier : ID ;
    public final TDLParser.identifier_return identifier() throws RecognitionException {
        TDLParser.identifier_return retval = new TDLParser.identifier_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID1=null;

        Object ID1_tree=null;

        try {
            // grammars/TDL.g:31:2: ( ID )
            // grammars/TDL.g:31:4: ID
            {
            root_0 = (Object)adaptor.nil();

            ID1=(Token)match(input,ID,FOLLOW_ID_in_identifier173); 
            ID1_tree = (Object)adaptor.create(ID1);
            adaptor.addChild(root_0, ID1_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "identifier"

    public static class seven_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "seven"
    // grammars/TDL.g:33:1: seven : SEVENUP ;
    public final TDLParser.seven_return seven() throws RecognitionException {
        TDLParser.seven_return retval = new TDLParser.seven_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEVENUP2=null;

        Object SEVENUP2_tree=null;

        try {
            // grammars/TDL.g:34:2: ( SEVENUP )
            // grammars/TDL.g:34:4: SEVENUP
            {
            root_0 = (Object)adaptor.nil();

            SEVENUP2=(Token)match(input,SEVENUP,FOLLOW_SEVENUP_in_seven184); 
            SEVENUP2_tree = (Object)adaptor.create(SEVENUP2);
            adaptor.addChild(root_0, SEVENUP2_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "seven"

    public static class number_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "number"
    // grammars/TDL.g:36:1: number : ( INT | SEVENUP );
    public final TDLParser.number_return number() throws RecognitionException {
        TDLParser.number_return retval = new TDLParser.number_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set3=null;

        Object set3_tree=null;

        try {
            // grammars/TDL.g:37:5: ( INT | SEVENUP )
            // grammars/TDL.g:
            {
            root_0 = (Object)adaptor.nil();

            set3=(Token)input.LT(1);
            if ( (input.LA(1)>=SEVENUP && input.LA(1)<=INT) ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set3));
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "number"

    public static class type_return extends ParserRuleReturnScope {
        public Type type;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "type"
    // grammars/TDL.g:39:1: type returns [Type type] : ( primitiveType | identifier | ( 'any' 'defined' 'by' identifier ) );
    public final TDLParser.type_return type() throws RecognitionException {
        TDLParser.type_return retval = new TDLParser.type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal6=null;
        Token string_literal7=null;
        Token string_literal8=null;
        TDLParser.primitiveType_return primitiveType4 = null;

        TDLParser.identifier_return identifier5 = null;

        TDLParser.identifier_return identifier9 = null;


        Object string_literal6_tree=null;
        Object string_literal7_tree=null;
        Object string_literal8_tree=null;

        retval.type = new Type();
        try {
            // grammars/TDL.g:41:2: ( primitiveType | identifier | ( 'any' 'defined' 'by' identifier ) )
            int alt1=3;
            switch ( input.LA(1) ) {
            case 10:
                {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==11) ) {
                    alt1=3;
                }
                else if ( (LA1_1==ID||LA1_1==25) ) {
                    alt1=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
                }
                break;
            case ID:
                {
                alt1=2;
                }
                break;
            case 13:
            case 14:
            case 15:
                {
                alt1=1;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // grammars/TDL.g:41:4: primitiveType
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_primitiveType_in_type222);
                    primitiveType4=primitiveType();

                    state._fsp--;

                    adaptor.addChild(root_0, primitiveType4.getTree());
                    retval.type.primitive = true; retval.type.setName((primitiveType4!=null?input.toString(primitiveType4.start,primitiveType4.stop):null));

                    }
                    break;
                case 2 :
                    // grammars/TDL.g:42:2: identifier
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_identifier_in_type229);
                    identifier5=identifier();

                    state._fsp--;

                    adaptor.addChild(root_0, identifier5.getTree());
                    retval.type.setName((identifier5!=null?input.toString(identifier5.start,identifier5.stop):null));

                    }
                    break;
                case 3 :
                    // grammars/TDL.g:43:2: ( 'any' 'defined' 'by' identifier )
                    {
                    root_0 = (Object)adaptor.nil();

                    // grammars/TDL.g:43:2: ( 'any' 'defined' 'by' identifier )
                    // grammars/TDL.g:43:3: 'any' 'defined' 'by' identifier
                    {
                    string_literal6=(Token)match(input,10,FOLLOW_10_in_type237); 
                    string_literal6_tree = (Object)adaptor.create(string_literal6);
                    adaptor.addChild(root_0, string_literal6_tree);

                    string_literal7=(Token)match(input,11,FOLLOW_11_in_type239); 
                    string_literal7_tree = (Object)adaptor.create(string_literal7);
                    adaptor.addChild(root_0, string_literal7_tree);

                    string_literal8=(Token)match(input,12,FOLLOW_12_in_type241); 
                    string_literal8_tree = (Object)adaptor.create(string_literal8);
                    adaptor.addChild(root_0, string_literal8_tree);

                    pushFollow(FOLLOW_identifier_in_type243);
                    identifier9=identifier();

                    state._fsp--;

                    adaptor.addChild(root_0, identifier9.getTree());
                    retval.type.anyDefinedBy = true; retval.type.setName((identifier9!=null?input.toString(identifier9.start,identifier9.stop):null));

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "type"

    public static class primitiveType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "primitiveType"
    // grammars/TDL.g:45:1: primitiveType : ( 'int' | 'string' | 'binary' | 'any' );
    public final TDLParser.primitiveType_return primitiveType() throws RecognitionException {
        TDLParser.primitiveType_return retval = new TDLParser.primitiveType_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set10=null;

        Object set10_tree=null;

        try {
            // grammars/TDL.g:46:5: ( 'int' | 'string' | 'binary' | 'any' )
            // grammars/TDL.g:
            {
            root_0 = (Object)adaptor.nil();

            set10=(Token)input.LT(1);
            if ( input.LA(1)==10||(input.LA(1)>=13 && input.LA(1)<=15) ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set10));
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "primitiveType"

    public static class typedef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typedef"
    // grammars/TDL.g:48:1: typedef[Protocol protocol] : (st= structdef | s= sequencedef | u= uniondef | f= forwarddef );
    public final TDLParser.typedef_return typedef(Protocol protocol) throws RecognitionException {
        TDLParser.typedef_return retval = new TDLParser.typedef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        TDLParser.structdef_return st = null;

        TDLParser.sequencedef_return s = null;

        TDLParser.uniondef_return u = null;

        TDLParser.forwarddef_return f = null;



        try {
            // grammars/TDL.g:49:2: (st= structdef | s= sequencedef | u= uniondef | f= forwarddef )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt2=1;
                }
                break;
            case 23:
                {
                alt2=2;
                }
                break;
            case 26:
                {
                alt2=3;
                }
                break;
            case 29:
                {
                alt2=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // grammars/TDL.g:49:4: st= structdef
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_structdef_in_typedef290);
                    st=structdef();

                    state._fsp--;

                    adaptor.addChild(root_0, st.getTree());
                    protocol.structs.add(st.struct);

                    }
                    break;
                case 2 :
                    // grammars/TDL.g:50:2: s= sequencedef
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_sequencedef_in_typedef299);
                    s=sequencedef();

                    state._fsp--;

                    adaptor.addChild(root_0, s.getTree());
                    protocol.sequences.add(s.sequence);

                    }
                    break;
                case 3 :
                    // grammars/TDL.g:51:2: u= uniondef
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_uniondef_in_typedef308);
                    u=uniondef();

                    state._fsp--;

                    adaptor.addChild(root_0, u.getTree());
                    protocol.unions.add(u.union);

                    }
                    break;
                case 4 :
                    // grammars/TDL.g:52:2: f= forwarddef
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forwarddef_in_typedef317);
                    f=forwarddef();

                    state._fsp--;

                    adaptor.addChild(root_0, f.getTree());
                    protocol.forwards.add(f.forward);

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typedef"

    public static class structdef_return extends ParserRuleReturnScope {
        public Struct struct;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structdef"
    // grammars/TDL.g:54:1: structdef returns [Struct struct] : 'struct' identifier ( '=' 'ID' number )? '{' (f= field )+ '}' ;
    public final TDLParser.structdef_return structdef() throws RecognitionException {
        TDLParser.structdef_return retval = new TDLParser.structdef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal11=null;
        Token char_literal13=null;
        Token string_literal14=null;
        Token char_literal16=null;
        Token char_literal17=null;
        TDLParser.field_return f = null;

        TDLParser.identifier_return identifier12 = null;

        TDLParser.number_return number15 = null;


        Object string_literal11_tree=null;
        Object char_literal13_tree=null;
        Object string_literal14_tree=null;
        Object char_literal16_tree=null;
        Object char_literal17_tree=null;

        retval.struct = new Struct();
        try {
            // grammars/TDL.g:56:5: ( 'struct' identifier ( '=' 'ID' number )? '{' (f= field )+ '}' )
            // grammars/TDL.g:56:7: 'struct' identifier ( '=' 'ID' number )? '{' (f= field )+ '}'
            {
            root_0 = (Object)adaptor.nil();

            string_literal11=(Token)match(input,16,FOLLOW_16_in_structdef339); 
            string_literal11_tree = (Object)adaptor.create(string_literal11);
            adaptor.addChild(root_0, string_literal11_tree);

            pushFollow(FOLLOW_identifier_in_structdef341);
            identifier12=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier12.getTree());
            retval.struct.setName((identifier12!=null?input.toString(identifier12.start,identifier12.stop):null));
            // grammars/TDL.g:57:5: ( '=' 'ID' number )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==17) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // grammars/TDL.g:57:7: '=' 'ID' number
                    {
                    char_literal13=(Token)match(input,17,FOLLOW_17_in_structdef352); 
                    char_literal13_tree = (Object)adaptor.create(char_literal13);
                    adaptor.addChild(root_0, char_literal13_tree);

                    string_literal14=(Token)match(input,18,FOLLOW_18_in_structdef354); 
                    string_literal14_tree = (Object)adaptor.create(string_literal14);
                    adaptor.addChild(root_0, string_literal14_tree);

                    pushFollow(FOLLOW_number_in_structdef356);
                    number15=number();

                    state._fsp--;

                    adaptor.addChild(root_0, number15.getTree());
                    retval.struct.id = Integer.parseInt((number15!=null?input.toString(number15.start,number15.stop):null));

                    }
                    break;

            }

            char_literal16=(Token)match(input,19,FOLLOW_19_in_structdef367); 
            char_literal16_tree = (Object)adaptor.create(char_literal16);
            adaptor.addChild(root_0, char_literal16_tree);

            // grammars/TDL.g:58:9: (f= field )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==ID||LA4_0==10||(LA4_0>=13 && LA4_0<=15)||LA4_0==21) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // grammars/TDL.g:58:10: f= field
            	    {
            	    pushFollow(FOLLOW_field_in_structdef372);
            	    f=field();

            	    state._fsp--;

            	    adaptor.addChild(root_0, f.getTree());
            	    retval.struct.fields.add(f.field);

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            char_literal17=(Token)match(input,20,FOLLOW_20_in_structdef378); 
            char_literal17_tree = (Object)adaptor.create(char_literal17);
            adaptor.addChild(root_0, char_literal17_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "structdef"

    public static class field_return extends ParserRuleReturnScope {
        public Field field;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "field"
    // grammars/TDL.g:60:1: field returns [Field field] : ( 'optional' )? t= type identifier ';' ;
    public final TDLParser.field_return field() throws RecognitionException {
        TDLParser.field_return retval = new TDLParser.field_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal18=null;
        Token char_literal20=null;
        TDLParser.type_return t = null;

        TDLParser.identifier_return identifier19 = null;


        Object string_literal18_tree=null;
        Object char_literal20_tree=null;

        retval.field = new Field();
        try {
            // grammars/TDL.g:62:5: ( ( 'optional' )? t= type identifier ';' )
            // grammars/TDL.g:62:7: ( 'optional' )? t= type identifier ';'
            {
            root_0 = (Object)adaptor.nil();

            // grammars/TDL.g:62:7: ( 'optional' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==21) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // grammars/TDL.g:62:8: 'optional'
                    {
                    string_literal18=(Token)match(input,21,FOLLOW_21_in_field402); 
                    string_literal18_tree = (Object)adaptor.create(string_literal18);
                    adaptor.addChild(root_0, string_literal18_tree);

                    retval.field.optional = true;

                    }
                    break;

            }

            pushFollow(FOLLOW_type_in_field410);
            t=type();

            state._fsp--;

            adaptor.addChild(root_0, t.getTree());
            pushFollow(FOLLOW_identifier_in_field412);
            identifier19=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier19.getTree());
            char_literal20=(Token)match(input,22,FOLLOW_22_in_field414); 
            char_literal20_tree = (Object)adaptor.create(char_literal20);
            adaptor.addChild(root_0, char_literal20_tree);

            retval.field.type = t.type;
               	retval.field.setName((identifier19!=null?input.toString(identifier19.start,identifier19.stop):null));

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "field"

    public static class sequencedef_return extends ParserRuleReturnScope {
        public Sequence sequence;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "sequencedef"
    // grammars/TDL.g:67:1: sequencedef returns [Sequence sequence] : 'sequence' '<' t= type '>' identifier ';' ;
    public final TDLParser.sequencedef_return sequencedef() throws RecognitionException {
        TDLParser.sequencedef_return retval = new TDLParser.sequencedef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal21=null;
        Token char_literal22=null;
        Token char_literal23=null;
        Token char_literal25=null;
        TDLParser.type_return t = null;

        TDLParser.identifier_return identifier24 = null;


        Object string_literal21_tree=null;
        Object char_literal22_tree=null;
        Object char_literal23_tree=null;
        Object char_literal25_tree=null;

        retval.sequence = new Sequence();
        try {
            // grammars/TDL.g:69:5: ( 'sequence' '<' t= type '>' identifier ';' )
            // grammars/TDL.g:69:7: 'sequence' '<' t= type '>' identifier ';'
            {
            root_0 = (Object)adaptor.nil();

            string_literal21=(Token)match(input,23,FOLLOW_23_in_sequencedef448); 
            string_literal21_tree = (Object)adaptor.create(string_literal21);
            adaptor.addChild(root_0, string_literal21_tree);

            char_literal22=(Token)match(input,24,FOLLOW_24_in_sequencedef450); 
            char_literal22_tree = (Object)adaptor.create(char_literal22);
            adaptor.addChild(root_0, char_literal22_tree);

            pushFollow(FOLLOW_type_in_sequencedef454);
            t=type();

            state._fsp--;

            adaptor.addChild(root_0, t.getTree());
            char_literal23=(Token)match(input,25,FOLLOW_25_in_sequencedef456); 
            char_literal23_tree = (Object)adaptor.create(char_literal23);
            adaptor.addChild(root_0, char_literal23_tree);

            pushFollow(FOLLOW_identifier_in_sequencedef458);
            identifier24=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier24.getTree());
            char_literal25=(Token)match(input,22,FOLLOW_22_in_sequencedef460); 
            char_literal25_tree = (Object)adaptor.create(char_literal25);
            adaptor.addChild(root_0, char_literal25_tree);

            retval.sequence.type = t.type;
               	retval.sequence.setName((identifier24!=null?input.toString(identifier24.start,identifier24.stop):null));

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "sequencedef"

    public static class uniondef_return extends ParserRuleReturnScope {
        public Union union;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "uniondef"
    // grammars/TDL.g:73:1: uniondef returns [Union union] : 'union' identifier '{' (c= casedef )+ '}' ;
    public final TDLParser.uniondef_return uniondef() throws RecognitionException {
        TDLParser.uniondef_return retval = new TDLParser.uniondef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal26=null;
        Token char_literal28=null;
        Token char_literal29=null;
        TDLParser.casedef_return c = null;

        TDLParser.identifier_return identifier27 = null;


        Object string_literal26_tree=null;
        Object char_literal28_tree=null;
        Object char_literal29_tree=null;

        retval.union = new Union();
        try {
            // grammars/TDL.g:75:5: ( 'union' identifier '{' (c= casedef )+ '}' )
            // grammars/TDL.g:75:7: 'union' identifier '{' (c= casedef )+ '}'
            {
            root_0 = (Object)adaptor.nil();

            string_literal26=(Token)match(input,26,FOLLOW_26_in_uniondef489); 
            string_literal26_tree = (Object)adaptor.create(string_literal26);
            adaptor.addChild(root_0, string_literal26_tree);

            pushFollow(FOLLOW_identifier_in_uniondef491);
            identifier27=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier27.getTree());
            char_literal28=(Token)match(input,19,FOLLOW_19_in_uniondef493); 
            char_literal28_tree = (Object)adaptor.create(char_literal28);
            adaptor.addChild(root_0, char_literal28_tree);

            // grammars/TDL.g:75:30: (c= casedef )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==27) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // grammars/TDL.g:75:31: c= casedef
            	    {
            	    pushFollow(FOLLOW_casedef_in_uniondef498);
            	    c=casedef();

            	    state._fsp--;

            	    adaptor.addChild(root_0, c.getTree());
            	    retval.union.cases.add(c.caze);

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            char_literal29=(Token)match(input,20,FOLLOW_20_in_uniondef504); 
            char_literal29_tree = (Object)adaptor.create(char_literal29);
            adaptor.addChild(root_0, char_literal29_tree);

            retval.union.setName((identifier27!=null?input.toString(identifier27.start,identifier27.stop):null));

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "uniondef"

    public static class casedef_return extends ParserRuleReturnScope {
        public Case caze;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "casedef"
    // grammars/TDL.g:78:1: casedef returns [Case caze] : 'case' number ':' t= type identifier ';' ;
    public final TDLParser.casedef_return casedef() throws RecognitionException {
        TDLParser.casedef_return retval = new TDLParser.casedef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal30=null;
        Token char_literal32=null;
        Token char_literal34=null;
        TDLParser.type_return t = null;

        TDLParser.number_return number31 = null;

        TDLParser.identifier_return identifier33 = null;


        Object string_literal30_tree=null;
        Object char_literal32_tree=null;
        Object char_literal34_tree=null;

        retval.caze = new Case();
        try {
            // grammars/TDL.g:80:5: ( 'case' number ':' t= type identifier ';' )
            // grammars/TDL.g:80:7: 'case' number ':' t= type identifier ';'
            {
            root_0 = (Object)adaptor.nil();

            string_literal30=(Token)match(input,27,FOLLOW_27_in_casedef533); 
            string_literal30_tree = (Object)adaptor.create(string_literal30);
            adaptor.addChild(root_0, string_literal30_tree);

            pushFollow(FOLLOW_number_in_casedef535);
            number31=number();

            state._fsp--;

            adaptor.addChild(root_0, number31.getTree());
            char_literal32=(Token)match(input,28,FOLLOW_28_in_casedef537); 
            char_literal32_tree = (Object)adaptor.create(char_literal32);
            adaptor.addChild(root_0, char_literal32_tree);

            pushFollow(FOLLOW_type_in_casedef541);
            t=type();

            state._fsp--;

            adaptor.addChild(root_0, t.getTree());
            pushFollow(FOLLOW_identifier_in_casedef543);
            identifier33=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier33.getTree());
            char_literal34=(Token)match(input,22,FOLLOW_22_in_casedef545); 
            char_literal34_tree = (Object)adaptor.create(char_literal34);
            adaptor.addChild(root_0, char_literal34_tree);

            retval.caze.id = Integer.parseInt((number31!=null?input.toString(number31.start,number31.stop):null));
               	retval.caze.type = t.type;
               	retval.caze.setName((identifier33!=null?input.toString(identifier33.start,identifier33.stop):null));

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "casedef"

    public static class forwarddef_return extends ParserRuleReturnScope {
        public Forward forward;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forwarddef"
    // grammars/TDL.g:85:1: forwarddef returns [Forward forward] : 'typedef' identifier ';' ;
    public final TDLParser.forwarddef_return forwarddef() throws RecognitionException {
        TDLParser.forwarddef_return retval = new TDLParser.forwarddef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal35=null;
        Token char_literal37=null;
        TDLParser.identifier_return identifier36 = null;


        Object string_literal35_tree=null;
        Object char_literal37_tree=null;

        retval.forward = new Forward();
        try {
            // grammars/TDL.g:87:5: ( 'typedef' identifier ';' )
            // grammars/TDL.g:87:7: 'typedef' identifier ';'
            {
            root_0 = (Object)adaptor.nil();

            string_literal35=(Token)match(input,29,FOLLOW_29_in_forwarddef574); 
            string_literal35_tree = (Object)adaptor.create(string_literal35);
            adaptor.addChild(root_0, string_literal35_tree);

            pushFollow(FOLLOW_identifier_in_forwarddef576);
            identifier36=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier36.getTree());
            char_literal37=(Token)match(input,22,FOLLOW_22_in_forwarddef578); 
            char_literal37_tree = (Object)adaptor.create(char_literal37);
            adaptor.addChild(root_0, char_literal37_tree);

            retval.forward.setName((identifier36!=null?input.toString(identifier36.start,identifier36.stop):null));

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "forwarddef"

    public static class messagedef_return extends ParserRuleReturnScope {
        public Message message;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "messagedef"
    // grammars/TDL.g:90:1: messagedef returns [Message message] : 'message' identifier '=' ( seven | 'ID' number ) '{' (f= field )* '}' ;
    public final TDLParser.messagedef_return messagedef() throws RecognitionException {
        TDLParser.messagedef_return retval = new TDLParser.messagedef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal38=null;
        Token char_literal40=null;
        Token string_literal42=null;
        Token char_literal44=null;
        Token char_literal45=null;
        TDLParser.field_return f = null;

        TDLParser.identifier_return identifier39 = null;

        TDLParser.seven_return seven41 = null;

        TDLParser.number_return number43 = null;


        Object string_literal38_tree=null;
        Object char_literal40_tree=null;
        Object string_literal42_tree=null;
        Object char_literal44_tree=null;
        Object char_literal45_tree=null;

        retval.message = new Message();
        try {
            // grammars/TDL.g:92:5: ( 'message' identifier '=' ( seven | 'ID' number ) '{' (f= field )* '}' )
            // grammars/TDL.g:92:7: 'message' identifier '=' ( seven | 'ID' number ) '{' (f= field )* '}'
            {
            root_0 = (Object)adaptor.nil();

            string_literal38=(Token)match(input,30,FOLLOW_30_in_messagedef606); 
            string_literal38_tree = (Object)adaptor.create(string_literal38);
            adaptor.addChild(root_0, string_literal38_tree);

            pushFollow(FOLLOW_identifier_in_messagedef608);
            identifier39=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier39.getTree());
            retval.message.setName((identifier39!=null?input.toString(identifier39.start,identifier39.stop):null));
            char_literal40=(Token)match(input,17,FOLLOW_17_in_messagedef611); 
            char_literal40_tree = (Object)adaptor.create(char_literal40);
            adaptor.addChild(root_0, char_literal40_tree);

            // grammars/TDL.g:93:5: ( seven | 'ID' number )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==SEVENUP) ) {
                alt7=1;
            }
            else if ( (LA7_0==18) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // grammars/TDL.g:93:7: seven
                    {
                    pushFollow(FOLLOW_seven_in_messagedef620);
                    seven41=seven();

                    state._fsp--;

                    adaptor.addChild(root_0, seven41.getTree());
                    retval.message.id = Integer.parseInt((seven41!=null?input.toString(seven41.start,seven41.stop):null));

                    }
                    break;
                case 2 :
                    // grammars/TDL.g:93:61: 'ID' number
                    {
                    string_literal42=(Token)match(input,18,FOLLOW_18_in_messagedef625); 
                    string_literal42_tree = (Object)adaptor.create(string_literal42);
                    adaptor.addChild(root_0, string_literal42_tree);

                    pushFollow(FOLLOW_number_in_messagedef627);
                    number43=number();

                    state._fsp--;

                    adaptor.addChild(root_0, number43.getTree());
                    retval.message.id = Integer.parseInt((number43!=null?input.toString(number43.start,number43.stop):null));

                    }
                    break;

            }

            char_literal44=(Token)match(input,19,FOLLOW_19_in_messagedef637); 
            char_literal44_tree = (Object)adaptor.create(char_literal44);
            adaptor.addChild(root_0, char_literal44_tree);

            // grammars/TDL.g:94:9: (f= field )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ID||LA8_0==10||(LA8_0>=13 && LA8_0<=15)||LA8_0==21) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // grammars/TDL.g:94:10: f= field
            	    {
            	    pushFollow(FOLLOW_field_in_messagedef642);
            	    f=field();

            	    state._fsp--;

            	    adaptor.addChild(root_0, f.getTree());
            	    retval.message.fields.add(f.field);

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            char_literal45=(Token)match(input,20,FOLLOW_20_in_messagedef648); 
            char_literal45_tree = (Object)adaptor.create(char_literal45);
            adaptor.addChild(root_0, char_literal45_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "messagedef"

    public static class protocol_return extends ParserRuleReturnScope {
        public Protocol protocol;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "protocol"
    // grammars/TDL.g:96:1: protocol returns [Protocol protocol] : 'protocol' identifier '=' 'ID' number '{' ( protocolelement[$protocol] )* '}' ;
    public final TDLParser.protocol_return protocol() throws RecognitionException {
        TDLParser.protocol_return retval = new TDLParser.protocol_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal46=null;
        Token char_literal48=null;
        Token string_literal49=null;
        Token char_literal51=null;
        Token char_literal53=null;
        TDLParser.identifier_return identifier47 = null;

        TDLParser.number_return number50 = null;

        TDLParser.protocolelement_return protocolelement52 = null;


        Object string_literal46_tree=null;
        Object char_literal48_tree=null;
        Object string_literal49_tree=null;
        Object char_literal51_tree=null;
        Object char_literal53_tree=null;

        retval.protocol = new Protocol();
        try {
            // grammars/TDL.g:98:5: ( 'protocol' identifier '=' 'ID' number '{' ( protocolelement[$protocol] )* '}' )
            // grammars/TDL.g:98:7: 'protocol' identifier '=' 'ID' number '{' ( protocolelement[$protocol] )* '}'
            {
            root_0 = (Object)adaptor.nil();

            string_literal46=(Token)match(input,31,FOLLOW_31_in_protocol671); 
            string_literal46_tree = (Object)adaptor.create(string_literal46);
            adaptor.addChild(root_0, string_literal46_tree);

            pushFollow(FOLLOW_identifier_in_protocol673);
            identifier47=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier47.getTree());
            char_literal48=(Token)match(input,17,FOLLOW_17_in_protocol675); 
            char_literal48_tree = (Object)adaptor.create(char_literal48);
            adaptor.addChild(root_0, char_literal48_tree);

            string_literal49=(Token)match(input,18,FOLLOW_18_in_protocol677); 
            string_literal49_tree = (Object)adaptor.create(string_literal49);
            adaptor.addChild(root_0, string_literal49_tree);

            pushFollow(FOLLOW_number_in_protocol679);
            number50=number();

            state._fsp--;

            adaptor.addChild(root_0, number50.getTree());
            char_literal51=(Token)match(input,19,FOLLOW_19_in_protocol681); 
            char_literal51_tree = (Object)adaptor.create(char_literal51);
            adaptor.addChild(root_0, char_literal51_tree);

            // grammars/TDL.g:98:49: ( protocolelement[$protocol] )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==16||LA9_0==23||LA9_0==26||(LA9_0>=29 && LA9_0<=30)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // grammars/TDL.g:98:50: protocolelement[$protocol]
            	    {
            	    pushFollow(FOLLOW_protocolelement_in_protocol684);
            	    protocolelement52=protocolelement(retval.protocol);

            	    state._fsp--;

            	    adaptor.addChild(root_0, protocolelement52.getTree());

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            char_literal53=(Token)match(input,20,FOLLOW_20_in_protocol689); 
            char_literal53_tree = (Object)adaptor.create(char_literal53);
            adaptor.addChild(root_0, char_literal53_tree);

            retval.protocol.setName((identifier47!=null?input.toString(identifier47.start,identifier47.stop):null));
               	retval.protocol.id = Integer.parseInt((number50!=null?input.toString(number50.start,number50.stop):null));

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "protocol"

    public static class protocolelement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "protocolelement"
    // grammars/TDL.g:102:1: protocolelement[Protocol protocol] : ( typedef[$protocol] | m= messagedef );
    public final TDLParser.protocolelement_return protocolelement(Protocol protocol) throws RecognitionException {
        TDLParser.protocolelement_return retval = new TDLParser.protocolelement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        TDLParser.messagedef_return m = null;

        TDLParser.typedef_return typedef54 = null;



        try {
            // grammars/TDL.g:103:5: ( typedef[$protocol] | m= messagedef )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==16||LA10_0==23||LA10_0==26||LA10_0==29) ) {
                alt10=1;
            }
            else if ( (LA10_0==30) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // grammars/TDL.g:103:7: typedef[$protocol]
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_typedef_in_protocolelement709);
                    typedef54=typedef(protocol);

                    state._fsp--;

                    adaptor.addChild(root_0, typedef54.getTree());

                    }
                    break;
                case 2 :
                    // grammars/TDL.g:104:5: m= messagedef
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_messagedef_in_protocolelement721);
                    m=messagedef();

                    state._fsp--;

                    adaptor.addChild(root_0, m.getTree());
                    protocol.messages.add(m.message);

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "protocolelement"

    public static class specification_return extends ParserRuleReturnScope {
        public Specification spec;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "specification"
    // grammars/TDL.g:106:1: specification returns [Specification spec] : (p= protocol | m= messagedef | s= structdef )* EOF ;
    public final TDLParser.specification_return specification() throws RecognitionException {
        TDLParser.specification_return retval = new TDLParser.specification_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF55=null;
        TDLParser.protocol_return p = null;

        TDLParser.messagedef_return m = null;

        TDLParser.structdef_return s = null;


        Object EOF55_tree=null;

        retval.spec = new Specification();
        try {
            // grammars/TDL.g:108:5: ( (p= protocol | m= messagedef | s= structdef )* EOF )
            // grammars/TDL.g:108:7: (p= protocol | m= messagedef | s= structdef )* EOF
            {
            root_0 = (Object)adaptor.nil();

            // grammars/TDL.g:108:7: (p= protocol | m= messagedef | s= structdef )*
            loop11:
            do {
                int alt11=4;
                switch ( input.LA(1) ) {
                case 31:
                    {
                    alt11=1;
                    }
                    break;
                case 30:
                    {
                    alt11=2;
                    }
                    break;
                case 16:
                    {
                    alt11=3;
                    }
                    break;

                }

                switch (alt11) {
            	case 1 :
            	    // grammars/TDL.g:108:8: p= protocol
            	    {
            	    pushFollow(FOLLOW_protocol_in_specification745);
            	    p=protocol();

            	    state._fsp--;

            	    adaptor.addChild(root_0, p.getTree());
            	    retval.spec.protocols.add(p.protocol);

            	    }
            	    break;
            	case 2 :
            	    // grammars/TDL.g:109:5: m= messagedef
            	    {
            	    pushFollow(FOLLOW_messagedef_in_specification757);
            	    m=messagedef();

            	    state._fsp--;

            	    adaptor.addChild(root_0, m.getTree());
            	    retval.spec.messages.add(m.message);

            	    }
            	    break;
            	case 3 :
            	    // grammars/TDL.g:110:5: s= structdef
            	    {
            	    pushFollow(FOLLOW_structdef_in_specification769);
            	    s=structdef();

            	    state._fsp--;

            	    adaptor.addChild(root_0, s.getTree());
            	    retval.spec.structs.add(s.struct);

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            EOF55=(Token)match(input,EOF,FOLLOW_EOF_in_specification775); 
            EOF55_tree = (Object)adaptor.create(EOF55);
            adaptor.addChild(root_0, EOF55_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "specification"

    // Delegated rules


 

    public static final BitSet FOLLOW_ID_in_identifier173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEVENUP_in_seven184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_number0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_type222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_type229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_type237 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_type239 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_type241 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_type243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_primitiveType0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structdef_in_typedef290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sequencedef_in_typedef299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_uniondef_in_typedef308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forwarddef_in_typedef317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_structdef339 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_structdef341 = new BitSet(new long[]{0x00000000000A0000L});
    public static final BitSet FOLLOW_17_in_structdef352 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_structdef354 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_number_in_structdef356 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_structdef367 = new BitSet(new long[]{0x000000000020E440L});
    public static final BitSet FOLLOW_field_in_structdef372 = new BitSet(new long[]{0x000000000030E440L});
    public static final BitSet FOLLOW_20_in_structdef378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_field402 = new BitSet(new long[]{0x000000000020E440L});
    public static final BitSet FOLLOW_type_in_field410 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_field412 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_field414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_sequencedef448 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_sequencedef450 = new BitSet(new long[]{0x000000000020E440L});
    public static final BitSet FOLLOW_type_in_sequencedef454 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_sequencedef456 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_sequencedef458 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_sequencedef460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_uniondef489 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_uniondef491 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_uniondef493 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_casedef_in_uniondef498 = new BitSet(new long[]{0x0000000008100000L});
    public static final BitSet FOLLOW_20_in_uniondef504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_casedef533 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_number_in_casedef535 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_casedef537 = new BitSet(new long[]{0x000000000020E440L});
    public static final BitSet FOLLOW_type_in_casedef541 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_casedef543 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_casedef545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_forwarddef574 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_forwarddef576 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_forwarddef578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_messagedef606 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_messagedef608 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_messagedef611 = new BitSet(new long[]{0x0000000000040100L});
    public static final BitSet FOLLOW_seven_in_messagedef620 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_18_in_messagedef625 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_number_in_messagedef627 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_messagedef637 = new BitSet(new long[]{0x000000000030E440L});
    public static final BitSet FOLLOW_field_in_messagedef642 = new BitSet(new long[]{0x000000000030E440L});
    public static final BitSet FOLLOW_20_in_messagedef648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_protocol671 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_protocol673 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_protocol675 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_protocol677 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_number_in_protocol679 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_protocol681 = new BitSet(new long[]{0x0000000064910000L});
    public static final BitSet FOLLOW_protocolelement_in_protocol684 = new BitSet(new long[]{0x0000000064910000L});
    public static final BitSet FOLLOW_20_in_protocol689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typedef_in_protocolelement709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_messagedef_in_protocolelement721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_protocol_in_specification745 = new BitSet(new long[]{0x00000000E4810000L});
    public static final BitSet FOLLOW_messagedef_in_specification757 = new BitSet(new long[]{0x00000000E4810000L});
    public static final BitSet FOLLOW_structdef_in_specification769 = new BitSet(new long[]{0x00000000E4810000L});
    public static final BitSet FOLLOW_EOF_in_specification775 = new BitSet(new long[]{0x0000000000000002L});

}