package twp.generated;

import java.util.List;

public class StatResult {
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
	
	public int getSize() {
		return size;
	}
	
	public int getMtime() {
		return mtime;
	}
	
	public int getAtime() {
		return atime;
	}
	
}
