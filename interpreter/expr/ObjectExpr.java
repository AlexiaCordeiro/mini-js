package interpreter.expr;

import java.util.List;

import interpreter.value.Value;

public class ObjectExpr extends Expr{
    List<ObjectItem> items;
    
    public ObjectExpr(int line, List<ObjectItem> items){
        super(line);
        this.items = items;
    }

    @Override
    public Value<?> expr() {
        return null;

    }
}
