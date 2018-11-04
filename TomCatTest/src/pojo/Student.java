package pojo;

public class Student {
	
	private String sname;
	private String spass;
	
	
	public Student(String name,String pass) {
		sname = name;
		spass = pass;
	}
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSpass() {
		return spass;
	}
	public void setSpass(String spass) {
		this.spass = spass;
	}
	

}
