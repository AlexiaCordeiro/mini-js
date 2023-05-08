package interpreter.expr;

import interpreter.value.Value;

public class AccessExpr extends SetExpr {
    SetExpr base;
    Expr index;
    private Value<?> value;

    public AccessExpr(int line, SetExpr base, Expr index) {
        super(line);
        this.base = base;
        this.index = index;
    }

    public Value<?> expr(){
        return value;
    }

    public void setValue(Value<?> value){
        this.value = value;
    }
}
