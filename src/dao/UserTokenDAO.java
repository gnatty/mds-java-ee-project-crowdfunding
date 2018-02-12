package dao;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import com.sun.jmx.snmp.Timestamp;

import entity.UserEntity;
import entity.UserTokenEntity;

public class UserTokenDAO extends DAO {
	
	public final static String expireAt = "172800";
	
	public String createWithUsername(String username) {
		String timestamp = new Timestamp(System.currentTimeMillis()).toString();
		String key = username + timestamp;
		String res = DigestUtils.sha1Hex(key.getBytes());
		return this.base64Encode(res);
	}
	
	public String base64Encode(String data) {
		return Base64.getEncoder().encodeToString(data.getBytes());
	}
	
	public String getCurrentTimestamp() {
		Long cur = System.currentTimeMillis() / 1000;
		return String.valueOf(cur);
	}
	
	public UserTokenEntity create(int user, String username) {
		UserTokenEntity token = new UserTokenEntity();
		token.setUser(user);
		token.setKey(this.createWithUsername(username));
		token.setCreatedAt(this.getCurrentTimestamp());
		token.setExpireAt(expireAt);
		
		init();
		getT().begin();
		getEm().persist(token);
		getT().commit();
		destroy();
		return token;
	}
	
	@SuppressWarnings("unchecked")
	public boolean check(String key) {
		String query = "SELECT u FROM UserTokenEntity u WHERE u.key = :key";
		init();
		getT().begin();
		List<UserTokenEntity> res = getEm().createQuery(query)
				.setParameter("key", key)
				.getResultList();
		getT().commit();
		
		if(res.size() == 0) {
			// --- false.
			return false;
		} else {
			// --- check if token is valid.
			int curTimestamp = Integer.parseInt(this.getCurrentTimestamp());
			int compTimestamp = Integer.parseInt(res.get(0).getCreatedAt()) + Integer.parseInt(res.get(0).getExpireAt());
			if(compTimestamp <= curTimestamp) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UserTokenEntity> getData(String key) {
		String query = "SELECT u FROM UserTokenEntity u WHERE u.key = :key";
		init();
		getT().begin();
		List<UserTokenEntity> res = getEm().createQuery(query)
				.setParameter("key", key)
				.getResultList();
		getT().commit();
		return res;
	}
	
}
