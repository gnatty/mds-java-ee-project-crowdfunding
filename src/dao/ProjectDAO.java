package dao;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import entity.CategoryEntity;
import entity.ProjectEntity;

public class ProjectDAO extends DAO {
	
	public ProjectEntity create(int user, int category, String name, String description, String amount, String createdAt, String endAt) {
		ProjectEntity project = new ProjectEntity(user, category, name, description, amount, createdAt, endAt);
		init();
		getT().begin();
		getEm().persist(project);
		getEm().flush();
		getT().commit();
		destroy();
		return project;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectEntity> list() {
		String query = "SELECT p FROM ProjectEntity p";
		init();
		getT().begin();
		List<ProjectEntity> res = getEm().createQuery(query)
				.getResultList();
		getT().commit();
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public ProjectEntity getById(int id) {
		String query = "SELECT p FROM ProjectEntity p WHERE p.id = :id";
		init();
		getT().begin();
		List<ProjectEntity> res = getEm().createQuery(query)
				.setParameter("id", id)
				.getResultList();
		getT().commit();
		if(res.size() == 0) {
			return null;
		}
		return res.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectEntity> getByCatId(int cat) {
		String query = "SELECT p FROM ProjectEntity p WHERE p.category = :cat";
		init();
		getT().begin();
		List<ProjectEntity> res = getEm().createQuery(query)
				.setParameter("cat", cat)
				.getResultList();
		getT().commit();
		return res;
	}
	
}
