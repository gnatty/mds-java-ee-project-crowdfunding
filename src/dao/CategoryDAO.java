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

}
