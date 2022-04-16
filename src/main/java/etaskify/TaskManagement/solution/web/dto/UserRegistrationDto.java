package etaskify.TaskManagement.solution.web.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserRegistrationDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String org_name;
	private String adress;
	private long phone;

	public UserRegistrationDto(String firstName, String lastName, String email, String password, String org_name,
							   String adress, long phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.org_name = org_name;
		this.adress = adress;
		this.phone = phone;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getOrg_name() {
		return this.org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public long getPhone() {
		return this.phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
