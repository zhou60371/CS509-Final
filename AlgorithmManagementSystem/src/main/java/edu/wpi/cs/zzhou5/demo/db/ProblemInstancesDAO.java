package edu.wpi.cs.zzhou5.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.wpi.cs.zzhou5.demo.model.Algorithm;
import edu.wpi.cs.zzhou5.demo.model.Benchmark;
import edu.wpi.cs.zzhou5.demo.model.ProblemInstance;

public class ProblemInstancesDAO {
	java.sql.Connection conn;
	
	final String tblName = "ProblemInstances";   // Exact capitalization

	public ProblemInstancesDAO() {
		try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}	
	}
	
	public boolean uploadProblemInstance(ProblemInstance pi) throws Exception{
		try {
			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM "+ tblName + " WHERE caseType = ? AND algorithm = ? AND user = ?; ");
			ps1.setInt(1,pi.caseType);
			ps1.setInt(2, pi.algorithm);
			ps1.setString(3, pi.user);
			ResultSet resultSet1 = ps1.executeQuery();
			if(resultSet1.next()) {
				return false;
			}
			ps1.close();
			PreparedStatement ps2 = conn.prepareStatement("INSERT INTO " + tblName + " (fileAdress,caseType,algorithm,user) values(?,?,?,?);");
			ps2.setString(1, pi.fileAdress);
			ps2.setInt(2, pi.caseType);
			ps2.setInt(3, pi.algorithm);
			ps2.setString(4, pi.user);
			ps2.execute();
			return true;
		}catch(Exception e) {
			throw new Exception("Failed to upload problem instance: " + e.getMessage());
		}
	}
	
	public Map<String, ArrayList<ProblemInstance>> getProblemInstance(int id) throws Exception{
		try {
			Map<String, ArrayList<ProblemInstance>> pisMap = new HashMap<>();
			PreparedStatement ps1 = conn.prepareStatement("SELECT distinct user FROM "+ tblName + " WHERE algorithm=?;" ,
                    ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
			ps1.setInt(1, id);
			ResultSet resultSet1 = ps1.executeQuery();
			if(!resultSet1.next()){
				return null;
			}	
			resultSet1.beforeFirst();
			while(resultSet1.next()) {
				ArrayList<ProblemInstance> pisList = new ArrayList<ProblemInstance>();
				//查询best case
				PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE caseType = ? and algorithm = ? and user = ?");
				ps2.setInt(1, 0);
				ps2.setInt(2, id);
				ps2.setString(3, resultSet1.getString("user"));
				ResultSet resultSet2 = ps2.executeQuery();
				if(resultSet2.next()) {
					ProblemInstance pi0 = generateProblemInstance(resultSet2);
					PreparedStatement ps4 = conn.prepareStatement("SELECT * FROM Benchmarks WHERE problemInstance = ?",
                            ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
					ps4.setInt(1, pi0.id);
					ResultSet resultSet4 = ps4.executeQuery();
					if(!resultSet4.next()) {
						pi0.benchmarks = new ArrayList<Benchmark>();
					}else {
						resultSet4.beforeFirst();
						pi0.benchmarks = new ArrayList<Benchmark>();
						while(resultSet4.next()) {
							Benchmark bk = generateBenchmark(resultSet4);
							
							pi0.benchmarks.add(bk);
						}
					}
					ps4.close();
					resultSet4.close();
					pisList.add(pi0);
				}
				ps2.close();
				resultSet2.close();
				//查询worst case
				PreparedStatement ps3 = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE caseType = ? and algorithm = ? and user = ?");
				ps3.setInt(1, 1);
				ps3.setInt(2, id);
				ps3.setString(3, resultSet1.getString("user"));
				ResultSet resultSet3 = ps3.executeQuery();
				if(resultSet3.next()) {
					ProblemInstance pi1 = generateProblemInstance(resultSet3);
					PreparedStatement ps5 = conn.prepareStatement("SELECT * FROM Benchmarks WHERE problemInstance = ?",
                            ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
					ps5.setInt(1, pi1.id);
					ResultSet resultSet5 = ps5.executeQuery();
					if(!resultSet5.next()) {
						pi1.benchmarks = new ArrayList<Benchmark>();
					}else {
						resultSet5.beforeFirst();
						pi1.benchmarks = new ArrayList<Benchmark>();
						while(resultSet5.next()) {
							Benchmark bk = generateBenchmark(resultSet5);
							pi1.benchmarks.add(bk);
						}
					}
					ps5.close();
					resultSet5.close();
					pisList.add(pi1);
				}
				ps3.close();
				resultSet3.close();
				
				pisMap.put(resultSet1.getNString("user"), pisList);
			}
			
			ps1.close();
			resultSet1.close();
			return pisMap;
		}catch(Exception e) {
			throw new Exception("Failed to get problem instance: " + e.getMessage());
		}
	}
	
	public boolean deleteProblemInstance(int id) throws Exception {
		try {
			PreparedStatement ps1 = conn.prepareStatement("DELETE FROM Benchmarks WHERE  problemInstance = ?");
			ps1.setInt(1, id);
			ps1.executeUpdate();
			ps1.close();
			PreparedStatement ps2 = conn.prepareStatement("DELETE FROM " + tblName + " WHERE id = ?");
			ps2.setInt(1, id);
			int numAffected = ps2.executeUpdate();
			return (numAffected == 1);
		}catch(Exception e) {
			throw new Exception("Failed to delete problem instance: " + e.getMessage());
		}
	}
	
	private ProblemInstance generateProblemInstance(ResultSet resultset) throws Exception{
		int id = resultset.getInt("id");
		String fileAdress = resultset.getString("fileAdress");
		int caseType = resultset.getInt("caseType");
		int algo = resultset.getInt("algorithm");
		String user = resultset.getNString("user");
		return new ProblemInstance(id,fileAdress,caseType,algo,user);
	}
//	
	private Benchmark generateBenchmark(ResultSet resultset) throws Exception{
		int id = resultset.getInt("id");
		String date = resultset.getString("date");
		String runningTime = resultset.getString("runningTime");
		String L3 = resultset.getString("L3");
		String L2 = resultset.getString("L2");
		String L1 = resultset.getString("L1");
		String threads = resultset.getString("threads");
		String cores = resultset.getString("cores");
		String cache = resultset.getString("cache");
		String cpu = resultset.getString("cpu");
		int imple = resultset.getInt("imple");
		int pi = resultset.getInt("problemInstance");
		return new Benchmark(id,date,runningTime,L1,L2,L3,threads,cores,cache,cpu,imple,pi);
	}
}
