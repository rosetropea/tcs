 /**             .'  '.'  `.
              _.-|  o | o  |-._
            .~   `.__.'.__.'^  ~.
          .~     ^  /   \  ^     ~.
          \-._^   ^|     |    ^_.-/
          `\  `-._  \___/ ^_.-' /'        ^
            `\_   `--...--'   /'         / \
               `-.._______..-'          /  /
                  __/   \__            /__/
                .'^   ^    `.      .'`|  |
              .'    ^     ^  `.__.'^ . /
             .' ^ .    ^   .    ^  .'  
            /    /        ^ \'.__.'
           |  ^ /|   ^      |
            \   \|^      ^  |  
             `\^ |        ^ |
               `~|    ^     |
                 |  ^     ^ |
                 \^         /
                  `.    ^ .'
                   : ^    ; 
           .-~~~~~~   |  ^ ~~~~~~-.
          /   ^     ^ |    ^       \
          \^     ^   / \  ^     ^  /
           `~~~~~~~~'   `~~~~~~~~~'*/




import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class LexicalAnalyser {

    public static List<Token> analyse(String sourceCode) throws LexicalException {

        /**
           Trim white space in array 
           Separate into array of strings 
           Split by whitespace and by terminals? 
           Check For (String str | array[])
           if statment for all terminals
           if (str = terminal) then (Add that Token.type(str) to List<Token>)
           else then (str is variable identify ID, Num, String etc (Add to Token.type(variable) to Lis<Token)>))
           return List<Token>;
           */
          List<Token> tokenList = new ArrayList();
          String [] array = sourceCode.split("((?<=\\))|(?=\\)))|((?<=\\()|(?=\\())|((?<=\\+)|(?=\\+))|((?<=\\*)|(?=\\*))|((?<=\\/)|(?=\\/))|((?<=\\-)|(?=\\-))|((?<=\\%)|(?=\\%))|((?<=\")|(?=\"))|((?<=\')|(?=\'))|((?<=\\})|(?=\\}))|((?<=\\{)|(?=\\{))|((?<=\\&)|(?=\\&))|((?<=\\|)|(?=\\|))|((?<=\\=)|(?=\\=))|((?<=\\!)|(?=\\!))|((?<=\\>)|(?=\\>))|((?<=\\<)|(?=\\<))|((?<=\\;)|(?=\\;))|\\s+");
          for (int a=0; a<array.length; ++a){
              array[a].trim();
            }
          String[] removedNull = Arrays.stream(array)
                .filter(value ->
                        value != null && value.length() > 0
                )
                .toArray(size -> new String[size]);
          System.out.println(Arrays.toString(removedNull));
          for(int i=0; i<removedNull.length; ++i){
            if(false){
                /**
                   Check if double terminal i.e. == != <= >= etc
                   if does find go to double token converter
                   */
                /**
                   Go to token converter
                   */
            
            }
            else if(removedNull[i].equals("\'")){
                
                tokenList.add(tokenID(removedNull[i]));
                tokenList.add(variableID(removedNull[i+1]));
                tokenList.add(tokenID(removedNull[i+2]));
               
                i += 2;
            }
            else if(removedNull[i].equals("\"")){
                
                tokenList.add(tokenID(removedNull[i]));
                tokenList.add(stringID(removedNull[i+1]));
                tokenList.add(tokenID(removedNull[i+2]));
               
                i += 2;
            }
            else if(removedNull[i].equals(" ")){
                /**
                   Do nothing
                   */
            }
            else{
                throw new LexicalException();
            }
            
            }
          for(Token t : tokenList)
            System.out.println(t);
            return null;
    }

    public static Token tokenID(String str){
        
        if(str.equals("+")){
            Token.TokenType type = Token.TokenType.PLUS;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("-")){
            Token.TokenType type = Token.TokenType.MINUS;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("*")){
            Token.TokenType type = Token.TokenType.TIMES;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("/")){
            Token.TokenType type = Token.TokenType.DIVIDE;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("%")){
            Token.TokenType type = Token.TokenType.MOD;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("=")){
            Token.TokenType type = Token.TokenType.ASSIGN;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("<")){
            Token.TokenType type = Token.TokenType.LT;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals(">")){
            Token.TokenType type = Token.TokenType.GT;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("(")){
            Token.TokenType type = Token.TokenType.LPAREN;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals(")")){
            Token.TokenType type = Token.TokenType.RPAREN;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("{")){
            Token.TokenType type = Token.TokenType.LBRACE;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("}")){
            Token.TokenType type = Token.TokenType.RBRACE;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals(";")){
            Token.TokenType type = Token.TokenType.SEMICOLON;
            Token token = new Token(type, str);
            return token;
        }
       else if(str.equals("public")){
            Token.TokenType type = Token.TokenType.PUBLIC;
            Token token = new Token(type, str);
            return token;
        }
       else if(str.equals("class")){
            Token.TokenType type = Token.TokenType.CLASS;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("static")){
            Token.TokenType type = Token.TokenType.STATIC;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("void")){
            Token.TokenType type = Token.TokenType.VOID;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("main")){
            Token.TokenType type = Token.TokenType.MAIN;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("String[]")){
            Token.TokenType type = Token.TokenType.STRINGARR;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("args")){
            Token.TokenType type = Token.TokenType.ARGS;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("int") || str.equals("char") || str.equals("boolean")){
            Token.TokenType type = Token.TokenType.TYPE;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("System.out.println")){
            Token.TokenType type = Token.TokenType.PRINT;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("while")){
            Token.TokenType type = Token.TokenType.WHILE;
            Token token = new Token(type, str);
            return token;
       }
       else if(str.equals("for")){
            Token.TokenType type = Token.TokenType.FOR;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("if")){
            Token.TokenType type = Token.TokenType.IF;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("else")){
            Token.TokenType type = Token.TokenType.ELSE;
            Token token = new Token(type, str);
            return token;
        }
                else if(str.equals("\"")){
            Token.TokenType type = Token.TokenType.DQUOTE;
            Token token = new Token(type, str);
            return token;
        }
                else if(str.equals("\'")){
            Token.TokenType type = Token.TokenType.SQUOTE;
            Token token = new Token(type, str);
            return token;
        }
        return null;
    }
    /**
        ID, NUM
       */
    public static Token variableID(String str){
         if(str.equals("true")){
            Token.TokenType type = Token.TokenType.TRUE;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.equals("false")){
            Token.TokenType type = Token.TokenType.FALSE;
            Token token = new Token(type, str);
            return token;
        }
        else if(str.length() == 1){
            Token.TokenType type = Token.TokenType.CHARLIT;
            Token token = new Token(type, str);
            return token;
        }
        return null;
    }
    public static Token stringID(String str){
        if(str.length() == 1){
            Token.TokenType type = Token.TokenType.STRINGLIT;
            Token token = new Token(type, str);
            return token;
        }
        return null;
    }
}
