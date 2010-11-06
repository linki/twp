// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammars/TDL.g 2010-11-06 16:45:30

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TDLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "WHITESPACE", "ID", "SEVEN", "INT", "'any'", "'defined'", "'by'", "'int'", "'string'", "'binary'", "'struct'", "'='", "'ID'", "'{'", "'}'", "'optional'", "';'", "'sequence'", "'<'", "'>'", "'union'", "'case'", "':'", "'typedef'", "'message'", "'protocol'"
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
    public static final int SEVEN=6;
    public static final int INT=7;
    public static final int ID=5;
    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int T__8=8;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__10=10;

    // delegates
    // delegators


        public TDLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TDLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TDLParser.tokenNames; }
    public String getGrammarFileName() { return "grammars/TDL.g"; }



    // $ANTLR start "identifier"
    // grammars/TDL.g:9:1: identifier : ID ;
    public final void identifier() throws RecognitionException {
        try {
            // grammars/TDL.g:10:2: ( ID )
            // grammars/TDL.g:10:4: ID
            {
            match(input,ID,FOLLOW_ID_in_identifier102); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "identifier"


    // $ANTLR start "seven"
    // grammars/TDL.g:12:1: seven : SEVEN ;
    public final void seven() throws RecognitionException {
        try {
            // grammars/TDL.g:12:7: ( SEVEN )
            // grammars/TDL.g:13:2: SEVEN
            {
            match(input,SEVEN,FOLLOW_SEVEN_in_seven113); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "seven"


    // $ANTLR start "number"
    // grammars/TDL.g:15:1: number : INT ;
    public final void number() throws RecognitionException {
        try {
            // grammars/TDL.g:16:5: ( INT )
            // grammars/TDL.g:16:7: INT
            {
            match(input,INT,FOLLOW_INT_in_number127); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "number"


    // $ANTLR start "type"
    // grammars/TDL.g:18:1: type : ( primitiveType | identifier | ( 'any' 'defined' 'by' identifier ) );
    public final void type() throws RecognitionException {
        try {
            // grammars/TDL.g:19:2: ( primitiveType | identifier | ( 'any' 'defined' 'by' identifier ) )
            int alt1=3;
            switch ( input.LA(1) ) {
            case 8:
                {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==9) ) {
                    alt1=3;
                }
                else if ( (LA1_1==ID||LA1_1==23) ) {
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
            case 11:
            case 12:
            case 13:
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
                    // grammars/TDL.g:19:4: primitiveType
                    {
                    pushFollow(FOLLOW_primitiveType_in_type142);
                    primitiveType();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // grammars/TDL.g:19:20: identifier
                    {
                    pushFollow(FOLLOW_identifier_in_type146);
                    identifier();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // grammars/TDL.g:19:33: ( 'any' 'defined' 'by' identifier )
                    {
                    // grammars/TDL.g:19:33: ( 'any' 'defined' 'by' identifier )
                    // grammars/TDL.g:19:34: 'any' 'defined' 'by' identifier
                    {
                    match(input,8,FOLLOW_8_in_type151); 
                    match(input,9,FOLLOW_9_in_type153); 
                    match(input,10,FOLLOW_10_in_type155); 
                    pushFollow(FOLLOW_identifier_in_type157);
                    identifier();

                    state._fsp--;


                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "type"


    // $ANTLR start "primitiveType"
    // grammars/TDL.g:20:1: primitiveType : ( 'int' | 'string' | 'binary' | 'any' );
    public final void primitiveType() throws RecognitionException {
        try {
            // grammars/TDL.g:21:5: ( 'int' | 'string' | 'binary' | 'any' )
            // grammars/TDL.g:
            {
            if ( input.LA(1)==8||(input.LA(1)>=11 && input.LA(1)<=13) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "primitiveType"


    // $ANTLR start "typedef"
    // grammars/TDL.g:23:1: typedef : ( structdef | sequencedef | uniondef | forwarddef );
    public final void typedef() throws RecognitionException {
        try {
            // grammars/TDL.g:24:2: ( structdef | sequencedef | uniondef | forwarddef )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt2=1;
                }
                break;
            case 21:
                {
                alt2=2;
                }
                break;
            case 24:
                {
                alt2=3;
                }
                break;
            case 27:
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
                    // grammars/TDL.g:24:5: structdef
                    {
                    pushFollow(FOLLOW_structdef_in_typedef198);
                    structdef();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // grammars/TDL.g:24:17: sequencedef
                    {
                    pushFollow(FOLLOW_sequencedef_in_typedef202);
                    sequencedef();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // grammars/TDL.g:24:31: uniondef
                    {
                    pushFollow(FOLLOW_uniondef_in_typedef206);
                    uniondef();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // grammars/TDL.g:24:42: forwarddef
                    {
                    pushFollow(FOLLOW_forwarddef_in_typedef210);
                    forwarddef();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "typedef"


    // $ANTLR start "structdef"
    // grammars/TDL.g:25:1: structdef : 'struct' identifier ( '=' 'ID' number )? '{' ( field )+ '}' ;
    public final void structdef() throws RecognitionException {
        try {
            // grammars/TDL.g:26:5: ( 'struct' identifier ( '=' 'ID' number )? '{' ( field )+ '}' )
            // grammars/TDL.g:26:7: 'struct' identifier ( '=' 'ID' number )? '{' ( field )+ '}'
            {
            match(input,14,FOLLOW_14_in_structdef222); 
            pushFollow(FOLLOW_identifier_in_structdef224);
            identifier();

            state._fsp--;

            // grammars/TDL.g:26:27: ( '=' 'ID' number )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==15) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // grammars/TDL.g:26:29: '=' 'ID' number
                    {
                    match(input,15,FOLLOW_15_in_structdef228); 
                    match(input,16,FOLLOW_16_in_structdef230); 
                    pushFollow(FOLLOW_number_in_structdef232);
                    number();

                    state._fsp--;


                    }
                    break;

            }

            match(input,17,FOLLOW_17_in_structdef237); 
            // grammars/TDL.g:26:52: ( field )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==ID||LA4_0==8||(LA4_0>=11 && LA4_0<=13)||LA4_0==19) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // grammars/TDL.g:26:52: field
            	    {
            	    pushFollow(FOLLOW_field_in_structdef239);
            	    field();

            	    state._fsp--;


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

            match(input,18,FOLLOW_18_in_structdef242); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "structdef"


    // $ANTLR start "field"
    // grammars/TDL.g:27:1: field : ( 'optional' )? type identifier ';' ;
    public final void field() throws RecognitionException {
        try {
            // grammars/TDL.g:28:5: ( ( 'optional' )? type identifier ';' )
            // grammars/TDL.g:28:7: ( 'optional' )? type identifier ';'
            {
            // grammars/TDL.g:28:7: ( 'optional' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==19) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // grammars/TDL.g:28:7: 'optional'
                    {
                    match(input,19,FOLLOW_19_in_field254); 

                    }
                    break;

            }

            pushFollow(FOLLOW_type_in_field257);
            type();

            state._fsp--;

            pushFollow(FOLLOW_identifier_in_field259);
            identifier();

            state._fsp--;

            match(input,20,FOLLOW_20_in_field261); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "field"


    // $ANTLR start "sequencedef"
    // grammars/TDL.g:29:1: sequencedef : 'sequence' '<' type '>' identifier ';' ;
    public final void sequencedef() throws RecognitionException {
        try {
            // grammars/TDL.g:30:5: ( 'sequence' '<' type '>' identifier ';' )
            // grammars/TDL.g:30:7: 'sequence' '<' type '>' identifier ';'
            {
            match(input,21,FOLLOW_21_in_sequencedef273); 
            match(input,22,FOLLOW_22_in_sequencedef275); 
            pushFollow(FOLLOW_type_in_sequencedef277);
            type();

            state._fsp--;

            match(input,23,FOLLOW_23_in_sequencedef279); 
            pushFollow(FOLLOW_identifier_in_sequencedef281);
            identifier();

            state._fsp--;

            match(input,20,FOLLOW_20_in_sequencedef283); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "sequencedef"


    // $ANTLR start "uniondef"
    // grammars/TDL.g:31:1: uniondef : 'union' identifier '{' ( casedef )+ '}' ;
    public final void uniondef() throws RecognitionException {
        try {
            // grammars/TDL.g:32:5: ( 'union' identifier '{' ( casedef )+ '}' )
            // grammars/TDL.g:32:7: 'union' identifier '{' ( casedef )+ '}'
            {
            match(input,24,FOLLOW_24_in_uniondef295); 
            pushFollow(FOLLOW_identifier_in_uniondef297);
            identifier();

            state._fsp--;

            match(input,17,FOLLOW_17_in_uniondef299); 
            // grammars/TDL.g:32:30: ( casedef )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==25) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // grammars/TDL.g:32:30: casedef
            	    {
            	    pushFollow(FOLLOW_casedef_in_uniondef301);
            	    casedef();

            	    state._fsp--;


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

            match(input,18,FOLLOW_18_in_uniondef304); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "uniondef"


    // $ANTLR start "casedef"
    // grammars/TDL.g:33:1: casedef : 'case' number ':' type identifier ';' ;
    public final void casedef() throws RecognitionException {
        try {
            // grammars/TDL.g:34:5: ( 'case' number ':' type identifier ';' )
            // grammars/TDL.g:34:7: 'case' number ':' type identifier ';'
            {
            match(input,25,FOLLOW_25_in_casedef317); 
            pushFollow(FOLLOW_number_in_casedef319);
            number();

            state._fsp--;

            match(input,26,FOLLOW_26_in_casedef321); 
            pushFollow(FOLLOW_type_in_casedef323);
            type();

            state._fsp--;

            pushFollow(FOLLOW_identifier_in_casedef325);
            identifier();

            state._fsp--;

            match(input,20,FOLLOW_20_in_casedef327); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "casedef"


    // $ANTLR start "forwarddef"
    // grammars/TDL.g:35:1: forwarddef : 'typedef' identifier ';' ;
    public final void forwarddef() throws RecognitionException {
        try {
            // grammars/TDL.g:36:5: ( 'typedef' identifier ';' )
            // grammars/TDL.g:36:7: 'typedef' identifier ';'
            {
            match(input,27,FOLLOW_27_in_forwarddef339); 
            pushFollow(FOLLOW_identifier_in_forwarddef341);
            identifier();

            state._fsp--;

            match(input,20,FOLLOW_20_in_forwarddef343); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "forwarddef"


    // $ANTLR start "messagedef"
    // grammars/TDL.g:38:1: messagedef : 'message' identifier '=' ( seven | 'ID' number ) '{' ( field )* '}' ;
    public final void messagedef() throws RecognitionException {
        try {
            // grammars/TDL.g:39:5: ( 'message' identifier '=' ( seven | 'ID' number ) '{' ( field )* '}' )
            // grammars/TDL.g:39:7: 'message' identifier '=' ( seven | 'ID' number ) '{' ( field )* '}'
            {
            match(input,28,FOLLOW_28_in_messagedef359); 
            pushFollow(FOLLOW_identifier_in_messagedef361);
            identifier();

            state._fsp--;

            match(input,15,FOLLOW_15_in_messagedef363); 
            // grammars/TDL.g:39:32: ( seven | 'ID' number )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==SEVEN) ) {
                alt7=1;
            }
            else if ( (LA7_0==16) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // grammars/TDL.g:39:34: seven
                    {
                    pushFollow(FOLLOW_seven_in_messagedef367);
                    seven();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // grammars/TDL.g:39:42: 'ID' number
                    {
                    match(input,16,FOLLOW_16_in_messagedef371); 
                    pushFollow(FOLLOW_number_in_messagedef373);
                    number();

                    state._fsp--;


                    }
                    break;

            }

            match(input,17,FOLLOW_17_in_messagedef377); 
            // grammars/TDL.g:39:60: ( field )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ID||LA8_0==8||(LA8_0>=11 && LA8_0<=13)||LA8_0==19) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // grammars/TDL.g:39:60: field
            	    {
            	    pushFollow(FOLLOW_field_in_messagedef379);
            	    field();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match(input,18,FOLLOW_18_in_messagedef382); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "messagedef"


    // $ANTLR start "protocol"
    // grammars/TDL.g:40:1: protocol : 'protocol' identifier '=' 'ID' number '{' ( protocolelement )* '}' ;
    public final void protocol() throws RecognitionException {
        try {
            // grammars/TDL.g:41:5: ( 'protocol' identifier '=' 'ID' number '{' ( protocolelement )* '}' )
            // grammars/TDL.g:41:7: 'protocol' identifier '=' 'ID' number '{' ( protocolelement )* '}'
            {
            match(input,29,FOLLOW_29_in_protocol394); 
            pushFollow(FOLLOW_identifier_in_protocol396);
            identifier();

            state._fsp--;

            match(input,15,FOLLOW_15_in_protocol398); 
            match(input,16,FOLLOW_16_in_protocol400); 
            pushFollow(FOLLOW_number_in_protocol402);
            number();

            state._fsp--;

            match(input,17,FOLLOW_17_in_protocol404); 
            // grammars/TDL.g:41:49: ( protocolelement )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==14||LA9_0==21||LA9_0==24||(LA9_0>=27 && LA9_0<=28)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // grammars/TDL.g:41:49: protocolelement
            	    {
            	    pushFollow(FOLLOW_protocolelement_in_protocol406);
            	    protocolelement();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            match(input,18,FOLLOW_18_in_protocol409); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "protocol"


    // $ANTLR start "protocolelement"
    // grammars/TDL.g:42:1: protocolelement : ( typedef | messagedef );
    public final void protocolelement() throws RecognitionException {
        try {
            // grammars/TDL.g:43:5: ( typedef | messagedef )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==14||LA10_0==21||LA10_0==24||LA10_0==27) ) {
                alt10=1;
            }
            else if ( (LA10_0==28) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // grammars/TDL.g:43:7: typedef
                    {
                    pushFollow(FOLLOW_typedef_in_protocolelement421);
                    typedef();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // grammars/TDL.g:43:17: messagedef
                    {
                    pushFollow(FOLLOW_messagedef_in_protocolelement425);
                    messagedef();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "protocolelement"


    // $ANTLR start "specification"
    // grammars/TDL.g:44:1: specification : ( protocol | messagedef | structdef )* EOF ;
    public final void specification() throws RecognitionException {
        try {
            // grammars/TDL.g:45:5: ( ( protocol | messagedef | structdef )* EOF )
            // grammars/TDL.g:45:7: ( protocol | messagedef | structdef )* EOF
            {
            // grammars/TDL.g:45:7: ( protocol | messagedef | structdef )*
            loop11:
            do {
                int alt11=4;
                switch ( input.LA(1) ) {
                case 29:
                    {
                    alt11=1;
                    }
                    break;
                case 28:
                    {
                    alt11=2;
                    }
                    break;
                case 14:
                    {
                    alt11=3;
                    }
                    break;

                }

                switch (alt11) {
            	case 1 :
            	    // grammars/TDL.g:45:8: protocol
            	    {
            	    pushFollow(FOLLOW_protocol_in_specification438);
            	    protocol();

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    // grammars/TDL.g:45:19: messagedef
            	    {
            	    pushFollow(FOLLOW_messagedef_in_specification442);
            	    messagedef();

            	    state._fsp--;


            	    }
            	    break;
            	case 3 :
            	    // grammars/TDL.g:45:32: structdef
            	    {
            	    pushFollow(FOLLOW_structdef_in_specification446);
            	    structdef();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            match(input,EOF,FOLLOW_EOF_in_specification450); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "specification"

    // Delegated rules


 

    public static final BitSet FOLLOW_ID_in_identifier102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEVEN_in_seven113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_number127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_type142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_type146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_type151 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_type153 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_type155 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_identifier_in_type157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_primitiveType0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structdef_in_typedef198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sequencedef_in_typedef202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_uniondef_in_typedef206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forwarddef_in_typedef210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_structdef222 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_identifier_in_structdef224 = new BitSet(new long[]{0x0000000000028000L});
    public static final BitSet FOLLOW_15_in_structdef228 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_structdef230 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_number_in_structdef232 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_structdef237 = new BitSet(new long[]{0x0000000000083920L});
    public static final BitSet FOLLOW_field_in_structdef239 = new BitSet(new long[]{0x00000000000C3920L});
    public static final BitSet FOLLOW_18_in_structdef242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_field254 = new BitSet(new long[]{0x0000000000083920L});
    public static final BitSet FOLLOW_type_in_field257 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_identifier_in_field259 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_field261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_sequencedef273 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_sequencedef275 = new BitSet(new long[]{0x0000000000083920L});
    public static final BitSet FOLLOW_type_in_sequencedef277 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_sequencedef279 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_identifier_in_sequencedef281 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_sequencedef283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_uniondef295 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_identifier_in_uniondef297 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_uniondef299 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_casedef_in_uniondef301 = new BitSet(new long[]{0x0000000002040000L});
    public static final BitSet FOLLOW_18_in_uniondef304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_casedef317 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_number_in_casedef319 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_casedef321 = new BitSet(new long[]{0x0000000000083920L});
    public static final BitSet FOLLOW_type_in_casedef323 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_identifier_in_casedef325 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_casedef327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_forwarddef339 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_identifier_in_forwarddef341 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_forwarddef343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_messagedef359 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_identifier_in_messagedef361 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_messagedef363 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_seven_in_messagedef367 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_16_in_messagedef371 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_number_in_messagedef373 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_messagedef377 = new BitSet(new long[]{0x00000000000C3920L});
    public static final BitSet FOLLOW_field_in_messagedef379 = new BitSet(new long[]{0x00000000000C3920L});
    public static final BitSet FOLLOW_18_in_messagedef382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_protocol394 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_identifier_in_protocol396 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_protocol398 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_protocol400 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_number_in_protocol402 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_protocol404 = new BitSet(new long[]{0x0000000019244000L});
    public static final BitSet FOLLOW_protocolelement_in_protocol406 = new BitSet(new long[]{0x0000000019244000L});
    public static final BitSet FOLLOW_18_in_protocol409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typedef_in_protocolelement421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_messagedef_in_protocolelement425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_protocol_in_specification438 = new BitSet(new long[]{0x0000000039204000L});
    public static final BitSet FOLLOW_messagedef_in_specification442 = new BitSet(new long[]{0x0000000039204000L});
    public static final BitSet FOLLOW_structdef_in_specification446 = new BitSet(new long[]{0x0000000039204000L});
    public static final BitSet FOLLOW_EOF_in_specification450 = new BitSet(new long[]{0x0000000000000002L});

}