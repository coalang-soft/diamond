package diamond.run.jbridge;

import diamond.run.core.model.Value;
import diamond.run.core.model.operator.SingleOperator;
import io.github.coalangsoft.reflect.Clss;

public class MethodFunction implements SingleOperator{

	@Override
	public Value operateSingle(Value obj, Value method) {
		if(obj instanceof JavaClass){
			try {
				return new JavaFunctionArray(((Clss) obj.get()).getMethods(null, method.toString()));
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			}
		}else{
			try {
				return new JavaFunctionArray((new Clss(obj.get().getClass())).getMethods(obj.get(), method.toString()));
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			}
		}
	}

}