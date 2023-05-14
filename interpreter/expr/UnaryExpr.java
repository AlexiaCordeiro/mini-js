package interpreter.expr;

import interpreter.value.BoolValue;
import interpreter.value.NumberValue;
import interpreter.value.Value;
import interpreter.InterpreterException;

public class UnaryExpr extends Expr {

    public static enum Op {
        Not,
        Pos,
        Neg,
        PreInc,
        PosInc,
        PreDec,
        PosDec
    }

    private Expr expr;
    private Op op;

    public UnaryExpr(int line, Expr expr, Op op) {
        super(line);
        this.expr = expr;
        this.op = op;
    }

    public Value<?> expr() {
        Value<?> v = this.expr.expr();
        switch (this.op) {
            case Not:
                return notOp(v);
            case Pos:
                return posOp(v);
            case Neg:
                return negOp(v);
            case PreInc:
                return preIncOp(v);
            case PosInc:
                return posIncOp(v);
            case PreDec:
                return preDecOp(v);
            case PosDec:
            default:
                return posDecOp(v);
        }
    }

    private Value<?> notOp(Value<?> v) {
        boolean b = BoolValue.convert(v);
        return new BoolValue(!b);
    }

    private Value<?> posOp(Value<?> v) {
        double n = NumberValue.convert(v);
        return new NumberValue(n);
    }

    private Value<?> negOp(Value<?> v) {
        double n = NumberValue.convert(v);
        return new NumberValue(-n);
    }

    private Value<?> preIncOp(Value<?> v) {
    	if(this.expr instanceof SetExpr) {
    		NumberValue result = new NumberValue(NumberValue.convert(v)+1);
    		((SetExpr) this.expr).setValue(result);
    		return result;
		}
    	else {
    		throw new InterpreterException(super.getLine());
    	}    	
    }
    	
    private Value<?> posIncOp(Value<?> v) {
    	if(this.expr instanceof SetExpr) {
    		NumberValue result = new NumberValue(NumberValue.convert(v)+1);
        	((SetExpr) this.expr).setValue(result);
        	NumberValue aux = new NumberValue(NumberValue.convert(v));
        	return aux;
		}
    	else {
    		throw new InterpreterException(super.getLine());
    	}  
    }

    private Value<?> preDecOp(Value<?> v) {
    	if(this.expr instanceof SetExpr) {
    		NumberValue result = new NumberValue(NumberValue.convert(v)-1);
    		((SetExpr) this.expr).setValue(result);
    		return result;
		}
    	else {
    		throw new InterpreterException(super.getLine());
    	}   
    }

    private Value<?> posDecOp(Value<?> v) {
    	if(this.expr instanceof SetExpr) {
    		NumberValue result = new NumberValue(NumberValue.convert(v)-1);
        	((SetExpr) this.expr).setValue(result);
        	NumberValue aux = new NumberValue(NumberValue.convert(v));
        	return aux;
		}
    	else {
    		throw new InterpreterException(super.getLine());
    	} 
    }
}
