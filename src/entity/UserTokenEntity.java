package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="user_token")
public class UserTokenEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="utok_id")
	private int id;
	
	@Column(name="utok_user")
	private int user;
	
	@Column(name="utok_key")
	private String key;
	
	@Column(name="utok_created_at")
	private String createdAt;
	
	@Column(name="utok_expire_at")
	private String expireAt;
	
	public UserTokenEntity() {
	}
	
	public UserTokenEntity(int user, String key, String createdAt, String expireAt) {
		this.user = user;
		this.key = key;
		this.createdAt = createdAt;
		this.expireAt = expireAt;
	}
	
	public UserTokenEntity(int id, int user, String key, String createdAt, String expireAt) {
		this.id = id;
		this.user = user;
		this.key = key;
		this.createdAt = createdAt;
		this.expireAt = expireAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(String expireAt) {
		this.expireAt = expireAt;
	}

}
