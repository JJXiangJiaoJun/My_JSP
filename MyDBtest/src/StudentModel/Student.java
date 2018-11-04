package StudentModel;

import GradeModel.Grade;

public class Student {

	private String sname;
	private String saddress;
	private String smajor;
	private int id;
	private String slikes;
	//添加一个班级对象，这样班级的所有信息都可以放在此对象中
	private Grade grade;

	public Grade getGrade() {
		return grade;
	}


	public void setGrade(Grade grade) {
		this.grade = grade;
	}



	public String getSlikes() {
		return slikes;
	}


	public void setSlikes(String slikes) {
		this.slikes = slikes;
	}

	public Student()
	{
		 
	}
	
	public Student(String sname,String saddress,String smajor)
	{
		 this.sname =sname;
		 this.saddress = saddress;
		 this.smajor = smajor;
	}
	
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	public String getSmajor() {
		return smajor;
	}
	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}
