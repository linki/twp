package twp.generated;

public class Term<T> {
	
	public static Class getType(int id) {
		//if (id == 0)
			return int.class;
		
	}
	
	private T value;
	
	public Term(T content) {
		value = content;
	}
	
	public T getValue() {
		return value;
	}
}
