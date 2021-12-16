package edu.wpi.cs.zzhou5.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import edu.wpi.cs.zzhou5.demo.model.Algorithm;
import edu.wpi.cs.zzhou5.demo.model.Implementation;

public class ImplementationsDAO {
	java.sql.Connection conn;
	
	final String tblName = "Implementations";   // Exact capitalization

	public ImplementationsDAO() {
		try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}	
	}
	
	public boolean uploadImplementation(Implementation imple) throws Exception {
		try {
			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Algorithms WHERE id=?;");
			ps1.setInt(1, imple.algorithm);
			ResultSet resultSet1 = ps1.executeQuery();
			if(!resultSet1.next()) {
				return false;
			}
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName + " (language,content,algorithm) values(?,?,?);");
            ps.setString(1, imple.language);
            ps.setString(2, imple.content);
            ps.setInt(3, imple.algorithm);
            ps.execute();
            return true;
		}catch (Exception e) {
            throw new Exception("Failed to insert implementation: " + e.getMessage());
        }
	}
	
	public Implementation getImplementation(int id) throws Exception{
		try {
			Implementation imple = null;
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM " + tblName + " WHERE  id="+ id +" ;";
			ResultSet resultSet = statement.executeQuery(query);
			while(!(resultSet.next())) {
				return null;
			}
//			imple = generateImplementation(resultSet);
//			int algo = resultSet.getInt("algorithm");
//			query = "SELECT * FROM Algorithms WHERE  id="+ algo +" ;";
//			resultSet = statement.executeQuery(query);
//			resultSet.next();
//			Algorithm algorithm = generateAlgorithm(resultSet);
//			imple.algo = algorithm;
//			resultSet.close();
//            statement.close();
			return imple;
		}catch (Exception e) {
            throw new Exception("Failed to get implementation: " + e.getMessage());
        }
	}
	
	public boolean deleteImplementation(int id) throws Exception{
		try {
			PreparedStatement ps1 = conn.prepareStatement("DELETE FROM Benchmarks WHERE imple = ?");
			ps1.setInt(1, id);
			ps1.executeUpdate();
			ps1.close();
			PreparedStatement ps2 = conn.prepareStatement("DELETE FROM Implementations WHERE id = ?");
			ps2.setInt(1, id);
			int numAffected = ps2.executeUpdate();
			ps2.close();
			return (numAffected == 1);
		}catch(Exception e){
			 throw new Exception("Failed to delete implementation: " + e.getMessage());
		}
	}
	
//	private Implementation generateImplementation(ResultSet resultSet) throws Exception {
//        String language  = resultSet.getString("language");
//        String content = resultSet.getString("content");
//        int id = resultSet.getInt("id");
//        int algo = resultSet.getInt("algorithm");
//        
//        return new Implementation (language, content,id,algo);
//    }
//	private Algorithm generateAlgorithm(ResultSet resultSet) throws Exception {
//        String name  = resultSet.getString("name");
//        String description = resultSet.getString("description");
//        int id = resultSet.getInt("id");
//        int classification = resultSet.getInt("classification");
//        
//        return new Algorithm (name, classification, description,id);
//    }
}
