package jminusminus;

import java.util.ArrayList;

/**
 * The AST node for a Basic For Statement.
 */

public class JForBasicStatement extends JStatement {
	
	/** forInit Statements */
	protected ArrayList<JStatement> forInitStatements;
	
	/** forInit Declarations */
	protected ArrayList<JVariableDeclarator> forInitDeclarations;
	
	/** Test expression. */
	protected JExpression condition;

	/** forUpdate statements */
	protected ArrayList<JStatement> forUpdate;
	
	/** Basic For body */
	protected JStatement body;

	private boolean forInitDeclared = false;
	private JBlock forBlock;

	/**
     * Construct an AST node for a basic for statement given its line number, the
     * forInit Statements or forInit Declarations, test expression,
     * forUpdate Statements and the basic for body.
     * 
     * @param line
     *            line in which the basic for statement occurs in the source file.
     * @param forInitStatements
     *            forInit Statements
     * @param forInitDeclarations
     * 			  forInit Declarations
     * @param condition
     * 			  Test Expression
     * @param body
     *            basic for body.
     */
	
	protected JForBasicStatement(int line, ArrayList<JStatement> forInitStatements,
			ArrayList<JVariableDeclarator> forInitDeclarations, JExpression condition, 
			ArrayList<JStatement> forUpdate, JStatement body) {
		super(line);

		if (forInitDeclarations != null) forInitDeclared = true;
		this.forInitStatements = forInitStatements;
		this.forInitDeclarations = forInitDeclarations;
		this.condition = condition;
		this.forUpdate = forUpdate;
		this.body = body;
	}


	@Override
	public JAST analyze(Context context) {

		ArrayList<JStatement> jStatements = new ArrayList<>();

		if(forInitStatements != null)
		for(int i = 0; i < forInitStatements.size(); i++){
		    jStatements.add((JStatement) forInitStatements.get(i));
		}

		if(forInitDeclarations != null){
			ArrayList<String> mods = new ArrayList<String>();
			for(int i = 0; i < forInitDeclarations.size(); i++){
				forInitDeclarations.set(i, (JVariableDeclarator) forInitDeclarations.get(i));
			}
			jStatements.add(new JVariableDeclaration(line, mods, forInitDeclarations));
		}


		ArrayList<JStatement> bodyBlock = new ArrayList<>();
		bodyBlock.add(body);

		if(forUpdate != null){
			for(int i = 0; i < forUpdate.size(); i++){
				bodyBlock.add((JStatement) forUpdate.get(i));//.analyze(context));
			}
		}

		JStatement whileBody = new JBlock(line, bodyBlock);
		JWhileStatement whileStatement = new JWhileStatement(line, condition, whileBody);
		jStatements.add(whileStatement); //.analyze(context));
		this.forBlock = new JBlock(line, jStatements).analyze(context);
		return forBlock;
	}


	/**
	 * for the for loop,
	 *
	 * ForStatement has : <header descriptions><body>
	 *
	 *
	 *
	 * @param output
	 *            the code emitter (basically an abstraction for producing the
	 * @param jLabelStatement
	 */
	@Override
	public void codegen(CLEmitter output, String label, JLabelStatement jLabelStatement) {

		System.out.println("JforCodegen");
		forBlock.codegen(output, label, jLabelStatement);
	}

	@Override
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JForBasicStatement line=\"%d\">\n", line());
		
		p.indentRight();
		if(forInitStatements != null){
			p.println("<ForInitStatements>");
            p.indentRight();
            for (JStatement statement : forInitStatements) {
                statement.writeToStdOut(p);
            }
            p.indentLeft();
            p.println("</ForInitStatements>");
		}
		
		if(forInitDeclarations != null){
			p.printf("<ForInitDeclarations>\n");
        	p.indentRight();
            for (JVariableDeclarator declaration : forInitDeclarations) {
                declaration.writeToStdOut(p);
            }
            p.indentLeft();
            p.println("</ForInitDeclarations>");
		}
		
		if(condition != null){
			p.printf("<TestExpression>\n");
	        p.indentRight();
	        condition.writeToStdOut(p);
	        p.indentLeft();
	        p.printf("</TestExpression>\n");
		}
        
		if(forUpdate != null){
			p.printf("<ForUpdate>\n");
	        p.indentRight();
	        for (JStatement statement : forUpdate) {
	            statement.writeToStdOut(p);
	        }
	        p.indentLeft();
	        p.printf("</ForUpdate>\n");
		}
       
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</JForBasicStatement>\n");
	}


}
