package be.abis.exercise.model;

public class Login 
{
	String emailAddress;
	String password;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Login(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
	}
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
}
