package jminusminus;

/**
 * The AST node for a static block.
 */

public class JStaticBlock extends JAST implements JMember {
	
	/** The static block body */
	protected JBlock block;
	
	/**
	 * Construct an AST of a static block 
	 * given the line number and static block body
	 * 
	 * @param line  line in which static block appears 
	 * 				in the source file.
	 * 
	 * @param body  static block body
	 */
	JStaticBlock(int line, JBlock body) {
		super(line);
		this.block = body;
	}


	//here we test the stuff like return and all
	public void preAnalyze(Context context, CLEmitter partial) {

    }


	@Override
	public void partialCodegen(Context context, CLEmitter partial) {
		super.partialCodegen(context, partial);
	}

	public JAST analyze(Context context) {
		return block.analyze(context);
	}

	public void codegen(CLEmitter output, String label, JLabelStatement jLabelStatement) {
		block.codegen(output, label, jLabelStatement);
	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JStaticBlock line=\"%d\">\n", line());
		p.indentRight();
		if (block != null) {
            p.println("<Static Block Body>");
            p.indentRight();
            block.writeToStdOut(p);
            p.indentLeft();
            p.println("</Static Block Body>");
        }
        p.indentLeft();
        p.println("</JStaticBlock>");
	}

}
