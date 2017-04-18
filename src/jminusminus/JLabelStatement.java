package jminusminus;

import static jminusminus.CLConstants.GOTO;

/**The AST of a Label statement */

public class JLabelStatement extends JStatement {
	
	/** Name of Label */
	protected String labelName;
	
	/** Label Statement */
	/** This is so confusing, I thought the statement would be outside the label Statement */
	//if this is a case, then just analyse this!
	protected JStatement statement;
	protected String outLabel;


	/**
	 * Construct an AST for label statement given the 
	 * line number, Label Name and label statement
	 * 
	 * @param line
	 * 			line in which the label-statement appears
     *          in the source file.
	 * @param labelName
	 * 			Name of Label
	 * @param statement
	 * 			Label Statement
	 */
	
	protected JLabelStatement(int line, String labelName, JStatement statement) {
		super(line);
		this.statement = statement;
		this.labelName = labelName;
		
	}

	@Override
	public JAST analyze(Context context) {
		// TODO Auto-generated method stub
		//not sure what to do here
		statement.analyze(context);
		return this;
	}



	@Override
	public void writeToStdOut(PrettyPrinter p) {
		 p.printf("<JLabelStatement line=\"%d\" labelName=\"%s\"/>\n", line(), labelName);
         p.indentRight();
         statement.writeToStdOut(p);
         p.indentLeft();
         p.printf("</JLabelStatement>\n");
	}

	@Override
	public void codegen(CLEmitter output, String label, JLabelStatement jLabelStatement) {

		//codegen here also gens the code of the label!!
		String outLabel = output.createLabel();
		this.outLabel = outLabel;
		System.out.println("creating label " + outLabel + " at line " + statement.line);
		statement.codegen(output, outLabel, this);

//		output.addBranchInstruction(GOTO, outLabel);
		//TODO THIS IS EXPERIMENTAL
		output.addLabel(outLabel);

		System.out.println("generating label done...");
	}

}
