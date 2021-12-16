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
            throw new Exception("Failed in getting Algorithm: " + e.getMessage());
        }
    }
    
    public boolean addAlgorithm(Algorithm algo) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name = ?;");
            ps.setString(1, algo.name);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (name,description,classification) values(?,?,?);");
            ps.setString(1,  algo.name);
            ps.setString(2,  algo.description);
            ps.setInt(3, algo.classification);
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
			throw new Exception("Failed in getting all algorithms: " + e.getMessage());
		}
    }
    
    public boolean deleteAlgorithm(int id) throws Exception{
    	try {
    		int impleId = 0;
    		PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Implementations WHERE algorithm = ?");
    		ps1.setInt(1, id);
    		ResultSet resultSet1 = ps1.executeQuery();
    		while(resultSet1.next()) {
    			impleId = resultSet1.getInt("id");
    			PreparedStatement ps2 = conn.prepareStatement("DELETE FROM Benchmarks WHERE imple = ?");
    			ps2.setInt(1, impleId);
    			ps2.executeUpdate();
    			ps2.close();
    			PreparedStatement ps3 = conn.prepareStatement("DELETE FROM Implementations WHERE id = ?");
    			ps3.setInt(1, impleId);
    			ps3.executeUpdate();
    			ps3.close();
    		}
    		ps1.close();
    		resultSet1.close();
    		PreparedStatement ps5 = conn.prepareStatement("DELETE FROM ProblemInstances where algorithm = ?");
    		ps5.setInt(1, id);
    		ps5.executeUpdate();
    		ps5.close();
			PreparedStatement ps4 = conn.prepareStatement("DELETE FROM Algorithms WHERE id = ?");
			ps4.setInt(1, id);
			int numAffected = ps4.executeUpdate();
    		return (numAffected == 1);
    	}catch(Exception e) {
    		throw new Exception("Failed in deleting algorithm: " + e.getMessage());
    	}
    }
    
    public boolean reclassifyAlgorithm(int algoId, int classiId) throws Exception {
    	try {
    		PreparedStatement ps1 = conn.prepareStatement("UPDATE " + tblName + " SET classification= ? WHERE id = ? ;");
    		ps1.setInt(1, classiId);
    		ps1.setInt(2, algoId);
    		int numAffected = ps1.executeUpdate();
    		ps1.close();
    		return (numAffected == 1);
    	}catch(Exception e) {
    		throw new Exception("Failed in reclassifying algorithm: " + e.getMessage());
    	}
    }
    
    private Algorithm generateAlgorithm(ResultSet resultSet) throws Exception {
        String name  = resultSet.getString("name");
        String description = resultSet.getString("description");
        int id = resultSet.getInt("id");
        int classification = resultSet.getInt("classification");
        
        return new Algorithm (name, classification, description,id);
    }
}
