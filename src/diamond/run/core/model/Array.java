package diamond.run.core.model;

import diamond.run.core.impl.ArrayImpl;
import diamond.run.core.impl.dynamic.DynamicArray;
import diamond.run.environment.Scope;

public interface Array extends Value{
	
	default Type getType(){
		return Type.ARRAY;
	}
	
	default Value take(Scope s, Value v){
		if(v.getType() == Type.ARRAY){
			return takeArray(s, (Array) v);
		}else if(v.getType() == Type.ARRAY_FUNCTION){
			return v.take(s, this);
		}else{
			return takeSingle(s, v);
		}
	}
	
	default Array takeSingle(Scope s, Value v){
		Value[] vals = new Value[length()];
		for(int i = 0; i < vals.length; i++){
			vals[i] = get(i).take(s, v);
		}
		return makeArray(vals);
	}
	
	default Array takeArray(Scope s, Array v){
		int len = length() < v.length() ? v.length() : length();
		Value[] vs = new Value[len];
		for(int i = 0; i < len; i++){
			vs[i] = get(i % length()).take(s, v.get(i % v.length()));
		}
		return makeArray(vs);
	}
	
	default Value callZeroArg(){
		if(length() == 1){
			return get(0).callZeroArg();
		}
		return this;
	}

	int length();
	Value get(int index);
	default Array makeArray(Value... values){
		return new ArrayImpl(values);
	}

}
