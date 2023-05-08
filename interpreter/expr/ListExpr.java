package interpreter.expr;

import java.util.List;

import interpreter.value.Value;

public class ListExpr extends Expr {
    List<Expr> items;

    public ListExpr(int line, List<Expr> items){
        super(line);
        this.items = items;
    }

    @Override
    public Value<?> expr() {
        return null;
    }
}
