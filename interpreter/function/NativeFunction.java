package interpreter.function;

import java.util.Random;
import java.util.Scanner;

import interpreter.expr.Variable;
import interpreter.value.NumberValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

public class NativeFunction extends Function{
    
    public static enum NativeOp{
        Log,
        Read,
        Random
    }

    private NativeOp op;
    
    public NativeFunction(Variable params, NativeOp op) {
        super(params);
        this.op = op;
    }

    public Value<?> call(){
        Value<?> returnValue = null;

        if(op == NativeOp.Log){
            System.out.println(getParams().getName());
            
        }else if(op == NativeOp.Read){
            try (Scanner scanner = new Scanner(System.in)) {
                String nextLine = scanner.nextLine();

                returnValue = new TextValue(nextLine);
            }
        } else if(op == NativeOp.Random){
            Random random = new Random();
            double randomValue = random.nextDouble();

            returnValue = new NumberValue(randomValue);
        }

        return returnValue;
    }

}
