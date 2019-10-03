import java.util.List;
import java.util.Deque;
import java.util.LinkedList;

public class SyntacticAnalyser {

     Deque<Token> deque = new LinkedList<Token>(); 
     
    public ParseTree<Token> parse(List<Token> tokens) throws SyntaxException {
        
        //  METHOD PLAN:
        //  for (Token token : tokens)
        //  Get token
        //  Push it to the stack
        //  Get the next token
        //  if the next token is valid, push it to the stack
        //  else throw new SyntaxException
        //  System.out.print("Passed");
        
        for (Token t : tokens) {
           deque.push(t);
            if (deque.peekFirst() != null) {
            deque.peekFirst(); 
           }
        }
        
        
        return new ParseTree<Token>();

    }
    
    
    

}
