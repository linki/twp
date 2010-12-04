package twp.generated;

import java.util.List;

import twp.core.Container;
import twp.core.GenericStruct;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class StatResult implements Container {
	private int size;
    private int mtime;  /* seconds since the Unix epoch */
    private int atime;
	
    public StatResult(List<Object> content) {
		if (content.size() == 3) {
			size = (Integer) content.get(0);
			mtime = (Integer) content.get(1);
			atime = (Integer) content.get(2);
		}
	}
    
    public StatResult(GenericStruct struct) {
    	size = (Integer) struct.getElements().get(0);
    	mtime = (Integer) struct.getElements().get(1);
    	atime = (Integer) struct.getElements().get(2);
    }
	
	public int getSize() {
		return size;
	}
	
	public int getMtime() {
		return mtime;
	}
	
	public int getAtime() {
		return atime;
	}

	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.STRUCT);
		container.add(new Parameter(ParameterType.LONG_INTEGER, size));
		container.add(new Parameter(ParameterType.LONG_INTEGER, mtime));
		container.add(new Parameter(ParameterType.LONG_INTEGER, atime));
		return container;
	}

	@Override
	public ParameterType getParameterType() {
		return ParameterType.STRUCT;
	}
	
}
