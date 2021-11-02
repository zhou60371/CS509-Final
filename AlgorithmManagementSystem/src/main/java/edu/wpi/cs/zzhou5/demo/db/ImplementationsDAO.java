package edu.wpi.cs.zzhou5.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName + " (language,context,algorithm) values(?,?,?);");
            ps.setString(1, imple.language);
            ps.setString(2, imple.context);
            ps.setInt(3, imple.algorithm);
            ps.execute();
            return true;
		}catch (Exception e) {
            throw new Exception("Failed to insert implementation: " + e.getMessage());
        }
	}
}
