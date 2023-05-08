package interpreter.expr;

import java.util.List;

import interpreter.value.ListValue;
import interpreter.value.NumberValue;
import interpreter.value.ObjectValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

public class AccessExpr extends SetExpr {
    private Expr base;
    private Expr index;

    public AccessExpr(int line, SetExpr base, Expr index) {
        super(line);
        this.base = base;
        this.index = index;
    }

    @Override
    public Value<?> expr(){
        Value<?> b = base.expr();
        Value<?> expIndex = index.expr();

        Value<?> retorno = null;

        if(b instanceof ListValue && expIndex instanceof NumberValue){
            ListValue arv = (ListValue) b;
            NumberValue nv = (NumberValue) expIndex;
            List<Value<?>> array = arv.value();

            
        }
        else if(b instanceof ObjectValue){
            String str = ((TextValue)expIndex).value();
            ObjectValue mv = (ObjectValue) b;
            if(mv.value().containsKey(str)){
                retorno = mv.value().get(str);
            }
            else{
                return null;
            }
        }

        return retorno;
    }

    public void setValue(Value<?> value){
        Value<?> b = base.expr();
        Value<?> expIndex = index.expr();

        if(b instanceof ListValue){ 
            if(expIndex instanceof NumberValue){ 
                ListValue arv = (ListValue) b;
                Double n = ((NumberValue)expIndex).value();
                List<Value<?>> array = arv.value();
                
                if(n >= array.size()){
                    for(int i = array.size(); i<=n; i++){
                        if(i==n){
                            array.add(value);
                        }
                        else{
                            array.add(null);
                        }
                    }
                }
                arv.value().set(((NumberValue)expIndex).value(), value); 
        }
        //index pode ser uma variavel ou ListValue.
        //Se for variavel, deve expandir para ListValue se existir.
            //Se nao exitir, o nome da variavel deve ser considerado como chave
        else if(b instanceof ObjectValue){
            String str = ((TextValue) index.expr()).value();
            ObjectValue mv = (ObjectValue) b;
            if(mv.value().containsKey(str)){
                mv.value().replace(str, value);
            }
            else{
                mv.value().put(str, value);
            }
        }
       
    }
}
