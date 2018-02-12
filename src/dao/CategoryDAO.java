package dao;

import java.util.ArrayList;
import java.util.List;

import entity.CategoryEntity;
import entity.UserEntity;

public class CategoryDAO extends DAO  {
	
	@SuppressWarnings("unchecked")
	public List<CategoryEntity> list() {
		String query = "SELECT c FROM CategoryEntity c";
		init();
		getT().begin();
		List<CategoryEntity> res = getEm().createQuery(query)
				.getResultList();
		getT().commit();
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isExistById(int id) {
		String query = "SELECT c FROM CategoryEntity c WHERE c.id = :id";
		init();
		getT().begin();
		List<CategoryEntity> res = getEm().createQuery(query)
				.setParameter("id", id)
				.getResultList();
		getT().commit();
		if(res.size() == 0) {
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public CategoryEntity getByLabel(String label) {
		String query = "SELECT c FROM CategoryEntity c WHERE c.label = :label";
		init();
		getT().begin();
		List<CategoryEntity> res = getEm().createQuery(query)
				.setParameter("label", label)
				.getResultList();
		getT().commit();
		if(res.size() == 0) {
			return null;
		}
		return res.get(0);
	}
	
}
