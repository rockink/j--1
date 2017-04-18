package jminusminus;

import static jminusminus.CLConstants.GOTO;

/**
 * The AST node for a break-statement.
 */

public class JBreakStatement extends JStatement{
	
	/** Name of Label */
	protected String labelName;
	
	/**
	 * Construct an AST of break statement given the line 
	 * and label name
	 * 
	 * @param line
	 * 			line in which the break-statement appears
     *          in the source file.
	 * @param labelName
	 * 			Name of Label
	 */
	
	protected JBreakStatement(int line, String labelName) {
		super(line);
		this.labelName = labelName;
	}

	@Override
	public JAST analyze(Context context) {
		//two types of break, labelled and unlabelled

		return this;
	}





	@Override
	public void partialCodegen(Context context, CLEmitter partial) {
		super.partialCodegen(context, partial);
	}


	@Override
	public void codegen(CLEmitter output, String label, JLabelStatement jLabelStatement) {


		if (jLabelStatement != null){
			System.out.println("jLabelname " + jLabelStatement.labelName);
			System.out.println("Breakname " + labelName);
			System.out.println("outlabel " + jLabelStatement.outLabel);
		}


		//break contains the labelName

		if(jLabelStatement != null && jLabelStatement.labelName.equals(labelName)){
			output.addBranchInstruction(GOTO, jLabelStatement.outLabel);
		}else {
			output.addBranchInstruction(GOTO, label);

		}
	}


	@Override
	public void writeToStdOut(PrettyPrinter p) {
		if (labelName != null) {
            p.printf("<JBreakStatement line=\"%d\" labelName=\"%s\"/>\n", line(), labelName);
        } else {
            p.printf("<JBreakStatement line=\"%d\"/>\n", line());
        }
	}


}
