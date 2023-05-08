package interpreter.expr;

import interpreter.value.Value;

public class ConditionalExpr extends Expr {

    Expr cond;
    Expr trueExpr;
    Expr falsExpr;

    public ConditionalExpr(int line, Expr trueExpr, Expr falsExpr) {
        super(line);
        this.trueExpr = trueExpr;
        this.falsExpr = falsExpr;
    }

    public Value<?> expr() {
        return null;
    }
    
}
