import java.util.Collections;
import java.util.List;
import java.util.Arrays;

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

          String [] array = sourceCode.split("((?<=\\))|(?=\\)))|((?<=\\()|(?=\\())|((?<=\\+)|(?=\\+))|((?<=\\*)|(?=\\*))|((?<=\\/)|(?=\\/))|((?<=\\-)|(?=\\-))|((?<=\\%)|(?=\\%))|((?<=\")|(?=\"))|((?<=\')|(?=\'))((?<=\\})|(?=\\}))|((?<=\\{)|(?=\\{))|((?<=\\&)|(?=\\&))|((?<=\\|)|(?=\\|))|((?<=\\=)|(?=\\=))|((?<=\\!)|(?=\\!))|((?<=\\>)|(?=\\>))|((?<=\\<)|(?=\\<))|((?<=\\;)|(?=\\;))|\\s+");
          System.out.println(Arrays.toString(array));
            return null;
    }
    
    public static void stringCheck(String str){
       /**
          function check each character in string
          While (i = 0, i < str.length, i++){
           if (str.charAt(i) == + / * - % ( ) { } = & " '
              
           }
           "String A BEcaue "
           Check if is a variable
           when runs into a terminal it will split the string and tokenise the variable before the split 
           and the terminal used in the split. After tokenise continue analysing the string
          */
         int number=1+0;
         
       }

}
