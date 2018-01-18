package dao;

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
	
	
	
	
}
