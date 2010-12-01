package twp.generated;

import java.util.ArrayList;
import java.util.List;

public class ListResult {
	List<String> dirs = new ArrayList<String>();
	List<String> files = new ArrayList<String>();
	
	public ListResult(List<Object> params) {
		if (params.size() == 2) {
			if (params.get(0) != null) {
				for (Object elem:(List<Object>) params.get(0)) {
					dirs.add((String) elem);
				}
			}
			if (params.get(1) != null) {
				for (Object elem:(List<Object>) params.get(1)) {
					files.add((String) elem);
				}
			}
		}
	}
	
	public List<String> getDirectories() {
		return dirs;
	}
	
	public List<String> getFiles() {
		return files;
	}
}
