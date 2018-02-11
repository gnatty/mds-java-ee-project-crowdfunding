package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class CategoryEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="cat_id")
	private int id;
	
	@Column(name="cat_name")
	private String name;
	
	@Column(name="cat_label")
	private String label;

	public CategoryEntity() {
	}
	
	public CategoryEntity(String name, String label) {
		this.name = name;
		this.label = label;
	}
	
	public CategoryEntity(int id, String name, String label) {
		super();
		this.id = id;
		this.name = name;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}