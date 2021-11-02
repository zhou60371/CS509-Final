package edu.wpi.cs.zzhou5.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.zzhou5.demo.db.DatabaseUtil;
import edu.wpi.cs.zzhou5.demo.model.Algorithm;

public class AlgorithmsDAO {
java.sql.Connection conn;
	
	final String tblName = "Algorithms";   // Exact capitalization

    public AlgorithmsDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Algorithm getAlgorithm(String name) throws Exception {
        
        try {
        	Algorithm algo = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                algo = generateAlgorithm(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return algo;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }
    
    public boolean addAlgorithm(Algorithm algo) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name = ?;");
            ps.setString(1, algo.name);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
            	Algorithm c = generateAlgorithm(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (name,description,stars,classification) values(?,?,?,?);");
            ps.setString(1,  algo.name);
            ps.setString(2,  algo.description);
            ps.setInt(3, algo.stars);
            ps.setInt(4, algo.classification);
            ps.execute();
            return true;

        } catch (Exception e) {
        	
            throw new Exception("Failed to insert algorithm: " + e.getMessage());
        }
    }
    
    public List<Algorithm> getAlgorithms(String id) throws Exception{
    	List<Algorithm> algos = new ArrayList<>();
    	int classificationID = Integer.parseInt(id);
    	try {
    		Statement statement = conn.createStatement();
            String query = "SELECT * FROM " + tblName + " WHERE classification = "+ classificationID + " ;";
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
            	Algorithm algo = generateAlgorithm(resultSet);
            	algos.add(algo);
            }
            resultSet.close();
            statement.close();
            
            return algos;
            
    	}catch(Exception e) {
			throw new Exception("Failed in getting algorithms: " + e.getMessage());
		}
    }
    
    private Algorithm generateAlgorithm(ResultSet resultSet) throws Exception {
        String name  = resultSet.getString("name");
        String description = resultSet.getString("description");
        int stars = resultSet.getInt("stars");
        int id = resultSet.getInt("id");
        int classification = resultSet.getInt("classification");
        
        return new Algorithm (name, classification, description, stars,id);
    }
}
