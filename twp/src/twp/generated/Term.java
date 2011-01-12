package twp.generated;

import java.util.List;

import twp.core.Container;
import twp.core.GenericApplicationType;
import twp.core.GenericStruct;
import twp.core.GenericUnion;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class Term implements Container {
	private Double value = null;
	private Expression expr = null;
	private int option = -1;
	
	public static Class getClassForId(int id) {
		Class clazz;
		switch (id) {
		case 0:
			clazz = Double.class;
			break;
		case 1:
			clazz = Expression.class;
			break;
		default:
			clazz = Object.class;
		}
		return clazz;
	}
	
	public static int getIdForClass(Class clazz) {
		int id = -1;
		if (clazz == Double.class)
			id = 0;
		else if (clazz == Expression.class) 
			id = 1;
		return id;
	}
	
	public Term(Double newValue) {
		option = 0;
		value = newValue;
	}
	
	public Term(Expression newExpr) {
		option = 1;
		expr = newExpr;
	}
	
	public Term(List<Object> params) {
		
	}
	
	public Term(GenericUnion elem) {
		option = elem.getId();
		switch(elem.getId()) {
			case 0:
				value = new Double((GenericApplicationType) elem.getValue());
				break;
			case 1:
				expr = new Expression((GenericStruct) elem.getValue());
		}
	}
	
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double newValue) {
		if (newValue != null)
			option = 0;
		value = newValue;
	}

	public Expression getExpr() {
		return expr;
	}
	
	public void setExpr(Expression newExpr) {
		if (newExpr != null)
			option = 1;
		expr = newExpr;
	}
	
	@Override
	public ParameterType getParameterType() {
		return ParameterType.UNION;
	}

	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.UNION);
		container.setId(option);
		if (option == 0)
			container.add(CalculatorProtocol.decompose(value));
		else if (option == 1)
			container.add(CalculatorProtocol.decompose(expr));
		return container;
	}

}
