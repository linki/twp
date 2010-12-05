package twp.generator.metamodel;

public class Element {

	public int id = -1;
	public String name;
	public String smallName;
	public String bigName;
	
	public void setName(String content) {
		if (content != null && content.length() > 0) {
			name = content;
			bigName = toCamelCase(content);
			if (bigName.length() > 1)
				smallName = bigName.substring(0, 1).toLowerCase() + bigName.substring(1);
			else
				smallName = bigName.toLowerCase();
		}
	}
	
	private String toCamelCase(String content) {
		String[] parts = content.split("_");
		StringBuilder result = new StringBuilder();
		for (String part:parts) {
			if (part.length() > 1)
				result.append(part.substring(0, 1).toUpperCase()).append(part.substring(1));
			else
				result.append(part.toUpperCase());
		}
		return result.toString();
	}
}
