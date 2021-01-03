package src_entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


public class UserImp implements UserDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	public UserImp(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public User insertUser(int id,String firstName,String secName,String usrName,int password,String mail) {
		User newUser = new User(id,firstName,secName,usrName,password,mail);
		em.persist(newUser);
		return newUser;
	}
	
	@Override
	public User findUser(int id) {
		return em.find(User.class,id);
	}
	
	@Override
	public List<User> findAllUsers() {
		Query userQ = em.createQuery("select u from User u");
		List<User> userList = userQ.getResultList();
		return userList;
	}
	
	@Override
	public void removeUser(int id) {
		User removedSum = findUser(id);
		if (removedSum != null) {
			em.remove(removedSum);			
		}
	}
	@Override
	public User insertUser(int id,String firstName,String secName,String usrName,int password,String mail,String type) {
		User newUser = new User(id,firstName,secName,usrName,password,mail,type);
		em.persist(newUser);
		return newUser;
	}
}
