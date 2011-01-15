package twp.generated;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import twp.core.Container;
import twp.core.GenericApplicationType;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class Double implements Container {
	private double value;
	private byte[] content;
	public final int ID = 160;
	
	public Double() {};
	
	public Double(double newValue) {
		setValue(newValue);
	}
	
	public Double(List<Object> params) {
		if (params.size() == 1) {
			initValue((byte[]) params.get(0));
		}
	}
	
	public Double(GenericApplicationType gat) {
		initValue((byte[]) gat.getValue());
	}
	
	private void initValue(byte[] newValue) {
		content = newValue;
		ByteArrayInputStream bi = new ByteArrayInputStream(newValue);
		DataInputStream in = new DataInputStream(bi);
		value = 0.0;
		try {
			value = java.lang.Double.longBitsToDouble(in.readLong());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getId() {
		return ID;
	}
		
	public double getValue() {
		return value;
	}
	
	public void setValue(double newValue) {
		value = newValue;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(bo);
		try {
			out.writeLong(java.lang.Double.doubleToLongBits(newValue));
			content = bo.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ParameterType getParameterType() {
		return ParameterType.APPLICATION_TYPE;
	}

	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.APPLICATION_TYPE);
		container.setId(ID);
		container.add(new Parameter(ParameterType.APPLICATION_TYPE, content));
		return container;
	}
	
	public static void main(String[] args) {
		double foo = 18.3;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(bo);
		try {
			out.writeLong(java.lang.Double.doubleToLongBits(foo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Object> params = new ArrayList<Object>();
		params.add(bo.toByteArray());
		Double bar = new Double(params);
		System.out.println(String.valueOf(bar.getValue()) + " - " + String.valueOf(bar.getValue() == foo));
		
	}

}
