package jminusminus;

/** The AST node for a Conditional Operator ?:
 */

public class JConditionalOperator extends JExpression {
	
	/**The condition*/
	protected JExpression lhs;
	
	/**Expression to be executed when condition is true*/
	protected JExpression rhsYes;
	
	/**Expression to be executed when condition is false*/
	protected JExpression rhsNo;
	
	/**
	 * Construct an AST for conditional Operator given its 
	 * line number, lhs, rhsYes, rhsNo
	 * 
	 * @param line
	 * 			line in which the conditional operator occurs in the source
     *          file.
	 * @param lhs
	 * 			condition to be evaluated
	 * @param rhsYes
	 * 			Expression to be executed when condition is true
	 * @param rhsNo
	 * 			Expression to be executed when condition is false
	 */
	
	protected JConditionalOperator(int line, JExpression lhs, JExpression rhsYes, JExpression rhsNo) {
		super(line);
		this.lhs = lhs;
		this.rhsYes = rhsYes;
		this.rhsNo = rhsNo;
	}

	@Override
	public JExpression analyze(Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void codegen(CLEmitter output, String label, JLabelStatement jLabelStatement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeToStdOut(PrettyPrinter p) {
		 p.printf("<JConditionalExpression line=\"%d\" type=\"%s\" "
	                + "operator=\"%s\">\n", line(), ((type == null) ? "" : type
	                        .toString()), Util.escapeSpecialXMLChars("?:"));
	        p.indentRight();
	        p.printf("<Lhs>\n");
	        p.indentRight();
	        lhs.writeToStdOut(p);
	        p.indentLeft();
	        p.printf("</Lhs>\n");
	        p.printf("<RhsYes>\n");
	        p.indentRight();
	        rhsYes.writeToStdOut(p);
	        p.indentLeft();
	        p.printf("</RhsYes>\n");
	        p.printf("<RhsNo>\n");
	        p.indentRight();
	        rhsNo.writeToStdOut(p);
	        p.indentLeft();
	        p.printf("</RhsNo>\n");
	        p.indentLeft();
	        p.printf("</JConditionalExpression>\n");
		
	}

}
