package interpreter.command;

import java.util.List;
import java.util.Map;

import interpreter.InterpreterException;
import interpreter.expr.Expr;
import interpreter.expr.Variable;
import interpreter.value.ListValue;
import interpreter.value.ObjectValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

public class ForCommand extends Command{
	
    private Variable var;
	private Expr expr;
    private Command cmds;

    public ForCommand(int line, Variable var, Expr expr, Command cmds) {
        super(line);
        this.expr = expr;
        this.cmds = cmds;
        this.var = var;
    }
    
    public void execute() {
        Value<?> v = expr.expr();
        if (v instanceof ListValue) {
            ListValue lv = (ListValue) v;
            List<Value<?>> items = lv.value();
            for (Value<?> item : items) {
                var.setValue(item);
               
                cmds.execute();
            }
        } else if (v instanceof ObjectValue) {
            ObjectValue ov = (ObjectValue) v;
            Map<TextValue, Value<?>> map = ov.value();
            for (TextValue key : map.keySet()) {
                Value<?> item = map.get(key);
                var.setValue(item);
                
                cmds.execute();
            }
        } else {
        	throw new InterpreterException(super.getLine());
        }
    }
}
