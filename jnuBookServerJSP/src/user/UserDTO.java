package user;

public class UserDTO {
	private int user_id;
	private String user_pw;
	private boolean user_gender;
	private int user_grade;
	private String user_name;
	private boolean adminChecked;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public boolean isUser_gender() {
		return user_gender;
	}
	public void setUser_gender(boolean user_gender) {
		this.user_gender = user_gender;
	}
	public int getUser_grade() {
		return user_grade;
	}
	public void setUser_grade(int user_grade) {
		this.user_grade = user_grade;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public boolean isAdminChecked() {
		return adminChecked;
	}
	public void setAdminChecked(boolean adminChecked) {
		this.adminChecked = adminChecked;
	}
	
}
