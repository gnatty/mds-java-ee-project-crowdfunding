package dao;

import java.util.List;

import entity.ProjectContributionEntity;
import entity.ProjectEntity;

public class ProjectContributionDAO extends DAO {
	
	@SuppressWarnings("unchecked")
	public int getAmoundByProjectId(int project) {
		String query = "SELECT p FROM ProjectContributionEntity p WHERE project = :project";
		init();
		getT().begin();
		List<ProjectContributionEntity> res = getEm().createQuery(query)
				.setParameter("project", project)
				.getResultList();
		getT().commit();
		if(res.size() == 0) {
		}
		int amount = 0;
		for(ProjectContributionEntity contri : res) {
			amount += Integer.parseInt(contri.getAmount());
		}
		return amount;
	}
	
	public ProjectContributionEntity create(int user, int project, String amount) {
		ProjectContributionEntity contri = new ProjectContributionEntity(user, project, amount);
		init();
		getT().begin();
		getEm().persist(contri);
		getEm().flush();
		getT().commit();
		destroy();
		return contri;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectContributionEntity> getByProjectId(int project) {
		String query = "SELECT p FROM ProjectContributionEntity p WHERE project = :project";
		init();
		getT().begin();
		List<ProjectContributionEntity> res = getEm().createQuery(query)
				.setParameter("project", project)
				.getResultList();
		getT().commit();
		return res;
	}

}
