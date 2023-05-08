package interpreter.expr;

import java.util.List;

import interpreter.value.ListValue;
import interpreter.value.NumberValue;
import interpreter.value.ObjectValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

public class AccessExpr extends SetExpr {
   
    private Expr base;
    private Expr index; //indice eh sempre uma Variavel ou NumberValue (ou TextValue para mapas), que pode existir na memoria ou nao. Se nao existir, deve ser tratado como texto (String key = texto)
    //index.expr() retornara null se nao existe no mapa da memoria

    public AccessExpr(int line, Expr base, Expr index){
        super(line);
        this.base = base;
        this.index = index;
    }

    @Override
    public Value<?> expr() {
        Value<?> b = base.expr();
        Value<?> expIndex = index.expr();

        Value<?> retorno = null;

        if(b instanceof ListValue && expIndex instanceof NumberValue){
            ListValue arv = (ListValue) b;
            NumberValue n = (NumberValue) expIndex;
            List<Value<?>> array = arv.value();

            int index = n.value().intValue();

            if(n.value() < array.size()){
                retorno = array.get(index);
            }
            else{
                retorno = null;
            }
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

    @Override
    public void setValue(Value<?> value) {
        Value<?> b = base.expr();
        Value<?> expIndex = index.expr();


        if(b instanceof ListValue){ //index pode ser variavel ou NumberValue
            if(expIndex instanceof NumberValue){ //index eh uma (expressao ou variavel) que resulta em NumberValue
                ListValue arv = (ListValue) b;
                NumberValue n = (NumberValue) expIndex;
                List<Value<?>> array = arv.value();

                int index = n.value().intValue();

                if(index >= array.size()){
                    for(int i = array.size(); i<=index; i++){
                        if(i==index){
                            array.add(value);
                        }
                        else{
                            array.add(null);
                        }
                    }

                }
                arv.value().set(((NumberValue) expIndex).value().intValue(), value);
        
            }
       
        else if(b instanceof ObjectValue){
            
            String str = ((TextValue) index.expr()).value();
            ObjectValue mv = (ObjectValue) b;

            if(mv.value().containsKey(str)){
                mv.value().replace(new TextValue(str), value);
            }
            else{
                mv.value().put(new TextValue(str), value);
                }
            }
        }

    }
}
