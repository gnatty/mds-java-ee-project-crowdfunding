package entity;

public class FormErrorEntity {
	
	private String name;
	private String message;
	private String code;

	public FormErrorEntity(String name, String code, String message) {
		this.name = name;
		this.message = message;
		this.code = code;
	} 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
