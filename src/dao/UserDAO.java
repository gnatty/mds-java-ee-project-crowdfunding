package dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import entity.UserEntity;

public class UserDAO extends DAO  {
	
	public static ArrayList<UserEntity> users = new ArrayList<UserEntity>();

	public UserEntity bin(String username, String password, int role) {
		return new UserEntity(username, password, role);
	}
	
	public void register(UserEntity user) {
		
	}
	
	@SuppressWarnings("unchecked")
	public List<UserEntity> login(String username, String password) {
		String query = "SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password";
		init();
		getT().begin();
		List<UserEntity> e = getEm().createQuery(query)
				.setParameter("username", username)
				.setParameter("password", password)
				.getResultList();
		getT().commit();
		destroy();
		return e;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserEntity> list() {
		String query = "SELECT u FROM UserEntity u";
		init();
		getT().begin();
		List<UserEntity> e = getEm().createQuery(query)
				.getResultList();
		getT().commit();
		destroy();
		return e;
	}
	
	
}
