// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammars/TDL.g 2010-11-19 00:35:48

import twp.generator.metamodel.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

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
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "type"
    // grammars/TDL.g:39:1: type : ( primitiveType | identifier | ( 'any' 'defined' 'by' identifier ) );
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

        try {
            // grammars/TDL.g:40:2: ( primitiveType | identifier | ( 'any' 'defined' 'by' identifier ) )
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
                    // grammars/TDL.g:40:4: primitiveType
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_primitiveType_in_type217);
                    primitiveType4=primitiveType();

                    state._fsp--;

                    adaptor.addChild(root_0, primitiveType4.getTree());

                    }
                    break;
                case 2 :
                    // grammars/TDL.g:40:20: identifier
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_identifier_in_type221);
                    identifier5=identifier();

                    state._fsp--;

                    adaptor.addChild(root_0, identifier5.getTree());

                    }
                    break;
                case 3 :
                    // grammars/TDL.g:40:33: ( 'any' 'defined' 'by' identifier )
                    {
                    root_0 = (Object)adaptor.nil();

                    // grammars/TDL.g:40:33: ( 'any' 'defined' 'by' identifier )
                    // grammars/TDL.g:40:34: 'any' 'defined' 'by' identifier
                    {
                    string_literal6=(Token)match(input,10,FOLLOW_10_in_type226); 
                    string_literal6_tree = (Object)adaptor.create(string_literal6);
                    adaptor.addChild(root_0, string_literal6_tree);

                    string_literal7=(Token)match(input,11,FOLLOW_11_in_type228); 
                    string_literal7_tree = (Object)adaptor.create(string_literal7);
                    adaptor.addChild(root_0, string_literal7_tree);

                    string_literal8=(Token)match(input,12,FOLLOW_12_in_type230); 
                    string_literal8_tree = (Object)adaptor.create(string_literal8);
                    adaptor.addChild(root_0, string_literal8_tree);

                    pushFollow(FOLLOW_identifier_in_type232);
                    identifier9=identifier();

                    state._fsp--;

                    adaptor.addChild(root_0, identifier9.getTree());

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
    // grammars/TDL.g:42:1: primitiveType : ( 'int' | 'string' | 'binary' | 'any' );
    public final TDLParser.primitiveType_return primitiveType() throws RecognitionException {
        TDLParser.primitiveType_return retval = new TDLParser.primitiveType_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set10=null;

        Object set10_tree=null;

        try {
            // grammars/TDL.g:43:5: ( 'int' | 'string' | 'binary' | 'any' )
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
    // grammars/TDL.g:45:1: typedef : ( structdef | sequencedef | uniondef | forwarddef );
    public final TDLParser.typedef_return typedef() throws RecognitionException {
        TDLParser.typedef_return retval = new TDLParser.typedef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        TDLParser.structdef_return structdef11 = null;

        TDLParser.sequencedef_return sequencedef12 = null;

        TDLParser.uniondef_return uniondef13 = null;

        TDLParser.forwarddef_return forwarddef14 = null;



        try {
            // grammars/TDL.g:46:2: ( structdef | sequencedef | uniondef | forwarddef )
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
                    // grammars/TDL.g:46:5: structdef
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_structdef_in_typedef275);
                    structdef11=structdef();

                    state._fsp--;

                    adaptor.addChild(root_0, structdef11.getTree());

                    }
                    break;
                case 2 :
                    // grammars/TDL.g:46:17: sequencedef
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_sequencedef_in_typedef279);
                    sequencedef12=sequencedef();

                    state._fsp--;

                    adaptor.addChild(root_0, sequencedef12.getTree());

                    }
                    break;
                case 3 :
                    // grammars/TDL.g:46:31: uniondef
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_uniondef_in_typedef283);
                    uniondef13=uniondef();

                    state._fsp--;

                    adaptor.addChild(root_0, uniondef13.getTree());

                    }
                    break;
                case 4 :
                    // grammars/TDL.g:46:42: forwarddef
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forwarddef_in_typedef287);
                    forwarddef14=forwarddef();

                    state._fsp--;

                    adaptor.addChild(root_0, forwarddef14.getTree());

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
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structdef"
    // grammars/TDL.g:48:1: structdef : 'struct' identifier ( '=' 'ID' number )? '{' ( field )+ '}' ;
    public final TDLParser.structdef_return structdef() throws RecognitionException {
        TDLParser.structdef_return retval = new TDLParser.structdef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal15=null;
        Token char_literal17=null;
        Token string_literal18=null;
        Token char_literal20=null;
        Token char_literal22=null;
        TDLParser.identifier_return identifier16 = null;

        TDLParser.number_return number19 = null;

        TDLParser.field_return field21 = null;


        Object string_literal15_tree=null;
        Object char_literal17_tree=null;
        Object string_literal18_tree=null;
        Object char_literal20_tree=null;
        Object char_literal22_tree=null;

        try {
            // grammars/TDL.g:49:5: ( 'struct' identifier ( '=' 'ID' number )? '{' ( field )+ '}' )
            // grammars/TDL.g:49:7: 'struct' identifier ( '=' 'ID' number )? '{' ( field )+ '}'
            {
            root_0 = (Object)adaptor.nil();

            string_literal15=(Token)match(input,16,FOLLOW_16_in_structdef301); 
            string_literal15_tree = (Object)adaptor.create(string_literal15);
            adaptor.addChild(root_0, string_literal15_tree);

            pushFollow(FOLLOW_identifier_in_structdef303);
            identifier16=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier16.getTree());
            // grammars/TDL.g:49:27: ( '=' 'ID' number )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==17) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // grammars/TDL.g:49:29: '=' 'ID' number
                    {
                    char_literal17=(Token)match(input,17,FOLLOW_17_in_structdef307); 
                    char_literal17_tree = (Object)adaptor.create(char_literal17);
                    adaptor.addChild(root_0, char_literal17_tree);

                    string_literal18=(Token)match(input,18,FOLLOW_18_in_structdef309); 
                    string_literal18_tree = (Object)adaptor.create(string_literal18);
                    adaptor.addChild(root_0, string_literal18_tree);

                    pushFollow(FOLLOW_number_in_structdef311);
                    number19=number();

                    state._fsp--;

                    adaptor.addChild(root_0, number19.getTree());

                    }
                    break;

            }

            char_literal20=(Token)match(input,19,FOLLOW_19_in_structdef316); 
            char_literal20_tree = (Object)adaptor.create(char_literal20);
            adaptor.addChild(root_0, char_literal20_tree);

            // grammars/TDL.g:49:52: ( field )+
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
            	    // grammars/TDL.g:49:52: field
            	    {
            	    pushFollow(FOLLOW_field_in_structdef318);
            	    field21=field();

            	    state._fsp--;

            	    adaptor.addChild(root_0, field21.getTree());

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

            char_literal22=(Token)match(input,20,FOLLOW_20_in_structdef321); 
            char_literal22_tree = (Object)adaptor.create(char_literal22);
            adaptor.addChild(root_0, char_literal22_tree);


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
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "field"
    // grammars/TDL.g:51:1: field : ( 'optional' )? type identifier ';' ;
    public final TDLParser.field_return field() throws RecognitionException {
        TDLParser.field_return retval = new TDLParser.field_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal23=null;
        Token char_literal26=null;
        TDLParser.type_return type24 = null;

        TDLParser.identifier_return identifier25 = null;


        Object string_literal23_tree=null;
        Object char_literal26_tree=null;

        try {
            // grammars/TDL.g:52:5: ( ( 'optional' )? type identifier ';' )
            // grammars/TDL.g:52:7: ( 'optional' )? type identifier ';'
            {
            root_0 = (Object)adaptor.nil();

            // grammars/TDL.g:52:7: ( 'optional' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==21) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // grammars/TDL.g:52:7: 'optional'
                    {
                    string_literal23=(Token)match(input,21,FOLLOW_21_in_field338); 
                    string_literal23_tree = (Object)adaptor.create(string_literal23);
                    adaptor.addChild(root_0, string_literal23_tree);


                    }
                    break;

            }

            pushFollow(FOLLOW_type_in_field341);
            type24=type();

            state._fsp--;

            adaptor.addChild(root_0, type24.getTree());
            pushFollow(FOLLOW_identifier_in_field343);
            identifier25=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier25.getTree());
            char_literal26=(Token)match(input,22,FOLLOW_22_in_field345); 
            char_literal26_tree = (Object)adaptor.create(char_literal26);
            adaptor.addChild(root_0, char_literal26_tree);


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
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "sequencedef"
    // grammars/TDL.g:54:1: sequencedef : 'sequence' '<' type '>' identifier ';' ;
    public final TDLParser.sequencedef_return sequencedef() throws RecognitionException {
        TDLParser.sequencedef_return retval = new TDLParser.sequencedef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal27=null;
        Token char_literal28=null;
        Token char_literal30=null;
        Token char_literal32=null;
        TDLParser.type_return type29 = null;

        TDLParser.identifier_return identifier31 = null;


        Object string_literal27_tree=null;
        Object char_literal28_tree=null;
        Object char_literal30_tree=null;
        Object char_literal32_tree=null;

        try {
            // grammars/TDL.g:55:5: ( 'sequence' '<' type '>' identifier ';' )
            // grammars/TDL.g:55:7: 'sequence' '<' type '>' identifier ';'
            {
            root_0 = (Object)adaptor.nil();

            string_literal27=(Token)match(input,23,FOLLOW_23_in_sequencedef362); 
            string_literal27_tree = (Object)adaptor.create(string_literal27);
            adaptor.addChild(root_0, string_literal27_tree);

            char_literal28=(Token)match(input,24,FOLLOW_24_in_sequencedef364); 
            char_literal28_tree = (Object)adaptor.create(char_literal28);
            adaptor.addChild(root_0, char_literal28_tree);

            pushFollow(FOLLOW_type_in_sequencedef366);
            type29=type();

            state._fsp--;

            adaptor.addChild(root_0, type29.getTree());
            char_literal30=(Token)match(input,25,FOLLOW_25_in_sequencedef368); 
            char_literal30_tree = (Object)adaptor.create(char_literal30);
            adaptor.addChild(root_0, char_literal30_tree);

            pushFollow(FOLLOW_identifier_in_sequencedef370);
            identifier31=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier31.getTree());
            char_literal32=(Token)match(input,22,FOLLOW_22_in_sequencedef372); 
            char_literal32_tree = (Object)adaptor.create(char_literal32);
            adaptor.addChild(root_0, char_literal32_tree);


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
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "uniondef"
    // grammars/TDL.g:57:1: uniondef : 'union' identifier '{' ( casedef )+ '}' ;
    public final TDLParser.uniondef_return uniondef() throws RecognitionException {
        TDLParser.uniondef_return retval = new TDLParser.uniondef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal33=null;
        Token char_literal35=null;
        Token char_literal37=null;
        TDLParser.identifier_return identifier34 = null;

        TDLParser.casedef_return casedef36 = null;


        Object string_literal33_tree=null;
        Object char_literal35_tree=null;
        Object char_literal37_tree=null;

        try {
            // grammars/TDL.g:58:5: ( 'union' identifier '{' ( casedef )+ '}' )
            // grammars/TDL.g:58:7: 'union' identifier '{' ( casedef )+ '}'
            {
            root_0 = (Object)adaptor.nil();

            string_literal33=(Token)match(input,26,FOLLOW_26_in_uniondef389); 
            string_literal33_tree = (Object)adaptor.create(string_literal33);
            adaptor.addChild(root_0, string_literal33_tree);

            pushFollow(FOLLOW_identifier_in_uniondef391);
            identifier34=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier34.getTree());
            char_literal35=(Token)match(input,19,FOLLOW_19_in_uniondef393); 
            char_literal35_tree = (Object)adaptor.create(char_literal35);
            adaptor.addChild(root_0, char_literal35_tree);

            // grammars/TDL.g:58:30: ( casedef )+
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
            	    // grammars/TDL.g:58:30: casedef
            	    {
            	    pushFollow(FOLLOW_casedef_in_uniondef395);
            	    casedef36=casedef();

            	    state._fsp--;

            	    adaptor.addChild(root_0, casedef36.getTree());

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

            char_literal37=(Token)match(input,20,FOLLOW_20_in_uniondef398); 
            char_literal37_tree = (Object)adaptor.create(char_literal37);
            adaptor.addChild(root_0, char_literal37_tree);


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
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "casedef"
    // grammars/TDL.g:60:1: casedef : 'case' number ':' type identifier ';' ;
    public final TDLParser.casedef_return casedef() throws RecognitionException {
        TDLParser.casedef_return retval = new TDLParser.casedef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal38=null;
        Token char_literal40=null;
        Token char_literal43=null;
        TDLParser.number_return number39 = null;

        TDLParser.type_return type41 = null;

        TDLParser.identifier_return identifier42 = null;


        Object string_literal38_tree=null;
        Object char_literal40_tree=null;
        Object char_literal43_tree=null;

        try {
            // grammars/TDL.g:61:5: ( 'case' number ':' type identifier ';' )
            // grammars/TDL.g:61:7: 'case' number ':' type identifier ';'
            {
            root_0 = (Object)adaptor.nil();

            string_literal38=(Token)match(input,27,FOLLOW_27_in_casedef416); 
            string_literal38_tree = (Object)adaptor.create(string_literal38);
            adaptor.addChild(root_0, string_literal38_tree);

            pushFollow(FOLLOW_number_in_casedef418);
            number39=number();

            state._fsp--;

            adaptor.addChild(root_0, number39.getTree());
            char_literal40=(Token)match(input,28,FOLLOW_28_in_casedef420); 
            char_literal40_tree = (Object)adaptor.create(char_literal40);
            adaptor.addChild(root_0, char_literal40_tree);

            pushFollow(FOLLOW_type_in_casedef422);
            type41=type();

            state._fsp--;

            adaptor.addChild(root_0, type41.getTree());
            pushFollow(FOLLOW_identifier_in_casedef424);
            identifier42=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier42.getTree());
            char_literal43=(Token)match(input,22,FOLLOW_22_in_casedef426); 
            char_literal43_tree = (Object)adaptor.create(char_literal43);
            adaptor.addChild(root_0, char_literal43_tree);


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
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forwarddef"
    // grammars/TDL.g:63:1: forwarddef : 'typedef' identifier ';' ;
    public final TDLParser.forwarddef_return forwarddef() throws RecognitionException {
        TDLParser.forwarddef_return retval = new TDLParser.forwarddef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal44=null;
        Token char_literal46=null;
        TDLParser.identifier_return identifier45 = null;


        Object string_literal44_tree=null;
        Object char_literal46_tree=null;

        try {
            // grammars/TDL.g:64:5: ( 'typedef' identifier ';' )
            // grammars/TDL.g:64:7: 'typedef' identifier ';'
            {
            root_0 = (Object)adaptor.nil();

            string_literal44=(Token)match(input,29,FOLLOW_29_in_forwarddef443); 
            string_literal44_tree = (Object)adaptor.create(string_literal44);
            adaptor.addChild(root_0, string_literal44_tree);

            pushFollow(FOLLOW_identifier_in_forwarddef445);
            identifier45=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier45.getTree());
            char_literal46=(Token)match(input,22,FOLLOW_22_in_forwarddef447); 
            char_literal46_tree = (Object)adaptor.create(char_literal46);
            adaptor.addChild(root_0, char_literal46_tree);


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
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "messagedef"
    // grammars/TDL.g:66:1: messagedef : 'message' identifier '=' ( seven | 'ID' number ) '{' ( field )* '}' ;
    public final TDLParser.messagedef_return messagedef() throws RecognitionException {
        TDLParser.messagedef_return retval = new TDLParser.messagedef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal47=null;
        Token char_literal49=null;
        Token string_literal51=null;
        Token char_literal53=null;
        Token char_literal55=null;
        TDLParser.identifier_return identifier48 = null;

        TDLParser.seven_return seven50 = null;

        TDLParser.number_return number52 = null;

        TDLParser.field_return field54 = null;


        Object string_literal47_tree=null;
        Object char_literal49_tree=null;
        Object string_literal51_tree=null;
        Object char_literal53_tree=null;
        Object char_literal55_tree=null;

        try {
            // grammars/TDL.g:67:5: ( 'message' identifier '=' ( seven | 'ID' number ) '{' ( field )* '}' )
            // grammars/TDL.g:67:7: 'message' identifier '=' ( seven | 'ID' number ) '{' ( field )* '}'
            {
            root_0 = (Object)adaptor.nil();

            string_literal47=(Token)match(input,30,FOLLOW_30_in_messagedef463); 
            string_literal47_tree = (Object)adaptor.create(string_literal47);
            adaptor.addChild(root_0, string_literal47_tree);

            pushFollow(FOLLOW_identifier_in_messagedef465);
            identifier48=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier48.getTree());
            char_literal49=(Token)match(input,17,FOLLOW_17_in_messagedef467); 
            char_literal49_tree = (Object)adaptor.create(char_literal49);
            adaptor.addChild(root_0, char_literal49_tree);

            // grammars/TDL.g:67:32: ( seven | 'ID' number )
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
                    // grammars/TDL.g:67:34: seven
                    {
                    pushFollow(FOLLOW_seven_in_messagedef471);
                    seven50=seven();

                    state._fsp--;

                    adaptor.addChild(root_0, seven50.getTree());

                    }
                    break;
                case 2 :
                    // grammars/TDL.g:67:42: 'ID' number
                    {
                    string_literal51=(Token)match(input,18,FOLLOW_18_in_messagedef475); 
                    string_literal51_tree = (Object)adaptor.create(string_literal51);
                    adaptor.addChild(root_0, string_literal51_tree);

                    pushFollow(FOLLOW_number_in_messagedef477);
                    number52=number();

                    state._fsp--;

                    adaptor.addChild(root_0, number52.getTree());

                    }
                    break;

            }

            char_literal53=(Token)match(input,19,FOLLOW_19_in_messagedef481); 
            char_literal53_tree = (Object)adaptor.create(char_literal53);
            adaptor.addChild(root_0, char_literal53_tree);

            // grammars/TDL.g:67:60: ( field )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ID||LA8_0==10||(LA8_0>=13 && LA8_0<=15)||LA8_0==21) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // grammars/TDL.g:67:60: field
            	    {
            	    pushFollow(FOLLOW_field_in_messagedef483);
            	    field54=field();

            	    state._fsp--;

            	    adaptor.addChild(root_0, field54.getTree());

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            char_literal55=(Token)match(input,20,FOLLOW_20_in_messagedef486); 
            char_literal55_tree = (Object)adaptor.create(char_literal55);
            adaptor.addChild(root_0, char_literal55_tree);


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
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "protocol"
    // grammars/TDL.g:69:1: protocol[Specification spec] : 'protocol' identifier '=' 'ID' number '{' ( protocolelement )* '}' ;
    public final TDLParser.protocol_return protocol(Specification spec) throws RecognitionException {
        TDLParser.protocol_return retval = new TDLParser.protocol_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token string_literal56=null;
        Token char_literal58=null;
        Token string_literal59=null;
        Token char_literal61=null;
        Token char_literal63=null;
        TDLParser.identifier_return identifier57 = null;

        TDLParser.number_return number60 = null;

        TDLParser.protocolelement_return protocolelement62 = null;


        Object string_literal56_tree=null;
        Object char_literal58_tree=null;
        Object string_literal59_tree=null;
        Object char_literal61_tree=null;
        Object char_literal63_tree=null;

        Protocol prot = new Protocol();
        spec.protocols.add(prot);
        try {
            // grammars/TDL.g:72:5: ( 'protocol' identifier '=' 'ID' number '{' ( protocolelement )* '}' )
            // grammars/TDL.g:72:7: 'protocol' identifier '=' 'ID' number '{' ( protocolelement )* '}'
            {
            root_0 = (Object)adaptor.nil();

            string_literal56=(Token)match(input,31,FOLLOW_31_in_protocol508); 
            string_literal56_tree = (Object)adaptor.create(string_literal56);
            adaptor.addChild(root_0, string_literal56_tree);

            pushFollow(FOLLOW_identifier_in_protocol510);
            identifier57=identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier57.getTree());
            char_literal58=(Token)match(input,17,FOLLOW_17_in_protocol512); 
            char_literal58_tree = (Object)adaptor.create(char_literal58);
            adaptor.addChild(root_0, char_literal58_tree);

            string_literal59=(Token)match(input,18,FOLLOW_18_in_protocol514); 
            string_literal59_tree = (Object)adaptor.create(string_literal59);
            adaptor.addChild(root_0, string_literal59_tree);

            pushFollow(FOLLOW_number_in_protocol516);
            number60=number();

            state._fsp--;

            adaptor.addChild(root_0, number60.getTree());
            char_literal61=(Token)match(input,19,FOLLOW_19_in_protocol518); 
            char_literal61_tree = (Object)adaptor.create(char_literal61);
            adaptor.addChild(root_0, char_literal61_tree);

            // grammars/TDL.g:72:49: ( protocolelement )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==16||LA9_0==23||LA9_0==26||(LA9_0>=29 && LA9_0<=30)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // grammars/TDL.g:72:49: protocolelement
            	    {
            	    pushFollow(FOLLOW_protocolelement_in_protocol520);
            	    protocolelement62=protocolelement();

            	    state._fsp--;

            	    adaptor.addChild(root_0, protocolelement62.getTree());

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            char_literal63=(Token)match(input,20,FOLLOW_20_in_protocol523); 
            char_literal63_tree = (Object)adaptor.create(char_literal63);
            adaptor.addChild(root_0, char_literal63_tree);

            prot.name = (identifier57!=null?input.toString(identifier57.start,identifier57.stop):null);
               	prot.id = Integer.parseInt((number60!=null?input.toString(number60.start,number60.stop):null));

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
    // grammars/TDL.g:76:1: protocolelement : ( typedef | messagedef );
    public final TDLParser.protocolelement_return protocolelement() throws RecognitionException {
        TDLParser.protocolelement_return retval = new TDLParser.protocolelement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        TDLParser.typedef_return typedef64 = null;

        TDLParser.messagedef_return messagedef65 = null;



        try {
            // grammars/TDL.g:77:5: ( typedef | messagedef )
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
                    // grammars/TDL.g:77:7: typedef
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_typedef_in_protocolelement542);
                    typedef64=typedef();

                    state._fsp--;

                    adaptor.addChild(root_0, typedef64.getTree());

                    }
                    break;
                case 2 :
                    // grammars/TDL.g:77:17: messagedef
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_messagedef_in_protocolelement546);
                    messagedef65=messagedef();

                    state._fsp--;

                    adaptor.addChild(root_0, messagedef65.getTree());

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
    // grammars/TDL.g:79:1: specification returns [Specification spec] : ( protocol[$spec] | messagedef | structdef )* EOF ;
    public final TDLParser.specification_return specification() throws RecognitionException {
        TDLParser.specification_return retval = new TDLParser.specification_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF69=null;
        TDLParser.protocol_return protocol66 = null;

        TDLParser.messagedef_return messagedef67 = null;

        TDLParser.structdef_return structdef68 = null;


        Object EOF69_tree=null;

        retval.spec = new Specification();
        try {
            // grammars/TDL.g:81:5: ( ( protocol[$spec] | messagedef | structdef )* EOF )
            // grammars/TDL.g:81:7: ( protocol[$spec] | messagedef | structdef )* EOF
            {
            root_0 = (Object)adaptor.nil();

            // grammars/TDL.g:81:7: ( protocol[$spec] | messagedef | structdef )*
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
            	    // grammars/TDL.g:81:8: protocol[$spec]
            	    {
            	    pushFollow(FOLLOW_protocol_in_specification566);
            	    protocol66=protocol(retval.spec);

            	    state._fsp--;

            	    adaptor.addChild(root_0, protocol66.getTree());

            	    }
            	    break;
            	case 2 :
            	    // grammars/TDL.g:81:25: messagedef
            	    {
            	    pushFollow(FOLLOW_messagedef_in_specification570);
            	    messagedef67=messagedef();

            	    state._fsp--;

            	    adaptor.addChild(root_0, messagedef67.getTree());

            	    }
            	    break;
            	case 3 :
            	    // grammars/TDL.g:81:38: structdef
            	    {
            	    pushFollow(FOLLOW_structdef_in_specification574);
            	    structdef68=structdef();

            	    state._fsp--;

            	    adaptor.addChild(root_0, structdef68.getTree());

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            EOF69=(Token)match(input,EOF,FOLLOW_EOF_in_specification578); 
            EOF69_tree = (Object)adaptor.create(EOF69);
            adaptor.addChild(root_0, EOF69_tree);


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
    public static final BitSet FOLLOW_primitiveType_in_type217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_type221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_type226 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_type228 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_type230 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_type232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_primitiveType0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structdef_in_typedef275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sequencedef_in_typedef279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_uniondef_in_typedef283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forwarddef_in_typedef287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_structdef301 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_structdef303 = new BitSet(new long[]{0x00000000000A0000L});
    public static final BitSet FOLLOW_17_in_structdef307 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_structdef309 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_number_in_structdef311 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_structdef316 = new BitSet(new long[]{0x000000000020E440L});
    public static final BitSet FOLLOW_field_in_structdef318 = new BitSet(new long[]{0x000000000030E440L});
    public static final BitSet FOLLOW_20_in_structdef321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_field338 = new BitSet(new long[]{0x000000000020E440L});
    public static final BitSet FOLLOW_type_in_field341 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_field343 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_field345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_sequencedef362 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_sequencedef364 = new BitSet(new long[]{0x000000000020E440L});
    public static final BitSet FOLLOW_type_in_sequencedef366 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_sequencedef368 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_sequencedef370 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_sequencedef372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_uniondef389 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_uniondef391 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_uniondef393 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_casedef_in_uniondef395 = new BitSet(new long[]{0x0000000008100000L});
    public static final BitSet FOLLOW_20_in_uniondef398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_casedef416 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_number_in_casedef418 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_casedef420 = new BitSet(new long[]{0x000000000020E440L});
    public static final BitSet FOLLOW_type_in_casedef422 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_casedef424 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_casedef426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_forwarddef443 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_forwarddef445 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_forwarddef447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_messagedef463 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_messagedef465 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_messagedef467 = new BitSet(new long[]{0x0000000000040100L});
    public static final BitSet FOLLOW_seven_in_messagedef471 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_18_in_messagedef475 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_number_in_messagedef477 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_messagedef481 = new BitSet(new long[]{0x000000000030E440L});
    public static final BitSet FOLLOW_field_in_messagedef483 = new BitSet(new long[]{0x000000000030E440L});
    public static final BitSet FOLLOW_20_in_messagedef486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_protocol508 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_identifier_in_protocol510 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_protocol512 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_protocol514 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_number_in_protocol516 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_protocol518 = new BitSet(new long[]{0x0000000064910000L});
    public static final BitSet FOLLOW_protocolelement_in_protocol520 = new BitSet(new long[]{0x0000000064910000L});
    public static final BitSet FOLLOW_20_in_protocol523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typedef_in_protocolelement542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_messagedef_in_protocolelement546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_protocol_in_specification566 = new BitSet(new long[]{0x00000000E4810000L});
    public static final BitSet FOLLOW_messagedef_in_specification570 = new BitSet(new long[]{0x00000000E4810000L});
    public static final BitSet FOLLOW_structdef_in_specification574 = new BitSet(new long[]{0x00000000E4810000L});
    public static final BitSet FOLLOW_EOF_in_specification578 = new BitSet(new long[]{0x0000000000000002L});

}