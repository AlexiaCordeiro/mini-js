package interpreter.expr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import interpreter.value.ObjectValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

public class ObjectExpr extends Expr{
    
 
    public class ObjectItem{
        private TextValue key;
        private Expr value;

        public ObjectItem(TextValue key, Expr value){
            this.key = key;
            this.value = value;
        }
    }

    private List<ObjectItem> items;

    public ObjectExpr(int line){
        super(line);
        this.items = new ArrayList<>();
    }



    @Override
    public Value<?> expr(){
        HashMap<TextValue, Value<?>> mapa = new HashMap<>();

        for(ObjectItem i : items){
            mapa.put(i.key, i.value.expr());
        }

        ObjectValue ov = new ObjectValue(mapa);

        return ov;
    }
}
