package dao;

import java.util.List;

import entity.ProjectContributionEntity;
import entity.ProjectEntity;

public class ProjectContributionDAO extends DAO {
	
	@SuppressWarnings("unchecked")
	public void getAmoundByProjectId(int project) {
		String query = "SELECT p FROM ProjectContributionEntity p WHERE project = :project";
		init();
		getT().begin();
		List<ProjectContributionEntity> res = getEm().createQuery(query)
				.setParameter("project", project)
				.getResultList();
		getT().commit();
		if(res.size() == 0) {
			// -- return 0.
		}
		int amount = 0;
		res.forEach(item -> {
			System.out.println("oui");
		});
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

}
