package twp.generated;

import java.util.List;

import twp.core.Container;
import twp.core.GenericSequence;
import twp.core.GenericStruct;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class ListResult implements Container {
	private Filelist  directories ;
	private Filelist  files ;

	
	public ListResult(List<Object> params) {
		if (params.size() == 2) {
			directories = new Filelist((GenericSequence ) params.get(0));
			files = new Filelist((GenericSequence ) params.get(1));

		}
	}
	
	public ListResult(GenericStruct struct) {
		directories = new Filelist((GenericSequence ) struct.getElements().get(0));
		files = new Filelist((GenericSequence ) struct.getElements().get(1));
	
	}
	

	public Filelist  getDirectories() {
			return directories;
		}
		
		public void setDirectories(Filelist  directories) {
			this.directories = directories;
		}

	public Filelist  getFiles() {
			return files;
		}
		
		public void setFiles(Filelist  files) {
			this.files = files;
		}

	
	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.STRUCT);
		container.add(FAMProtocol.decompose(directories));
		container.add(FAMProtocol.decompose(files));

		return container;
	}

	@Override
	public ParameterType getParameterType() {
		return ParameterType.STRUCT;
	}
}
