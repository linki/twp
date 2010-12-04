package twp.generated;

import java.util.List;

import twp.core.Container;
import twp.core.GenericSequence;
import twp.core.GenericStruct;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class ListResult implements Container {
	FileList dirs;
	FileList files;
	
	public ListResult(List<Object> params) {
		if (params.size() == 2) {
			dirs = new FileList((GenericSequence) params.get(0));
			files = new FileList((GenericSequence) params.get(1));
		}
	}
	
	public ListResult(GenericStruct struct) {
		dirs = new FileList((GenericSequence) struct.getElements().get(0));
		files = new FileList((GenericSequence) struct.getElements().get(1));
	}
	
	public FileList getDirectories() {
		return dirs;
	}
	
	public FileList getFiles() {
		return files;
	}
	
	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.STRUCT);
		container.add(new Parameter(dirs.getParameterType(), dirs.toContainer()));
		container.add(new Parameter(files.getParameterType(), files.toContainer()));
		return container;
	}

	@Override
	public ParameterType getParameterType() {
		return ParameterType.STRUCT;
	}
}
