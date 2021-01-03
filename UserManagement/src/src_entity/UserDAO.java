package src_entity;

import java.util.List;

public interface UserDAO {
		
		public User insertUser(int id,String firstName,String secName,String usrName,int password,String mail);
		
		public User insertUser(int id,String firstName,String secName,String usrName,int password,String mail,String type);
		
		public void removeUser(int id);
		
		public User findUser(int id);
		
		public List<User> findAllUsers();
		
		
}
