package src_bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import src_entity.User;
import src_entity.UserDAO;
import src_entity.UserImp;

@Named("usrBean")
@ConversationScoped
public class UserBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserManagement");
	
	private User selectedUser;
	private int id;
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	private String userName;
	private int password;
	private String firstName;
	private String lastName;
	private String mailAddres;	
	private boolean showTable=false;
    
	@PostConstruct
	public void init() {
		selectedUser = new User();		
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public void select(User usr) {
		usr = selectedUser;
	}
	
	public User getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isShowTable() {
		return showTable;
	}
	public void setShowTable(boolean showTable) {
		this.showTable = showTable;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMailAddres() {
		return mailAddres;
	}
	public void setMailAddres(String mailAddres) {
		this.mailAddres = mailAddres;
	}
	public String login() {
		if(("".equalsIgnoreCase(firstName) || firstName == null) &&
			("".equalsIgnoreCase(lastName) || lastName == null) &&
			("".equalsIgnoreCase(mailAddres) || mailAddres == null)) {
			return "";
		}else {
			EntityManager em = emf.createEntityManager();
			EntityTransaction ets = em.getTransaction();
			
			UserDAO userDao = new UserImp(em);
			int tempId=1;
			Query qId =	em.createQuery("select u.id from User u");
			List<Integer> idList = qId.getResultList();
			for (Integer i : idList) {
				if(i > tempId){
					tempId = i;
				}
				tempId++;
			}
			ets.begin();
			User newUser = userDao.insertUser(tempId,firstName, lastName, userName, password, mailAddres);
			ets.commit();
			return "Welcome "+firstName+",";
		}
		
	}
	
	public String registeredUsr() {
		if(("".equalsIgnoreCase(firstName) || firstName == null) &&
				("".equalsIgnoreCase(lastName) || lastName == null) &&
				("".equalsIgnoreCase(mailAddres) || mailAddres == null)) {
				return "";
			}else {
				EntityManager em = emf.createEntityManager();
				UserDAO userDao = new UserImp(em);
				if(userDao.findUser(id).getPassword() == password &&             // Username & pw kontrol
						userDao.findUser(id).getUserName().equals(userName)) {
					if(userDao.findUser(getId()).getType().equalsIgnoreCase("Admin")) {
					return "adminPage.xhtml";
					}else {
					return "userPage.xhtml";
					}
				}
				return "";
			}
	}
	
	public String deleteUser() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction ets = em.getTransaction();
		UserDAO userDao = new UserImp(em);
		if(selectedUser != null) {
			ets.begin();
			userDao.removeUser(selectedUser.getId());
			ets.commit();
			return "Selected user is deleted.";
		}
		return "";
	}
	public void updateUser() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction ets = em.getTransaction();
		UserDAO userDao = new UserImp(em);
		ets.begin();
		userDao.findUser(selectedUser.getId()).setFirstName(firstName);
		userDao.findUser(selectedUser.getId()).setLastName(lastName);
		userDao.findUser(selectedUser.getId()).setMailAddress(mailAddres);
		ets.commit(); // kontrol yok 
	}
	public boolean makeTableVisible() {
		this.showTable = true;
		return this.showTable;
	}
	
	public List<User> listOfUsers() {
		EntityManager em = emf.createEntityManager();
		UserDAO userDao = new UserImp(em);
		return userDao.findAllUsers();
	}
	
}
