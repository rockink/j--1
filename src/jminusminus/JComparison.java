// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a comparison expression. This class captures common aspects
 * of comparison operations.
 */

abstract class JComparison extends JBooleanBinaryExpression {

    /**
     * Create an AST node for a comparison expression.
     * 
     * @param line
     *            line in which the expression occurs in the source file.
     * @param operator
     *            the comparison operator.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */

    protected JComparison(int line, String operator, JExpression lhs,
            JExpression rhs) {
        super(line, operator, lhs, rhs);
    }

    /**
     * The analysis of a comparison operation consists of analyzing its two
     * operands, and making sure they both have the same numeric type.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        lhs = (JExpression) lhs.analyze(context);
        rhs = (JExpression) rhs.analyze(context);


        lhs.type().mustMatchOneOf(line(), Type.INT, Type.DOUBLE);
        rhs.type().mustMatchExpected(line(), lhs.type());
        type = Type.BOOLEAN;
        return this;
    }

}

/**
 * The AST node for a greater-than (>) expression. Implements short-circuiting
 * branching.
 */

class JGreaterThanOp extends JComparison {

    /**
     * Construct an AST node for a greater-than expression given its line
     * number, and the lhs and rhs operands.
     * 
     * @param line
     *            line in which the greater-than expression occurs in the source
     *            file.
     * @param lhs
     *            lhs operand.
     * @param rhs
     *            rhs operand.
     */

    public JGreaterThanOp(int line, JExpression lhs, JExpression rhs) {
        super(line, ">", lhs, rhs);
    }

    /**
     * Branching code generation for > operation.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     * @param targetLabel
     *            target for generated branch instruction.
     * @param onTrue
     *            should we branch on true?
     */

    public void codegen(CLEmitter output, String targetLabel, boolean onTrue) {
        lhs.codegen(output);
        rhs.codegen(output);

        if(lhs.type().equals(Type.INT)) {
            output
                    .addBranchInstruction(onTrue ? IF_ICMPGT : IF_ICMPLE,
                            targetLabel);
        }else {
            output.addNoArgInstruction(onTrue? DCMPL : DCMPG);

            output
                    .addBranchInstruction(onTrue? IFGT : IFLE,
                    targetLabel);
        }
    }

}



/**
 * The AST node for a less-than (<) expression. Implements short-circuiting
 * branching.
 */

class JLessThanOp extends JComparison {

    /**
     * Construct an AST node for a less-than expression given its line
     * number, and the lhs and rhs operands.
     *
     * @param line
     *            line in which the less-than expression occurs in the source
     *            file.
     * @param lhs
     *            lhs operand.
     * @param rhs
     *            rhs operand.
     */

    public JLessThanOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "<", lhs, rhs);
    }

    /**
     * Branching code generation for < operation.
     *
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     * @param targetLabel
     *            target for generated branch instruction.
     * @param onTrue
     *            should we branch on true?
     */

    public void codegen(CLEmitter output, String targetLabel, boolean onTrue) {
        lhs.codegen(output);
        rhs.codegen(output);

        if(lhs.type().equals(Type.INT)) {
            output
                    .addBranchInstruction(onTrue ? IF_ICMPLT : IF_ICMPGE,
                            targetLabel);
        }else {

            output.addNoArgInstruction(onTrue? DCMPG : DCMPL);
            output
                    .addBranchInstruction(onTrue? IFLT : IFGE,
                            targetLabel);
        }
    }

}



/**
 * The AST node for a less-than-or-equal-to (<=) expression. Implements
 * short-circuiting branching.
 */

class JLessEqualOp extends JComparison {

    /**
     * Construct an AST node for a less-than-or-equal-to expression given its
     * line number, and the lhs and rhs operands.
     * 
     * @param line
     *            line in which the less-than-or-equal-to expression occurs in
     *            the source file.
     * @param lhs
     *            lhs operand.
     * @param rhs
     *            rhs operand.
     */

    public JLessEqualOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "<=", lhs, rhs);
    }

    /**
     * Branching code generation for <= operation.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     * @param targetLabel
     *            target for generated branch instruction.
     * @param onTrue
     *            should we branch on true?
     */

    public void codegen(CLEmitter output, String targetLabel, boolean onTrue) {
        lhs.codegen(output);
        rhs.codegen(output);


        if(lhs.type().equals(Type.INT)) {
            output
                    .addBranchInstruction(onTrue ? IF_ICMPLE : IF_ICMPGT,
                            targetLabel);
        }else {

            output.addNoArgInstruction(onTrue? DCMPG : DCMPL);

            output
                    .addBranchInstruction(onTrue ? IFLE : IFGE,
                            targetLabel);

        }
    }

}



/**
 * The AST node for a greater-than-or-equal-to (>=) expression. Implements
 * short-circuiting branching.
 */

class JGreaterEqualOp extends JComparison {

    /**
     * Construct an AST node for a greater-than-or-equal-to expression given its
     * line number, and the lhs and rhs operands.
     *
     * @param line
     *            line in which the greater-than-or-equal-to expression occurs in
     *            the source file.
     * @param lhs
     *            lhs operand.
     * @param rhs
     *            rhs operand.
     */

    public JGreaterEqualOp(int line, JExpression lhs, JExpression rhs) {
        super(line, ">=", lhs, rhs);
    }

    /**
     * Branching code generation for >= operation.
     *
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     * @param targetLabel
     *            target for generated branch instruction.
     * @param onTrue
     *            should we branch on true?
     */

    public void codegen(CLEmitter output, String targetLabel, boolean onTrue) {
        lhs.codegen(output);
        rhs.codegen(output);


        if(lhs.type().equals(Type.INT)) {
            output
                    .addBranchInstruction(onTrue ? IF_ICMPGE : IF_ICMPLT,
                            targetLabel);
        }else {

            output.addNoArgInstruction(onTrue? DCMPL : DCMPG);

            output
                    .addBranchInstruction(onTrue ? IFGE : IFLT,
                            targetLabel);

        }
    }

}



