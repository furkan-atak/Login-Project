package src_entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User_Table")
public class User implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String userName;
	private int password;
	private String firstName;
	private String lastName;
	private String mailAddress;
	private String type;
	
	public User() {
		super();
	}
	public User(int id,String firstName,String secName,String usrName,int password,String mail) {
		super();
		this.firstName = firstName;
		this.lastName = secName;
		this.mailAddress = mail;	
		this.id = id;
		this.userName=usrName;
		this.password = password;
		this.type="user";
	}
	public User(int id,String firstName,String secName,String usrName,int password,String mail,String type) {
		super();
		this.firstName = firstName;
		this.lastName = secName;
		this.mailAddress = mail;	
		this.id = id;
		this.userName=usrName;
		this.password = password;
		this.type = type;
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
	public int getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	@Override
	public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final User other = (User) obj;
	        if ((this.id == 0) ? (other.id != 0) : !(this.id == other.id)) {
	            return false;
	        }
	        return true;
	    }
	
	
}
