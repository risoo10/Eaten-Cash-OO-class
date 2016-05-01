package models;

import java.io.*;
import java.util.*;


public class Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// typy pouzivatelov 
	public static String[] userTyp = { "Obycajny pouzivatel", "Rodic", "Master" }; // 3 typy
	
	private List<User> users = new ArrayList<User>();
	
	public List<User> getUsers(){
		return users;
	}
	
	public void setUsers(List<User> newUsers){
		users = newUsers;
	}
	
	public void addUser(User u){
		users.add(u);
	}
	
	public User getUser(String userId){
		for(User u : users){
			if(u.getId() == userId)
				return u;
		}
		return null;
	}
	
	public boolean isUnique(String userId){
		for(User u : users){
			if(userId.equals(u.getId()))
				return false;
		}
		return true;
	}
	
	// Vrati iba pouzivatelov typu SimpleUser
	public List<SimpleUser> getSimpleUsers(){
		List<SimpleUser> simpleUsers = new ArrayList<>();
		for(User user : users){
			if(user instanceof SimpleUser)
				simpleUsers.add((SimpleUser) user);
		}
		
		return simpleUsers;
	}
	
	// Vrati iba pouzivatelov typu Rodic
	public List<Rodic> getRodicUsers(){
		List<Rodic> rodicUsers = new ArrayList<>();
		for(User user : users){
			if(user instanceof Rodic)
					rodicUsers.add((Rodic) user);
		}
		return rodicUsers;
	}
	
	
	
}
