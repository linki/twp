package twp.generated;

import java.util.List;

import twp.core.Container;
import twp.core.GenericStruct;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class StatResult implements Container {
	private int  size ;
	private int  mtime ;
	private int  atime ;

	
	public StatResult(List<Object> params) {
		if (params.size() == 3) {
			size = (Integer) params.get(0);

			mtime = (Integer) params.get(1);

			atime = (Integer) params.get(2);


		}
	}
	
	public StatResult(GenericStruct struct) {
		size = (Integer) struct.getElements().get(0);

		mtime = (Integer) struct.getElements().get(1);

		atime = (Integer) struct.getElements().get(2);

	
	}
	

	public int  getSize() {
			return size;
		}
		
		public void setSize(int  size) {
			this.size = size;
		}

	public int  getMtime() {
			return mtime;
		}
		
		public void setMtime(int  mtime) {
			this.mtime = mtime;
		}

	public int  getAtime() {
			return atime;
		}
		
		public void setAtime(int  atime) {
			this.atime = atime;
		}

	
	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.STRUCT);
		container.add(FAMProtocol.decompose(size));
		container.add(FAMProtocol.decompose(mtime));
		container.add(FAMProtocol.decompose(atime));

		return container;
	}

	@Override
	public ParameterType getParameterType() {
		return ParameterType.STRUCT;
	}
}
