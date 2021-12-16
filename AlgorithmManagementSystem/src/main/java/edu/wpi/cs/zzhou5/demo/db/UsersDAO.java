package edu.wpi.cs.zzhou5.demo.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.zzhou5.demo.model.Activity;
import edu.wpi.cs.zzhou5.demo.model.User;
import edu.wpi.cs.zzhou5.demo.db.DatabaseUtil;

public class UsersDAO {
	
	java.sql.Connection conn;
	
	final String tblName = "Users";   // Exact capitalization

	public UsersDAO() {
		try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}	
	}
	
	public User getUser(String name) throws Exception {
        
        try {
            User user = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE username=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                user = generateUser(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return user;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting user: " + e.getMessage());
        }
    }
	
	public boolean addUser(User user) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE username = ?;");
            ps.setString(1, user.username);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
//                User c = generateUser(resultSet);
//                resultSet.close();
//                return false;
            }

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (username,password,role) values(?,?,?);");
            ps.setString(1,  user.username);
            ps.setString(2,  user.password);
            ps.setInt(3, user.role);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert user: " + e.getMessage());
        }
    }
	
	public ArrayList<User> getUsers() throws Exception{
		try {
			ArrayList<User> users = new ArrayList<User>();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users;");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				User user = generateUser(resultSet);
				users.add(user);
			}
			return users;
		}catch (Exception e) {
            throw new Exception("Failed to get users: " + e.getMessage());
        }
	}
	
	public boolean deleteUser(String username) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Users WHERE username = ?;");
			ps.setString(1, username);
			ps.execute();
			ps.close();
			return true;
		}catch(Exception e) {
            throw new Exception("Failed to delete user: " + e.getMessage());
        }
	}
	
	public boolean uploadActivity(String name, String activity, String time) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Activities (user,activity,time) values(?,?,?);");
			ps.setString(1,name);
			ps.setString(2,activity);
			ps.setString(3,time);
			ps.execute();
			return true;
		}catch(Exception e) {
            throw new Exception("Failed to upload activity: " + e.getMessage());
        }
	}
	
	public ArrayList<Activity> getActivityForUser(String name) throws Exception {
		try {
			ArrayList<Activity> activities = new ArrayList<Activity>();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Activities WHERE user = ?;");
			ps.setString(1,name);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
//				Activity activity = generateActivity(resultSet);
//				activities.add(activity);
			}
			return activities;
		}catch(Exception e) {
            throw new Exception("Failed to get activity: " + e.getMessage());
        }
	}
	
	private User generateUser(ResultSet resultSet) throws Exception {
        String username  = resultSet.getString("username");
        String password = resultSet.getString("password");
        int role = resultSet.getInt("role");
        return new User (username, password,role);
    }
	
//	private Activity generateActivity(ResultSet resultSet) throws Exception{
//		String user = resultSet.getString("user");
//		String time = resultSet.getString("time");
//		String activity = resultSet.getString("activity");
//		return new Activity(user,time,activity);
//	}
}
