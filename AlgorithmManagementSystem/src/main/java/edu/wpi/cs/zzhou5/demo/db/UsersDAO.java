package edu.wpi.cs.zzhou5.demo.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                resultSet.close();
                return false;
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
	
	private User generateUser(ResultSet resultSet) throws Exception {
        String username  = resultSet.getString("username");
        String password = resultSet.getString("password");
        int role = resultSet.getInt("role");
        return new User (username, password,role);
    }
}
