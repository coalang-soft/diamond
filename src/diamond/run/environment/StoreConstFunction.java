package diamond.run.environment;

import diamond.run.core.model.Value;
import diamond.run.core.model.operator.SingleOperator;

public class StoreConstFunction implements SingleOperator {
	
	private Scope scope;

	public StoreConstFunction(Scope s) {
		this.scope = s;
	}

	@Override
	public Value operateSingle(Value a, Value b) {
		scope.put(b.toString(), a);
		return a;
	}

}
