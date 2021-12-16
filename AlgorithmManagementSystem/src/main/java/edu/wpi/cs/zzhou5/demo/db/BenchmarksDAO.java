package edu.wpi.cs.zzhou5.demo.db;

import java.sql.*;

import edu.wpi.cs.zzhou5.demo.model.Benchmark;
import edu.wpi.cs.zzhou5.demo.db.DatabaseUtil;

public class BenchmarksDAO {
	java.sql.Connection conn;
	
	final String tblName = "Benchmarks";   // Exact capitalization

	public BenchmarksDAO() {
		try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}	
	}
	
	public boolean uploadBenchmark(Benchmark bk) throws Exception{
		try {
			PreparedStatement ps =conn.prepareStatement("INSERT INTO " + tblName + " (date, runningTime, L3, L2, L1, threads, cores, cache, cpu, imple, problemInstance) values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, bk.date);
			ps.setString(2, bk.runingTime);
			ps.setString(3, bk.L3);
			ps.setString(4, bk.L2);
			ps.setString(5, bk.L1);
			ps.setString(6, bk.threads);
			ps.setString(7, bk.cores);
			ps.setString(8, bk.cache);
			ps.setString(9, bk.cpu);
			ps.setInt(10, bk.imple);
			ps.setInt(11, bk.pi);
			ps.execute();
			return true;
		}catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in upload benchmark: " + e.getMessage());
        }
	}
	
	public boolean deleteBenchamrk(int id) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE id = ?");
			ps.setInt(1, id);
			int numAffected = ps.executeUpdate();
			ps.close();
			return (numAffected == 1);
		}catch(Exception e) {
			e.printStackTrace();
            throw new Exception("Failed in delete benchmark: " + e.getMessage());
		}
	}
}
