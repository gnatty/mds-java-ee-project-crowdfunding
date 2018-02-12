package dao;

import java.util.ArrayList;
import java.util.List;
import entity.UserEntity;
import entity.UserTokenEntity;

public class UserDAO extends DAO  {
	
	public static ArrayList<UserEntity> users = new ArrayList<UserEntity>();

	public UserEntity bin(String username, String password, int role, String mail, String firstname, String lastname, String credit) {
		return new UserEntity(username, password, role, mail, firstname, lastname, credit);
	}
	
	public String register(String username, String password, String mail, String firstname, String lastname) {
		if(this.isUsernameExist(username)) {
			return "USERNAME_ALREADY_EXIST";
		}
		
		UserEntity user = new UserEntity(username, password, 0, mail, firstname, lastname, "1000");
		init();
		getT().begin();
		getEm().persist(user);
		getT().commit();
		destroy();
		return "ACCOUNT_CREATED";
	}
	
	@SuppressWarnings("unchecked")
	public List<UserEntity> login(String username, String password) {
		String query = "SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password";
		init();
		getT().begin();
		List<UserEntity> res = getEm().createQuery(query)
				.setParameter("username", username)
				.setParameter("password", password)
				.getResultList();
		getT().commit();
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserEntity> list() {
		String query = "SELECT u FROM UserEntity u";
		init();
		getT().begin();
		List<UserEntity> res = getEm().createQuery(query)
				.getResultList();
		getT().commit();
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isUsernameExist(String username) {
		String query = "SELECT u FROM UserEntity u WHERE u.username = :username";
		List<UserEntity> res;
		init();
		getT().begin();
		res = getEm().createQuery(query)
				.setParameter("username", username)
				.getResultList();
		getT().commit();
		if(res.size() == 0) {
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public UserEntity getUserById(int id) {
		String query = "SELECT u FROM UserEntity u WHERE u.id = :id";
		init();
		getT().begin();
		List<UserEntity> res = getEm().createQuery(query)
				.setParameter("id", id)
				.getResultList();
		getT().commit();
		if(res.size() == 0) {
			// --- return null
			return null;
		}
		return res.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public UserEntity getUserByToken(String token) {
		UserTokenDAO userTokenDAO = new UserTokenDAO();
		List<UserTokenEntity> findUser = userTokenDAO.getData(token);
		if(findUser.size() == 0) {
			// --- return null
			return null;
		}
		
		String query = "SELECT u FROM UserEntity u WHERE u.id = :id";
		init();
		getT().begin();
		List<UserEntity> res = getEm().createQuery(query)
				.setParameter("id", findUser.get(0).getUser())
				.getResultList();
		getT().commit();
		if(res.size() == 0) {
			// --- return null
			return null;
		}
		return res.get(0);
	}
	
	public void update(UserEntity user) {
		init();
		getT().begin();
		getEm().merge(user);
		getT().commit();
	}

}
