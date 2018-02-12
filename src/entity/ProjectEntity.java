package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class ProjectEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pro_id")
	private int id;
	
	@Column(name="pro_user")
	private int user;
	
	@Column(name="pro_category")
	private int category;
	
	@Column(name="pro_name")
	private String name;
	
	@Column(name="pro_description")
	private String description;
	
	@Column(name="pro_amount")
	private String amount;
	
	@Column(name="pro_created_at")
	private String createdAt;
	
	@Column(name="pro_end_at")
	private String endAt;
	
	private String dayLeft;
	
	public ProjectEntity() {
	}

	public ProjectEntity(int user, int category, String name, String description, String amount, String createdAt, String endAt) {
		this.user = user;
		this.category = category;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.createdAt = createdAt;
		this.endAt = endAt;
	}
	
	public ProjectEntity(int id, int user, int category, String name, String description, String amount, String createdAt, String endAt) {
		this.id = id;
		this.user = user;
		this.category = category;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.createdAt = createdAt;
		this.endAt = endAt;
	}

	
	
	public String getDayLeft() {
		return dayLeft;
	}

	public void setDayLeft(String dayLeft) {
		this.dayLeft = dayLeft;
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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getEndAt() {
		return endAt;
	}

	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}

}
