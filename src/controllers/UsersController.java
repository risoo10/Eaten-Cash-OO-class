package controllers;

import java.io.*;

import models.MapaObjektov;
import models.Users;

public class UsersController {
	private static Users allUsers = null;
	
	// Serializacia. Prebehne ukladanie vsetkych udajov do suboru.
	public static void ulozVsetkyData(){
		try {
			FileOutputStream fos = new FileOutputStream("VsetkyData.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			allUsers = (Users)MapaObjektov.vratObjekt("users");
			oos.writeObject(allUsers);
			oos.close();
			fos.close();
			
		} catch (IOException e) {
			System.out.println("Nepodarilo sa serializovat vsetky data!");
			System.exit(1);
		}
	}
		
		
	// DeSerializacia. Prebehne nacitanie vsetkych udajov zo suboru.
	public static Users nacitajVsetkyData(){
		try {
			FileInputStream fis = new FileInputStream("VsetkyData.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			allUsers = (Users) ois.readObject();
			ois.close();
			fis.close();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Nepodarilo sa nacitat data zo suboru!");
			System.exit(1);
		}
		
		return allUsers;
	}
	
	public static void setAllUsers(Users users){
		allUsers = users;
	}
	
	public static Users getAllUsers(){
		return allUsers;
	}

}
