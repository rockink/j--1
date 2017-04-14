package jminusminus;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;

import static jminusminus.TokenKind.*;

/**
 * The AST node for a Enhanced For Statement.
 */

public class JForEnhancedStatement extends JStatement {
	
	/** Type of variable */
	protected Type type;
	
	/** Name of variable */
	protected String name;
	
	/** Expression */
	protected JExpression expression;
	
	/** Enhanced for body */
	protected JStatement body;
	
	/**
     * Construct an AST node for an Enhanced for statement given its line number, the
     * type of variable, variable name, Expression and enhanced for body
     * 
     * @param line
     *            line in which the enhanced for statement occurs in the source file.
     * @param name
     * 			  Name of variable
     * @param expression
     * 			  Expression
     * @param body
     *            enhanced for body.
     */
	
	protected JForEnhancedStatement(int line, Type type, String name, JExpression expression, JStatement body) {
		super(line);
		this.type = type;
		this.name = name;
		this.expression = expression;
		this.body = body;
	}


	private boolean seeBasicType(Type type) {
		return  type.equals(Type.DOUBLE)  ||
				type.equals(Type.INT) ||
				type.equals(Type.CHAR) ||
				type.equals(Type.BOOLEAN);
	}


	@Override
	public JAST analyze(Context context) {
		//2 cases primitive
		// and collections

		Type superType = expression.type().superClass();


		if(superType.equals(Type.ITERABLE)){
			return iterableEnhancedFor(context);
		}

		return null;
	}

	private JAST iterableEnhancedFor(Context context) {

		return null;
	}

	final static String I_NAME = "_internal_i";
	private void forInit(Type type) {
		JVariable initVariable = new JVariable(line, I_NAME);

//		if(type.superClass().equals(Type.ITERABLE)){
//			JExpression singleType = new JMessageExpression(line, )
//		}

	}

	@Override
	public void codegen(CLEmitter output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JForEnhancedStatement line=\"%d\" type=\"%s\" name=\"%s\">\n",
				line(), type, name);
        p.indentRight();
        p.printf("<Expression>\n");
        p.indentRight();
        expression.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Expression>\n");
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</JForEnhancedStatement>\n");
		
	}

}
