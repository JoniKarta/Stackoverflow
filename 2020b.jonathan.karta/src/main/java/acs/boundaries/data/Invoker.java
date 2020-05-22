package acs.boundaries.data;

public class Invoker {
	private String email;

	
	public Invoker() {
	}


	public Invoker(String email) {
		super();
		this.email = email;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Invoker [email=" + email + "]";
	}
}
