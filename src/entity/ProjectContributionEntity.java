package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project_contribution")
public class ProjectContributionEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pcon_id")
	private int id;
	
	@Column(name="pcon_user")
	private int user;
	
	@Column(name="pcon_project")
	private int project;
	
	@Column(name="pcon_amount")
	private String amount;

	public ProjectContributionEntity() {
	}
	
	public ProjectContributionEntity(int user, int project, String amount) {
		this.user = user;
		this.project = project;
		this.amount = amount;
	}
	
	public ProjectContributionEntity(int id, int user, int project, String amount) {
		this.id = id;
		this.user = user;
		this.project = project;
		this.amount = amount;
	}

}
