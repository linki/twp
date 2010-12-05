package twp.generator;
// $ANTLR 3.2 Sep 23, 2009 12:02:23 grammars/TDL.g 2010-11-21 00:26:46

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class TDLLexer extends Lexer {
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

    public TDLLexer() {;} 
    public TDLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TDLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "grammars/TDL.g"; }

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:3:7: ( 'any' )
            // grammars/TDL.g:3:9: 'any'
            {
            match("any"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:4:7: ( 'defined' )
            // grammars/TDL.g:4:9: 'defined'
            {
            match("defined"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:5:7: ( 'by' )
            // grammars/TDL.g:5:9: 'by'
            {
            match("by"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:6:7: ( 'int' )
            // grammars/TDL.g:6:9: 'int'
            {
            match("int"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:7:7: ( 'string' )
            // grammars/TDL.g:7:9: 'string'
            {
            match("string"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:8:7: ( 'binary' )
            // grammars/TDL.g:8:9: 'binary'
            {
            match("binary"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:9:7: ( 'struct' )
            // grammars/TDL.g:9:9: 'struct'
            {
            match("struct"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:10:7: ( '=' )
            // grammars/TDL.g:10:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:11:7: ( 'ID' )
            // grammars/TDL.g:11:9: 'ID'
            {
            match("ID"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:12:7: ( '{' )
            // grammars/TDL.g:12:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:13:7: ( '}' )
            // grammars/TDL.g:13:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:14:7: ( 'optional' )
            // grammars/TDL.g:14:9: 'optional'
            {
            match("optional"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:15:7: ( ';' )
            // grammars/TDL.g:15:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:16:7: ( 'sequence' )
            // grammars/TDL.g:16:9: 'sequence'
            {
            match("sequence"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:17:7: ( '<' )
            // grammars/TDL.g:17:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:18:7: ( '>' )
            // grammars/TDL.g:18:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:19:7: ( 'union' )
            // grammars/TDL.g:19:9: 'union'
            {
            match("union"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:20:7: ( 'case' )
            // grammars/TDL.g:20:9: 'case'
            {
            match("case"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:21:7: ( ':' )
            // grammars/TDL.g:21:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:22:7: ( 'typedef' )
            // grammars/TDL.g:22:9: 'typedef'
            {
            match("typedef"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:23:7: ( 'message' )
            // grammars/TDL.g:23:9: 'message'
            {
            match("message"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:24:7: ( 'protocol' )
            // grammars/TDL.g:24:9: 'protocol'
            {
            match("protocol"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:12:2: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // grammars/TDL.g:12:4: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // grammars/TDL.g:12:4: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||(LA1_0>='\f' && LA1_0<='\r')||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // grammars/TDL.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHITESPACE"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:15:2: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // grammars/TDL.g:15:4: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // grammars/TDL.g:15:9: ( options {greedy=false; } : . )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='*') ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1=='/') ) {
                        alt2=2;
                    }
                    else if ( ((LA2_1>='\u0000' && LA2_1<='.')||(LA2_1>='0' && LA2_1<='\uFFFF')) ) {
                        alt2=1;
                    }


                }
                else if ( ((LA2_0>='\u0000' && LA2_0<=')')||(LA2_0>='+' && LA2_0<='\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // grammars/TDL.g:15:36: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match("*/"); 

            _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:18:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // grammars/TDL.g:18:4: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // grammars/TDL.g:18:28: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // grammars/TDL.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "SEVEN"
    public final void mSEVEN() throws RecognitionException {
        try {
            // grammars/TDL.g:22:2: ( '0' .. '7' )
            // grammars/TDL.g:22:4: '0' .. '7'
            {
            matchRange('0','7'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "SEVEN"

    // $ANTLR start "SEVENUP"
    public final void mSEVENUP() throws RecognitionException {
        try {
            int _type = SEVENUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:25:2: ( SEVEN )
            // grammars/TDL.g:25:4: SEVEN
            {
            mSEVEN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEVENUP"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // grammars/TDL.g:28:2: ( ( SEVEN | '8' | '9' )+ )
            // grammars/TDL.g:28:4: ( SEVEN | '8' | '9' )+
            {
            // grammars/TDL.g:28:4: ( SEVEN | '8' | '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // grammars/TDL.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    public void mTokens() throws RecognitionException {
        // grammars/TDL.g:1:8: ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | WHITESPACE | COMMENT | ID | SEVENUP | INT )
        int alt5=27;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // grammars/TDL.g:1:10: T__10
                {
                mT__10(); 

                }
                break;
            case 2 :
                // grammars/TDL.g:1:16: T__11
                {
                mT__11(); 

                }
                break;
            case 3 :
                // grammars/TDL.g:1:22: T__12
                {
                mT__12(); 

                }
                break;
            case 4 :
                // grammars/TDL.g:1:28: T__13
                {
                mT__13(); 

                }
                break;
            case 5 :
                // grammars/TDL.g:1:34: T__14
                {
                mT__14(); 

                }
                break;
            case 6 :
                // grammars/TDL.g:1:40: T__15
                {
                mT__15(); 

                }
                break;
            case 7 :
                // grammars/TDL.g:1:46: T__16
                {
                mT__16(); 

                }
                break;
            case 8 :
                // grammars/TDL.g:1:52: T__17
                {
                mT__17(); 

                }
                break;
            case 9 :
                // grammars/TDL.g:1:58: T__18
                {
                mT__18(); 

                }
                break;
            case 10 :
                // grammars/TDL.g:1:64: T__19
                {
                mT__19(); 

                }
                break;
            case 11 :
                // grammars/TDL.g:1:70: T__20
                {
                mT__20(); 

                }
                break;
            case 12 :
                // grammars/TDL.g:1:76: T__21
                {
                mT__21(); 

                }
                break;
            case 13 :
                // grammars/TDL.g:1:82: T__22
                {
                mT__22(); 

                }
                break;
            case 14 :
                // grammars/TDL.g:1:88: T__23
                {
                mT__23(); 

                }
                break;
            case 15 :
                // grammars/TDL.g:1:94: T__24
                {
                mT__24(); 

                }
                break;
            case 16 :
                // grammars/TDL.g:1:100: T__25
                {
                mT__25(); 

                }
                break;
            case 17 :
                // grammars/TDL.g:1:106: T__26
                {
                mT__26(); 

                }
                break;
            case 18 :
                // grammars/TDL.g:1:112: T__27
                {
                mT__27(); 

                }
                break;
            case 19 :
                // grammars/TDL.g:1:118: T__28
                {
                mT__28(); 

                }
                break;
            case 20 :
                // grammars/TDL.g:1:124: T__29
                {
                mT__29(); 

                }
                break;
            case 21 :
                // grammars/TDL.g:1:130: T__30
                {
                mT__30(); 

                }
                break;
            case 22 :
                // grammars/TDL.g:1:136: T__31
                {
                mT__31(); 

                }
                break;
            case 23 :
                // grammars/TDL.g:1:142: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;
            case 24 :
                // grammars/TDL.g:1:153: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 25 :
                // grammars/TDL.g:1:161: ID
                {
                mID(); 

                }
                break;
            case 26 :
                // grammars/TDL.g:1:164: SEVENUP
                {
                mSEVENUP(); 

                }
                break;
            case 27 :
                // grammars/TDL.g:1:172: INT
                {
                mINT(); 

                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\1\uffff\5\26\1\uffff\1\26\2\uffff\1\26\3\uffff\2\26\1\uffff\3\26"+
        "\3\uffff\1\47\1\uffff\2\26\1\52\4\26\1\57\6\26\1\uffff\1\66\1\26"+
        "\1\uffff\1\26\1\71\2\26\1\uffff\6\26\1\uffff\2\26\1\uffff\5\26\1"+
        "\112\11\26\1\124\1\uffff\4\26\1\131\1\132\1\133\2\26\1\uffff\3\26"+
        "\1\141\3\uffff\2\26\1\144\1\145\1\26\1\uffff\1\147\1\150\2\uffff"+
        "\1\151\3\uffff";
    static final String DFA5_eofS =
        "\152\uffff";
    static final String DFA5_minS =
        "\1\11\1\156\1\145\1\151\1\156\1\145\1\uffff\1\104\2\uffff\1\160"+
        "\3\uffff\1\156\1\141\1\uffff\1\171\1\145\1\162\3\uffff\1\60\1\uffff"+
        "\1\171\1\146\1\60\1\156\1\164\1\162\1\161\1\60\1\164\1\151\1\163"+
        "\1\160\1\163\1\157\1\uffff\1\60\1\151\1\uffff\1\141\1\60\1\151\1"+
        "\165\1\uffff\1\151\1\157\2\145\1\163\1\164\1\uffff\1\156\1\162\1"+
        "\uffff\1\156\1\143\1\145\1\157\1\156\1\60\1\144\1\141\1\157\1\145"+
        "\1\171\1\147\1\164\2\156\1\60\1\uffff\1\145\1\147\1\143\1\144\3"+
        "\60\1\143\1\141\1\uffff\1\146\1\145\1\157\1\60\3\uffff\1\145\1\154"+
        "\2\60\1\154\1\uffff\2\60\2\uffff\1\60\3\uffff";
    static final String DFA5_maxS =
        "\1\175\1\156\1\145\1\171\1\156\1\164\1\uffff\1\104\2\uffff\1\160"+
        "\3\uffff\1\156\1\141\1\uffff\1\171\1\145\1\162\3\uffff\1\71\1\uffff"+
        "\1\171\1\146\1\172\1\156\1\164\1\162\1\161\1\172\1\164\1\151\1\163"+
        "\1\160\1\163\1\157\1\uffff\1\172\1\151\1\uffff\1\141\1\172\2\165"+
        "\1\uffff\1\151\1\157\2\145\1\163\1\164\1\uffff\1\156\1\162\1\uffff"+
        "\1\156\1\143\1\145\1\157\1\156\1\172\1\144\1\141\1\157\1\145\1\171"+
        "\1\147\1\164\2\156\1\172\1\uffff\1\145\1\147\1\143\1\144\3\172\1"+
        "\143\1\141\1\uffff\1\146\1\145\1\157\1\172\3\uffff\1\145\1\154\2"+
        "\172\1\154\1\uffff\2\172\2\uffff\1\172\3\uffff";
    static final String DFA5_acceptS =
        "\6\uffff\1\10\1\uffff\1\12\1\13\1\uffff\1\15\1\17\1\20\2\uffff\1"+
        "\23\3\uffff\1\27\1\30\1\31\1\uffff\1\33\16\uffff\1\32\2\uffff\1"+
        "\3\4\uffff\1\11\6\uffff\1\1\2\uffff\1\4\20\uffff\1\22\11\uffff\1"+
        "\21\4\uffff\1\6\1\5\1\7\5\uffff\1\2\2\uffff\1\24\1\25\1\uffff\1"+
        "\16\1\14\1\26";
    static final String DFA5_specialS =
        "\152\uffff}>";
    static final String[] DFA5_transitionS = {
            "\2\24\1\uffff\2\24\22\uffff\1\24\16\uffff\1\25\10\27\2\30\1"+
            "\20\1\13\1\14\1\6\1\15\2\uffff\10\26\1\7\21\26\4\uffff\1\26"+
            "\1\uffff\1\1\1\3\1\17\1\2\4\26\1\4\3\26\1\22\1\26\1\12\1\23"+
            "\2\26\1\5\1\21\1\16\5\26\1\10\1\uffff\1\11",
            "\1\31",
            "\1\32",
            "\1\34\17\uffff\1\33",
            "\1\35",
            "\1\37\16\uffff\1\36",
            "",
            "\1\40",
            "",
            "",
            "\1\41",
            "",
            "",
            "",
            "\1\42",
            "\1\43",
            "",
            "\1\44",
            "\1\45",
            "\1\46",
            "",
            "",
            "",
            "\12\30",
            "",
            "\1\50",
            "\1\51",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\56",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "\1\67",
            "",
            "\1\70",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "\1\72\13\uffff\1\73",
            "\1\74",
            "",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "",
            "\1\103",
            "\1\104",
            "",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "\1\134",
            "\1\135",
            "",
            "\1\136",
            "\1\137",
            "\1\140",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "",
            "",
            "",
            "\1\142",
            "\1\143",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "\1\146",
            "",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "",
            "",
            "\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
            "",
            "",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | WHITESPACE | COMMENT | ID | SEVENUP | INT );";
        }
    }
 

}