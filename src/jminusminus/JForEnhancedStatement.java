package jminusminus;

import java.util.ArrayList;

/**
 * The AST node for a Enhanced For Statement.
 */

public class JForEnhancedStatement extends JStatement {

    /**
     * Type of variable
     */
    protected Type type;

    /**
     * Name of variable
     */
    protected String name;


    AmbiguousName ambiguousName;

    /**
     * Expression
     */
    protected JExpression expression;

    /**
     * Enhanced for body
     */
    protected JStatement body;

    /**
     * Construct an AST node for an Enhanced for statement given its line number, the
     * type of variable, variable name, Expression and enhanced for body
     *
     * @param line       line in which the enhanced for statement occurs in the source file.
     * @param name       Name of variable
     * @param expression Expression
     * @param body       enhanced for body.
     */

    protected JForEnhancedStatement(int line, Type type, String name, JExpression expression, JStatement body) {
        super(line);
        this.type = type;
        System.out.println("type should be double " + type.simpleName());
        this.name = name;
        this.expression = expression;
        System.out.println("expr type " + expression.type);
        this.body = body;
        ambiguousName = new AmbiguousName(line, "java.util.Iterator");
    }


    private boolean seeBasicType(Type type) {
        return type.equals(Type.DOUBLE) ||
                type.equals(Type.INT) ||
                type.equals(Type.CHAR) ||
                type.equals(Type.BOOLEAN);
    }


    @Override
    public JAST analyze(Context context) {


//        try {
            if (type.isPrimitive())
                return primitiveEnhancedFor(context);

//        }catch (Exception e){
            //be aware that it is exception!

            //TODO CHECK IF IT IS ITERABLE TYPE HERE!!
            return iterableEnhancedFor(context);
//        }

//        return null;
    }

    private JAST primitiveEnhancedFor(Context context) {

        JForBasicStatement jForBasicStatement = new JForBasicStatement(
                line, null,primitiveInitVariableDeclarators(),
                primitiveCondition(), primitiveForUpdate(), // primitiveForUpdate(),
                primitiveBody(context));

        System.out.println("iterable Enhanced for ");
        return jForBasicStatement.analyze(context);

    }

    private ArrayList<JStatement> primitiveForUpdate() {
        JVariable internalI = new JVariable(line, I_NAME);

        JStatement assignmentExpression = new JPostIncrementOp(line, internalI);
        ArrayList<JStatement> jStatements = new ArrayList<>();
        jStatements.add(assignmentExpression);
        return jStatements;
    }


    private JStatement primitiveBody(Context context) {
        ArrayList<JStatement> bodyBlock = new ArrayList<>();
        bodyBlock.add(primitiveIdentifier());
        bodyBlock.add(body);
        return new JBlock(line, bodyBlock);
    }



    /**
     *
     * For instance,
     * (for (int i =0; i < d.length; i++) //done {
     *
     *     double each = d[i];
     *
     * }
     *
     * @return
     */
    private JStatement primitiveIdentifier() {
        //this expression is of inside i

        JExpression insideI = new JVariable(line, I_NAME);
        JArrayExpression arrayElem = new JArrayExpression(line, expression, insideI);


        JVariableDeclarator identifier = new JVariableDeclarator(line, name, type, arrayElem);
        ArrayList<JVariableDeclarator> jVariableDeclarators = new ArrayList<>();
        jVariableDeclarators.add(identifier);

        JVariableDeclaration statement
                = new JVariableDeclaration(line, new ArrayList<>(), jVariableDeclarators);
        return statement;

    }


    /**
     * it is a condition of name.length
     * So.
     * i < d.length
     * @return
     */
    private JExpression primitiveCondition() {
        JExpression lhs = new JVariable(line, I_NAME);
        JExpression rhs = new JFieldSelection(line, expression, "length");
        JExpression jBinaryExpression = new JLessThanOp(line, lhs, rhs);

        System.out.println("binary Expression ");
        return jBinaryExpression;

    }

