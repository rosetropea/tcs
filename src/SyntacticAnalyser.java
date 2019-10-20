import java.util.List;
import java.util.Deque;
import java.util.LinkedList;

public class SyntacticAnalyser {

    Deque<StackToken> stack = new LinkedList<>();
    LinkedList<ParseTree.TreeNode> metadata = new LinkedList();
    ParseTree<Token> parseTree = new ParseTree();
         
    public ParseTree<Token> parse(List<Token> tokens) throws SyntaxException {
        ParseTree.TreeNode treenode = parseTree.new TreeNode(ParseTree.Label.prog, null);
        parseTree.setRoot(treenode);
        stack.add(StackToken.prog);
        metadata.add(treenode);
        
        //  METHOD PLAN:
        // Start at <<prog>>
        // pop to tree
        // add tokens realted to prog in reverse i.e. }} <<los>>
        // Check off each token until next <<???>>
        // when it reaches <<???>> repeat from 1 with new <<prog>>
        for (int i = 0; i < tokens.size(); ++i) {
        
        
            //PLUS, MINUS, TIMES, DIVIDE, MOD, ASSIGN, EQUAL, NEQUAL, LT, LE, GT, GE, LPAREN, RPAREN, LBRACE, RBRACE, AND, OR,
            //SEMICOLON, PUBLIC, CLASS, STATIC, VOID, MAIN, STRINGARR, ARGS, TYPE, PRINT, WHILE, FOR, IF, ELSE, DQUOTE,
            //SQUOTE, ID, NUM, CHARLIT, TRUE, FALSE, STRINGLIT,
         if(stack.getLast().equals(StackToken.PLUS) || stack.getLast().equals(StackToken.MINUS)|| stack.getLast().equals(StackToken.TIMES)|| stack.getLast().equals(StackToken.DIVIDE)
         || stack.getLast().equals(StackToken.MOD)|| stack.getLast().equals(StackToken.ASSIGN)|| stack.getLast().equals(StackToken.EQUAL)|| stack.getLast().equals(StackToken.NEQUAL)
         || stack.getLast().equals(StackToken.LT)|| stack.getLast().equals(StackToken.LE)||stack.getLast().equals(StackToken.GT) || stack.getLast().equals(StackToken.GE)
         || stack.getLast().equals(StackToken.LPAREN)|| stack.getLast().equals(StackToken.RPAREN)|| stack.getLast().equals(StackToken.LBRACE)|| stack.getLast().equals(StackToken.RBRACE)
         || stack.getLast().equals(StackToken.AND)|| stack.getLast().equals(StackToken.OR)|| stack.getLast().equals(StackToken.SEMICOLON)|| stack.getLast().equals(StackToken.PUBLIC)
         || stack.getLast().equals(StackToken.CLASS) || stack.getLast().equals(StackToken.STATIC)|| stack.getLast().equals(StackToken.VOID)|| stack.getLast().equals(StackToken.MAIN)
         || stack.getLast().equals(StackToken.STRINGARR)|| stack.getLast().equals(StackToken.ARGS)|| stack.getLast().equals(StackToken.TYPE)|| stack.getLast().equals(StackToken.PRINT)
         || stack.getLast().equals(StackToken.WHILE)|| stack.getLast().equals(StackToken.FOR)|| stack.getLast().equals(StackToken.IF) || stack.getLast().equals(StackToken.ELSE)
         || stack.getLast().equals(StackToken.DQUOTE)|| stack.getLast().equals(StackToken.SQUOTE)|| stack.getLast().equals(StackToken.ID)|| stack.getLast().equals(StackToken.NUM)
         || stack.getLast().equals(StackToken.CHARLIT)|| stack.getLast().equals(StackToken.TRUE)|| stack.getLast().equals(StackToken.FALSE)|| stack.getLast().equals(StackToken.STRINGLIT)){
            //Compare to token list
            tokenCompare(stack.getLast(), tokens.get(i), metadata.getLast());
         }
         else if(stack.getLast().equals(StackToken.prog) || stack.getLast().equals(StackToken.los)|| stack.getLast().equals(StackToken.stat)|| stack.getLast().equals(StackToken.whilestat)
         || stack.getLast().equals(StackToken.forstat)|| stack.getLast().equals(StackToken.forstart)|| stack.getLast().equals(StackToken.forarith)|| stack.getLast().equals(StackToken.ifstat)
         || stack.getLast().equals(StackToken.elseifstat)|| stack.getLast().equals(StackToken.elseorelseif)||stack.getLast().equals(StackToken.possif) || stack.getLast().equals(StackToken.assign)
         || stack.getLast().equals(StackToken.decl)|| stack.getLast().equals(StackToken.possassign)|| stack.getLast().equals(StackToken.print)|| stack.getLast().equals(StackToken.type)
         || stack.getLast().equals(StackToken.expr)|| stack.getLast().equals(StackToken.boolexprprime)|| stack.getLast().equals(StackToken.boolop)|| stack.getLast().equals(StackToken.boollog)
         || stack.getLast().equals(StackToken.relexpr) || stack.getLast().equals(StackToken.relexprprime)|| stack.getLast().equals(StackToken.relop)|| stack.getLast().equals(StackToken.arithexpr)
         || stack.getLast().equals(StackToken.arithexprprime)|| stack.getLast().equals(StackToken.term)|| stack.getLast().equals(StackToken.termprime)|| stack.getLast().equals(StackToken.factor)
         || stack.getLast().equals(StackToken.printexpr)|| stack.getLast().equals(StackToken.num)|| stack.getLast().equals(StackToken.charexpr) || stack.getLast().equals(StackToken.stringlit)
         || stack.getLast().equals(StackToken.boolconst)|| stack.getLast().equals(StackToken.epsilon)|| stack.getLast().equals(StackToken.terminal)){
            branchAdd(stack.getLast(), tokens.get(i), metadata.getLast());
            }
         else {
            throw new SyntaxException();
            }
        }
        return new ParseTree<Token>();

    }
    
    
    public void branchAdd(StackToken t, Token input, ParseTree.TreeNode tree){
        //Register what token it is and then add the tokens associated to it to the stack
        
        if(t == StackToken.prog && input.getType() == Token.TokenType.PUBLIC){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.prog, tree);
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.RBRACE);
            stack.add(StackToken.RBRACE);
            stack.add(StackToken.los);
            metadata.add(child);
            stack.add(StackToken.LBRACE);
            stack.add(StackToken.RPAREN);
            stack.add(StackToken.ARGS);
            stack.add(StackToken.STRINGARR);
            stack.add(StackToken.LPAREN);
            stack.add(StackToken.MAIN);
            stack.add(StackToken.VOID);
            stack.add(StackToken.STATIC);
            stack.add(StackToken.PUBLIC);
            stack.add(StackToken.LBRACE);
            stack.add(StackToken.ID);
            metadata.add(child);
            stack.add(StackToken.CLASS);
            stack.add(StackToken.PUBLIC);
                                }
        else if(t.equals("los") && input.getType() == Token.TokenType.ID){
            
        }
    }
    public void tokenCompare(StackToken t, Token input, ParseTree.TreeNode tree){
    
    
    }
    
    private enum StackToken{
        PLUS, MINUS, TIMES, DIVIDE, MOD, ASSIGN, EQUAL, NEQUAL, LT, LE, GT, GE, LPAREN, RPAREN, LBRACE, RBRACE, AND, OR,
        SEMICOLON, PUBLIC, CLASS, STATIC, VOID, MAIN, STRINGARR, ARGS, TYPE, PRINT, WHILE, FOR, IF, ELSE, DQUOTE,
        SQUOTE, ID, NUM, CHARLIT, TRUE, FALSE, STRINGLIT, prog, los, stat, whilestat, forstat, forstart, forarith, ifstat, 
        elseifstat, elseorelseif, possif, assign, decl, possassign, print, type, expr, boolexprprime, boolop, booleq, boollog, 
        relexpr, relexprprime, relop, arithexpr, arithexprprime, term, termprime, factor, printexpr, num, charexpr, stringlit, 
        boolconst, epsilon, terminal
    }

}
