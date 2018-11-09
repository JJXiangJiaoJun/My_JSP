package Form;

import org.apache.struts.action.ActionForm;

//Form bean表单类

public class HelloForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	
	
}