    private ArrayList<JVariableDeclarator> primitiveInitVariableDeclarators() {
        JExpression lhs = new JVariable(line, I_NAME);
        JExpression rhs = new JLiteralInt(line, "0");

        JExpression assignment = new JAssignOp(
                line, lhs, rhs
        );

        JVariableDeclarator init = new JVariableDeclarator(line, I_NAME, Type.INT, assignment);
        ArrayList<JVariableDeclarator> declarators = new ArrayList<>();
        declarators.add(init);
        return declarators;

    }

    @Override
    public void codegen(CLEmitter output, String label, JLabelStatement jLabelStatement) {

    }


    private JAST iterableEnhancedFor(Context context) {

        ArrayList<JVariableDeclarator> vDecls = initVariableDeclarators();
        JVariableDeclarator varDecl = vDecls.get(0);
        JForBasicStatement jForBasicStatement = new JForBasicStatement(
                line, null,initVariableDeclarators(), iteratorCondition(),  null, iteratorBody() );

        System.out.println("iterable Enhanced for ");
        return jForBasicStatement.analyze(context);
    }

    private ArrayList<JVariableDeclarator> initVariableDeclarators() {
        return initDeclarators();
    }


    /**
     * AssignmentOp assigns
     * lhs = rhs
     * for init, it is a case of
     * Iterator insideI = collections.iterator()
     * @return
     */
    private ArrayList<JVariableDeclarator> initDeclarators() {
        JExpression lhs = new JVariable(line, I_NAME);
        JExpression rhs = new JMessageExpression(line, expression, null, "iterator", new ArrayList<>());

        JExpression assignment = new JAssignOp(
                line, lhs, rhs
        );

        JVariableDeclarator init = new JVariableDeclarator(line, I_NAME, Type.ITERATOR, assignment);
        ArrayList<JVariableDeclarator> declarators = new ArrayList<>();
        declarators.add(init);
        return declarators;
    }


    private JBlock iteratorBody() {
        ArrayList<JStatement> bodyBlock = new ArrayList<>();
        bodyBlock.add(identifier());
        bodyBlock.add(body);
        return new JBlock(line, bodyBlock);
    }

    private JVariableDeclaration identifier() {
        JVariableDeclaration identifier
                = new JVariableDeclaration(line, new ArrayList<>(), variableDeclarators());
        return identifier;
    }

    /**
     * This is the body's first line
     * Type identifier = insideI.next()
     * @return
     */
    private ArrayList<JVariableDeclarator> variableDeclarators() {

        JVariableDeclarator identifier = new JVariableDeclarator(line, name, type, variableInitializer());
        ArrayList<JVariableDeclarator> jVariableDeclarators = new ArrayList<>();
        jVariableDeclarators.add(identifier);
        return jVariableDeclarators;

    }

    /**
     * Parse Tree is
     * this is to be parsed: insideI.next()
     * <Initializer>
         * <JMessageExpression line="19" name="next">
           * <Arguments>
            * </Arguments>
        * </JMessageExpression>
     * </Initializer>
     */
    private JMessageExpression variableInitializer() {

        //this expression is of inside i
        JExpression insideI = new JVariable(line, I_NAME);
        JMessageExpression next = new JMessageExpression(line, insideI, null, "next", new ArrayList<>());
        return next;

    }

    /**
     * This involves creating lhs and rhs
     * This condition involves the inside_I iterator,
     *
     * insideI.hasNext();
     *
     */
    private JExpression iteratorCondition() {

        JExpression conditionLHSExpression = new JVariable(line, I_NAME);
        JExpression testCondition = new JMessageExpression(
                line, conditionLHSExpression , "hasNext", new ArrayList<>()
        );
        return testCondition;
    }

    final static String I_NAME = "_internal_i";

    private JVariableDeclarator forInitDeclarations() {

        JVariableDeclarator insideI =
                new JVariableDeclarator(line, I_NAME, Type.ITERATOR, initializer());
        return insideI;

    }

    /**
     * Initializer for iterator
     *
     * @return
     */
    private JExpression initializer() {
        JExpression iterator = new JMessageExpression(
                line, null,ambiguousName,  "iterator", new ArrayList<>()
        );

        return iterator;
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
