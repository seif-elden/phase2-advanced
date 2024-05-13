package com.example.yarab2;

import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;



public class NetworkService {
    private Map<String, profile> profiles = new HashMap<>();
    public profile currentUser;

    public NetworkService() {
        try {
            FileInputStream fileInput = new FileInputStream("C:/Users/DELL/IdeaProjects/yarab2/src/main/java/com/example/yarab2/data.txt");
            ObjectInputStream objectInput
                    = new ObjectInputStream(fileInput);
            profiles = (HashMap)objectInput.readObject();


            objectInput.close();
            fileInput.close();
        }

        catch (IOException obj1) {
            obj1.printStackTrace();

        }
        catch (ClassNotFoundException obj2) {
            System.out.println("Class not found");
            obj2.printStackTrace();
        }

    }
    public boolean checkusername(String username){
        return username.contains(" ");
    }
    public boolean checkpassword(String password){
        return password.length() <= 8;
    }


    public boolean registerUser(String username , String password , String email, String bio, String profilePicUrl) {

        if (checkusername(username) || checkpassword(password) ) {
            return false;
        }


        if (!profiles.containsKey(username)) {
            profiles.put(username, new profile(username, password , email ,  bio, profilePicUrl));
            db.update(HelloApplication.getNetworking().getprofiles());
            return true;
        }
        return false; // User already exists
    }

    public profile loginUser(String username, String password) {
        profile user = profiles.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println(username + password );

            return user;

        }
        return null; // Login failed
    }


    public Map<String, profile> getprofiles() {
        return profiles;
    }

    public profile getfriend(String username){
        return profiles.get(username);
    }


}

