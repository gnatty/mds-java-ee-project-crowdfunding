package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="user")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="use_id")
	private int id;
	
	@Column(name="use_username")
	private String username;
	
	@Column(name="use_password")
	private String password;
	
	@Column(name="use_role")
	private int role;
	
	@Column(name="use_firstname")
	private String firstname;
	
	@Column(name="use_lastname")
	private String lastname;
	
	@Column(name="use_mail")
	private String mail;
	
	@Column(name="use_credit")
	private String credit;

	public UserEntity() {
	}
	
	public UserEntity(String username, String password, int role, String mail, String firstname, String lastname, String credit) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.mail = mail;
		this.firstname = firstname;
		this.lastname = lastname;
		this.credit = credit;
	}
	
	public UserEntity(int id, String username, String password, int role, String mail, String firstname, String lastname, String credit) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.mail = mail;
		this.firstname = firstname;
		this.lastname = lastname;
		this.credit = credit;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
}
