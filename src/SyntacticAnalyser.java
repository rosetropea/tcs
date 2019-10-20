import java.util.List;
import java.util.Deque;
import java.util.LinkedList;

public class SyntacticAnalyser {

             
    public static ParseTree<Token> parse(List<Token> tokens) throws SyntaxException {
        Deque<StackToken> stack = new LinkedList<>();
        LinkedList<ParseTree.TreeNode> metadata = new LinkedList();
        ParseTree<Token> parseTree = new ParseTree();
        ParseTree.TreeNode treenode = parseTree.new TreeNode(null, null);
        parseTree.setRoot(treenode);
        stack.add(StackToken.prog);
        metadata.add(treenode);
        
        //  METHOD PLAN:
        // Start at <<prog>>
        // pop to tree
        // add tokens realted to prog in reverse i.e. }} <<los>>
        // Check off each token until next <<???>>
        // when it reaches <<???>> repeat from 1 with new <<prog>>
        int i = 0;
        if(tokens.size() == 0){
            throw new SyntaxException();
        }
        while (i < tokens.size()-1) {
        
        
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
            StackToken temp = stack.getLast();
            if(tokenCompare(temp, tokens.get(i))){
                ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.prog, metadata.getLast());
                stack.removeLast();
                metadata.removeLast();
            }
            else{
             throw new SyntaxException();
            }
            ++i;
         }
         else if(stack.getLast() == StackToken.prog && tokens.get(i).getType() == Token.TokenType.PUBLIC){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.prog,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.RBRACE);
            metadata.add(child);
            stack.add(StackToken.RBRACE);
            metadata.add(child);
            stack.add(StackToken.los);
            metadata.add(child);
            stack.add(StackToken.LBRACE);
            metadata.add(child);
            stack.add(StackToken.RPAREN);
            metadata.add(child);
            stack.add(StackToken.ARGS);
            metadata.add(child);
            stack.add(StackToken.STRINGARR);
            metadata.add(child);
            stack.add(StackToken.LPAREN);
            metadata.add(child);
            stack.add(StackToken.MAIN);
            metadata.add(child);
            stack.add(StackToken.VOID);
            metadata.add(child);
            stack.add(StackToken.STATIC);
            metadata.add(child);
            stack.add(StackToken.PUBLIC);
            metadata.add(child);
            stack.add(StackToken.LBRACE);
            metadata.add(child);
            stack.add(StackToken.ID);
            metadata.add(child);
            stack.add(StackToken.CLASS);
            metadata.add(child);
            stack.add(StackToken.PUBLIC);
            metadata.add(child);
                                }
        else if(stack.getLast() == StackToken.los && (tokens.get(i).getType() == Token.TokenType.ID || tokens.get(i).getType() == Token.TokenType.TYPE || tokens.get(i).getType() == Token.TokenType.PRINT
        || tokens.get(i).getType() == Token.TokenType.WHILE||tokens.get(i).getType() == Token.TokenType.FOR||tokens.get(i).getType() == Token.TokenType.IF||tokens.get(i).getType() == Token.TokenType.SEMICOLON
        )){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.los, metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.los);
            metadata.add(child);
            stack.add(StackToken.stat);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.stat && tokens.get(i).getType() == Token.TokenType.ID){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.stat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.SEMICOLON);
            metadata.add(child);
            stack.add(StackToken.assign);
            metadata.add(child);
            
        }
        else if(stack.getLast() == StackToken.stat && tokens.get(i).getType() == Token.TokenType.TYPE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.stat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.SEMICOLON);
            metadata.add(child);
            stack.add(StackToken.decl);
            metadata.add(child);
            
        }
        else if(stack.getLast() == StackToken.stat && tokens.get(i).getType() == Token.TokenType.PRINT){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.stat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.SEMICOLON);
            metadata.add(child);
            stack.add(StackToken.print);
            metadata.add(child);
                    }
        else if(stack.getLast() == StackToken.stat && tokens.get(i).getType() == Token.TokenType.WHILE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.stat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.whilestat);
            metadata.add(child);
                   }
        else if(stack.getLast() == StackToken.stat && tokens.get(i).getType() == Token.TokenType.FOR){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.stat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.forstat);
            metadata.add(child);
                    }
        else if(stack.getLast() == StackToken.stat && tokens.get(i).getType() == Token.TokenType.IF){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.stat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.ifstat);
            metadata.add(child);
                   }
        else if(stack.getLast() == StackToken.stat && tokens.get(i).getType() == Token.TokenType.SEMICOLON){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.stat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.SEMICOLON);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.stat){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.los,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.whilestat && tokens.get(i).getType() == Token.TokenType.WHILE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.whilestat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.RBRACE);
            metadata.add(child);
            stack.add(StackToken.los);
            metadata.add(child);
            stack.add(StackToken.LBRACE);
            metadata.add(child);
            stack.add(StackToken.RPAREN);
            metadata.add(child);
            stack.add(StackToken.boolexprprime);
            metadata.add(child);
            stack.add(StackToken.relexpr);
            metadata.add(child);
            stack.add(StackToken.LPAREN);
            metadata.add(child);
            stack.add(StackToken.WHILE);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.forstat && tokens.get(i).getType() == Token.TokenType.ID){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.forstat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.RBRACE);
            metadata.add(child);
            stack.add(StackToken.los);
            metadata.add(child);
            stack.add(StackToken.LBRACE);
            metadata.add(child);
            stack.add(StackToken.RPAREN);
            metadata.add(child);
            stack.add(StackToken.forarith);
            metadata.add(child);
            stack.add(StackToken.SEMICOLON);
            metadata.add(child);
            stack.add(StackToken.boolexprprime);
            metadata.add(child);
            stack.add(StackToken.relexpr);
            metadata.add(child);
            stack.add(StackToken.SEMICOLON);
            metadata.add(child);
            stack.add(StackToken.forstart);
            metadata.add(child);
            stack.add(StackToken.LPAREN);
            metadata.add(child);
            stack.add(StackToken.FOR);
            metadata.add(child);
                    }
        else if(stack.getLast() == StackToken.forstart && tokens.get(i).getType() == Token.TokenType.ID){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.forstart,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.SEMICOLON);
            metadata.add(child);
            stack.add(StackToken.assign);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.forstart && tokens.get(i).getType() == Token.TokenType.TYPE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.forstart,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.SEMICOLON);
            metadata.add(child);
            stack.add(StackToken.decl);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.forstart && tokens.get(i).getType() == Token.TokenType.SEMICOLON){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.forstart,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
                    }
        else if(stack.getLast() == StackToken.forstart){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.forstart,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
                    }
        else if(stack.getLast() == StackToken.forarith && (tokens.get(i).getType() == Token.TokenType.ID||tokens.get(i).getType() == Token.TokenType.NUM||tokens.get(i).getType() == Token.TokenType.LPAREN)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.forarith,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.arithexpr);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.forarith && tokens.get(i).getType() == Token.TokenType.RPAREN){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.forarith,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
                    }
        else if(stack.getLast() == StackToken.forarith){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.forarith,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
                    }
        else if(stack.getLast() == StackToken.ifstat && tokens.get(i).getType() == Token.TokenType.IF){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.ifstat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.elseifstat);
            metadata.add(child);
            stack.add(StackToken.LBRACE);
            metadata.add(child);
            stack.add(StackToken.los);
            metadata.add(child);
            stack.add(StackToken.RBRACE);
            metadata.add(child);
            stack.add(StackToken.LPAREN);
            metadata.add(child);
            stack.add(StackToken.boolexprprime);
            metadata.add(child);
            stack.add(StackToken.relexpr);
            metadata.add(child);
            stack.add(StackToken.LPAREN);
            metadata.add(child);
            stack.add(StackToken.IF);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.elseifstat && (tokens.get(i).getType() == Token.TokenType.ID||tokens.get(i).getType() == Token.TokenType.TYPE
        ||tokens.get(i).getType() == Token.TokenType.PRINT||tokens.get(i).getType() == Token.TokenType.WHILE||
        tokens.get(i).getType() == Token.TokenType.FOR||tokens.get(i).getType() == Token.TokenType.IF
        ||tokens.get(i).getType() == Token.TokenType.SEMICOLON)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.elseifstat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.elseifstat && tokens.get(i).getType() == Token.TokenType.ELSE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.elseifstat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.elseifstat);
            metadata.add(child);
            stack.add(StackToken.LBRACE);
            metadata.add(child);
            stack.add(StackToken.los);
            metadata.add(child);
            stack.add(StackToken.RBRACE);
            metadata.add(child);
            stack.add(StackToken.elseorelseif);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.elseifstat){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.elseifstat,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
        }   
        else if(stack.getLast() == StackToken.elseorelseif && tokens.get(i).getType() == Token.TokenType.ELSE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.elseorelseif,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.possif);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.possif && tokens.get(i).getType() == Token.TokenType.LBRACE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.possif,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.possif && tokens.get(i).getType() == Token.TokenType.IF){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.possif,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.RPAREN);
            metadata.add(child);
            stack.add(StackToken.boolexprprime);
            metadata.add(child);
            stack.add(StackToken.relexpr);
            metadata.add(child);
            stack.add(StackToken.LPAREN);
            metadata.add(child);
            stack.add(StackToken.IF);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.possif){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.possif,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.assign && tokens.get(i).getType() == Token.TokenType.ID){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.assign,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.expr);
            metadata.add(child);
            stack.add(StackToken.ASSIGN);
            metadata.add(child);
            stack.add(StackToken.ID);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.decl && tokens.get(i).getType() == Token.TokenType.TYPE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.decl,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.possassign);
            metadata.add(child);
            stack.add(StackToken.ID);
            metadata.add(child);
            stack.add(StackToken.TYPE);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.possassign && tokens.get(i).getType() == Token.TokenType.ASSIGN){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.possassign,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.expr);
            metadata.add(child);
            stack.add(StackToken.ASSIGN);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.possassign && tokens.get(i).getType() == Token.TokenType.SEMICOLON){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.possassign,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
                    }
        else if(stack.getLast() == StackToken.possassign){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.possassign,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
                    }
        else if(stack.getLast() == StackToken.print && tokens.get(i).getType() == Token.TokenType.PRINT){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.print,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.RPAREN);
            metadata.add(child);
            stack.add(StackToken.printexpr);
            metadata.add(child);
            stack.add(StackToken.LPAREN);
            metadata.add(child);
            stack.add(StackToken.PRINT);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.expr && (tokens.get(i).getType() == Token.TokenType.ID||tokens.get(i).getType() == Token.TokenType.NUM
        ||tokens.get(i).getType() == Token.TokenType.LBRACE||tokens.get(i).getType() == Token.TokenType.FALSE||tokens.get(i).getType() == Token.TokenType.TRUE)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.expr,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.boolexprprime);
            metadata.add(child);
            stack.add(StackToken.relexpr);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.expr && tokens.get(i).getType() == Token.TokenType.CHARLIT){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.expr,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.charexpr);
            metadata.add(child);
            
        }
        else if(stack.getLast() == StackToken.boolexprprime && (tokens.get(i).getType() == Token.TokenType.AND||tokens.get(i).getType() == Token.TokenType.OR
        ||tokens.get(i).getType() == Token.TokenType.EQUAL||tokens.get(i).getType() == Token.TokenType.NEQUAL)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.boolexprprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.boolop);
            metadata.add(child);
            stack.add(StackToken.relexpr);
            metadata.add(child);
            stack.add(StackToken.boolexprprime);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.boolexprprime && (tokens.get(i).getType() == Token.TokenType.SEMICOLON||tokens.get(i).getType() == Token.TokenType.RPAREN)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.boolexprprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.boolexprprime){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.boolexprprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.boolop && (tokens.get(i).getType() == Token.TokenType.EQUAL||tokens.get(i).getType() == Token.TokenType.NEQUAL)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.boolop,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.booleq);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.boolop && (tokens.get(i).getType() == Token.TokenType.AND||tokens.get(i).getType() == Token.TokenType.OR)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.boolop,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.boollog);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.booleq && tokens.get(i).getType() == Token.TokenType.EQUAL){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.booleq,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.EQUAL);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.booleq && tokens.get(i).getType() == Token.TokenType.NEQUAL){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.booleq,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.NEQUAL);
            metadata.add(child);
        }
        
        else if(stack.getLast() == StackToken.boollog && tokens.get(i).getType() == Token.TokenType.AND){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.boollog,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.AND);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.boollog && tokens.get(i).getType() == Token.TokenType.OR){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.boollog,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.OR);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.relexpr && (tokens.get(i).getType() == Token.TokenType.ID||tokens.get(i).getType() == Token.TokenType.NUM
        ||tokens.get(i).getType() == Token.TokenType.LBRACE)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.relexpr,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
        }
        else if(stack.getLast() == StackToken.relexpr && tokens.get(i).getType() == Token.TokenType.TRUE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.relexpr,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.TRUE);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.relexpr && tokens.get(i).getType() == Token.TokenType.FALSE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.relexpr,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.FALSE);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.relexprprime && 
        (tokens.get(i).getType() == Token.TokenType.GE
        ||tokens.get(i).getType() == Token.TokenType.LE
        ||tokens.get(i).getType() == Token.TokenType.GT
        ||tokens.get(i).getType() == Token.TokenType.LT)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.relexprprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.arithexpr);
            metadata.add(child);
            stack.add(StackToken.relop);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.relexprprime && (tokens.get(i).getType() == Token.TokenType.EQUAL
        ||tokens.get(i).getType() == Token.TokenType.NEQUAL
        ||tokens.get(i).getType() == Token.TokenType.AND
        ||tokens.get(i).getType() == Token.TokenType.OR)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.relexprprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.relexprprime){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.relexprprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.relop && tokens.get(i).getType() == Token.TokenType.LT){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.relop,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.LT);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.relop && tokens.get(i).getType() == Token.TokenType.GT){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.relop,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.GT);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.relop && tokens.get(i).getType() == Token.TokenType.GE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.relop,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.GE);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.relop && tokens.get(i).getType() == Token.TokenType.LE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.relop,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.LE);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.arithexpr && (tokens.get(i).getType() == Token.TokenType.ID||tokens.get(i).getType() == Token.TokenType.NUM
        ||tokens.get(i).getType() == Token.TokenType.LBRACE)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.prog,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.arithexprprime);
            metadata.add(child);
            stack.add(StackToken.term);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.arithexprprime && tokens.get(i).getType() == Token.TokenType.PLUS){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.arithexprprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.arithexprprime);
            metadata.add(child);
            stack.add(StackToken.term);
            metadata.add(child);
            stack.add(StackToken.PLUS);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.arithexprprime && tokens.get(i).getType() == Token.TokenType.MINUS){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.arithexprprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.arithexprprime);
            metadata.add(child);
            stack.add(StackToken.term);
            metadata.add(child);
            stack.add(StackToken.MINUS);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.arithexprprime && (tokens.get(i).getType() == Token.TokenType.EQUAL
        ||tokens.get(i).getType() == Token.TokenType.NEQUAL
        ||tokens.get(i).getType() == Token.TokenType.AND
        ||tokens.get(i).getType() == Token.TokenType.OR
        ||tokens.get(i).getType() == Token.TokenType.GE
        ||tokens.get(i).getType() == Token.TokenType.LE
        ||tokens.get(i).getType() == Token.TokenType.RBRACE
        ||tokens.get(i).getType() == Token.TokenType.GT
        ||tokens.get(i).getType() == Token.TokenType.LT)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.arithexprprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.arithexprprime){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.arithexprprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.term && (tokens.get(i).getType() == Token.TokenType.ID||tokens.get(i).getType() == Token.TokenType.NUM
        ||tokens.get(i).getType() == Token.TokenType.LBRACE)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.term,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.termprime);
            metadata.add(child);
            stack.add(StackToken.factor);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.termprime && tokens.get(i).getType() == Token.TokenType.DIVIDE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.termprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.termprime);
            metadata.add(child);
            stack.add(StackToken.factor);
            metadata.add(child);
            stack.add(StackToken.DIVIDE);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.termprime && tokens.get(i).getType() == Token.TokenType.MOD){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.termprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.termprime);
            metadata.add(child);
            stack.add(StackToken.factor);
            metadata.add(child);
            stack.add(StackToken.MOD);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.termprime && tokens.get(i).getType() == Token.TokenType.TIMES){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.termprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.termprime);
            metadata.add(child);
            stack.add(StackToken.factor);
            metadata.add(child);
            stack.add(StackToken.TIMES);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.termprime && (tokens.get(i).getType() == Token.TokenType.PLUS||tokens.get(i).getType() == Token.TokenType.MINUS)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.termprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
            
        }
        else if(stack.getLast() == StackToken.termprime){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.termprime,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.epsilon);
            metadata.add(child);
            
        }
        else if(stack.getLast() == StackToken.factor && tokens.get(i).getType() == Token.TokenType.ID){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.factor,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.ID);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.factor && tokens.get(i).getType() == Token.TokenType.NUM){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.factor,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.NUM);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.factor && tokens.get(i).getType() == Token.TokenType.LPAREN){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.factor,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.RPAREN);
            metadata.add(child);
            stack.add(StackToken.arithexpr);
            metadata.add(child);
            stack.add(StackToken.LPAREN);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.printexpr &&(tokens.get(i).getType() == Token.TokenType.ID||tokens.get(i).getType() == Token.TokenType.NUM
        ||tokens.get(i).getType() == Token.TokenType.LBRACE)){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.prog,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.boolexprprime);
            metadata.add(child);
            stack.add(StackToken.relexpr);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.printexpr && tokens.get(i).getType() == Token.TokenType.DQUOTE){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.prog,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.stringlit);
            metadata.add(child);
            
        }
        else if(stack.getLast() == StackToken.charexpr && tokens.get(i).getType() == Token.TokenType.ID){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.prog,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.SQUOTE);
            metadata.add(child);
            stack.add(StackToken.CHARLIT);
            metadata.add(child);
            stack.add(StackToken.SQUOTE);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.stringlit && tokens.get(i).getType() == Token.TokenType.ID){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.prog,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
            stack.add(StackToken.DQUOTE);
            metadata.add(child);
            stack.add(StackToken.STRINGLIT);
            metadata.add(child);
            stack.add(StackToken.DQUOTE);
            metadata.add(child);
        }
        else if(stack.getLast() == StackToken.epsilon){
            ParseTree.TreeNode child = parseTree.new TreeNode(ParseTree.Label.epsilon,metadata.getLast());
            stack.removeLast();
            metadata.removeLast();
        }
         else {
            throw new SyntaxException();
            }
        }
        int coint = 3 + 2 * 5;
        return parseTree;
        
    }
    public static boolean tokenCompare(StackToken t, Token input){
        //Check if token input matches stack token
        //Add treenode with terminal label, token input and metadata parent
        if(t == StackToken.PLUS && input.getType() == Token.TokenType.PLUS){
            
            
            return true;
        }
        else if(t == StackToken.MINUS && input.getType() == Token.TokenType.MINUS){
            
            
            return true;
        }
        else if(t == StackToken.TIMES && input.getType() == Token.TokenType.TIMES){
            
            
            return true;
        }
        else if(t == StackToken.MOD && input.getType() == Token.TokenType.MOD){
            
            
            return true;
        }
        else if(t == StackToken.DIVIDE && input.getType() == Token.TokenType.DIVIDE){
            
            
            return true;
        }
        else if(t == StackToken.ASSIGN && input.getType() == Token.TokenType.ASSIGN){
            
            
            return true;
        }
        else if(t == StackToken.EQUAL && input.getType() == Token.TokenType.EQUAL){
            
            
            return true;
        }
        else if(t == StackToken.NEQUAL && input.getType() == Token.TokenType.NEQUAL){
            
            
            return true;
        }
        else if(t == StackToken.GT && input.getType() == Token.TokenType.GT){
            
            
            return true;
        }
        else if(t == StackToken.LT && input.getType() == Token.TokenType.LT){
            
            
            return true;
        }
        else if(t == StackToken.LE && input.getType() == Token.TokenType.LE){
            
            
            return true;
        }
        else if(t == StackToken.GE && input.getType() == Token.TokenType.GE){
            
            
            return true;
        }
        else if(t == StackToken.LPAREN && input.getType() == Token.TokenType.LPAREN){
            
            
            return true;
        }
        else if(t == StackToken.RPAREN && input.getType() == Token.TokenType.RPAREN){
            
            
            return true;
        }
        else if(t == StackToken.LBRACE && input.getType() == Token.TokenType.LBRACE){
            
            
            return true;
        }
        else if(t == StackToken.RBRACE && input.getType() == Token.TokenType.RBRACE){
            
            
            return true;
        }
        else if(t == StackToken.AND && input.getType() == Token.TokenType.AND){
            
            
            return true;
        }
        else if(t == StackToken.OR && input.getType() == Token.TokenType.OR){
            
            
            return true;
        }
        else if(t == StackToken.PUBLIC && input.getType() == Token.TokenType.PUBLIC){
            
            
            return true;
        }
        else if(t == StackToken.CLASS && input.getType() == Token.TokenType.CLASS){
            
            
            return true;
        }
        else if(t == StackToken.VOID && input.getType() == Token.TokenType.VOID){
            
            
            return true;
        }
        else if(t == StackToken.SEMICOLON && input.getType() == Token.TokenType.SEMICOLON){
            
            
            return true;
        }
        else if(t == StackToken.STATIC && input.getType() == Token.TokenType.STATIC){
            
            
            return true;
        }
        else if(t == StackToken.MAIN && input.getType() == Token.TokenType.MAIN){
            
            
            return true;
        }
        else if(t == StackToken.STRINGARR && input.getType() == Token.TokenType.STRINGARR){
            
            
            return true;
        }
        else if(t == StackToken.ARGS && input.getType() == Token.TokenType.ARGS){
            
            
            return true;
        }
        else if(t == StackToken.WHILE && input.getType() == Token.TokenType.WHILE){
            
            
            return true;
        }
        else if(t == StackToken.FOR && input.getType() == Token.TokenType.FOR){
            
            
            return true;
        }
        else if(t == StackToken.IF && input.getType() == Token.TokenType.IF){
            
            
            return true;
        }
        else if(t == StackToken.TYPE && input.getType() == Token.TokenType.TYPE){
            
            
            return true;
        }
        else if(t == StackToken.PRINT && input.getType() == Token.TokenType.PRINT){
            
            
            return true;
        }
        else if(t == StackToken.ELSE && input.getType() == Token.TokenType.ELSE){
            
            
            return true;
        }
        else if(t == StackToken.DQUOTE && input.getType() == Token.TokenType.DQUOTE){
            
            
            return true;
        }
        else if(t == StackToken.SQUOTE && input.getType() == Token.TokenType.SQUOTE){
            
            
            return true;
        }
        else if(t == StackToken.ID && input.getType() == Token.TokenType.ID){
            
            
            return true;
        }
        else if(t == StackToken.NUM && input.getType() == Token.TokenType.NUM){
            
            
            return true;
        }
        else if(t == StackToken.CHARLIT && input.getType() == Token.TokenType.CHARLIT){
            
            
            return true;
        }
        else if(t == StackToken.FALSE && input.getType() == Token.TokenType.FALSE){
            
            
            return true;
        }
        else if(t == StackToken.TRUE && input.getType() == Token.TokenType.TRUE){
            
            
            return true;
        }
        else if(t == StackToken.STRINGLIT && input.getType() == Token.TokenType.STRINGLIT){
            
            
            return true;
        }
        else {
            return false;
            }
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
