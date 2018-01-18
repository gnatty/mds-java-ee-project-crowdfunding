package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="user")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="use_id")
	private int id;
	
	@Column(name="use_username")
	private String username;
	
	@Column(name="use_password")
	private String password;
	
	@Column(name="use_role")
	private int role;
	
	public UserEntity(String username, String password, int role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public UserEntity(int id, String username, String password, int role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
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
