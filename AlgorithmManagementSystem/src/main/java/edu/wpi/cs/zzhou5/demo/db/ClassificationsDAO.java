package edu.wpi.cs.zzhou5.demo.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.wpi.cs.zzhou5.demo.model.Algorithm;
import edu.wpi.cs.zzhou5.demo.model.Classification;
import edu.wpi.cs.zzhou5.demo.model.Implementation;
import edu.wpi.cs.zzhou5.demo.db.DatabaseUtil;

public class ClassificationsDAO {
	
	java.sql.Connection conn;
	
	final String tblName = "Classifications";   // Exact capitalization

	public ClassificationsDAO() {
		try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}	
	}
	
	public Classification  getClassification(String name) throws Exception {
        
        try {
        	Classification c = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                c = generateClassification(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return c;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting user: " + e.getMessage());
        }
    }
	
	public boolean addClassification(Classification c) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name = ?;");
            ps.setString(1, c.name);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
//                User c = generateUser(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (name,childrenID,level,fatherID) values(?,?,?,?);");
            ps.setString(1,  c.name);
            String var= "";
            System.out.println(c.childrenID);
            if(c.childrenID == null) {
            	var = "";
            }
//            else if(c.childrenID.length == 1) {
//            	var  = var + c.childrenID[0];
//            }else if(c.childrenID.length > 1) {
//            	var  = var + c.childrenID[0];
//            	for(int i = 1; i<c.childrenID.length;i++ ) {
//            		var = var + "," + c.childrenID[i];
//            	}
//            }
            ps.setString(2,  var);
            ps.setInt(3,  c.level);
            ps.setInt(4,c.fatherID);
            ps.execute();
            
            ps = conn.prepareStatement("SELECT LAST_INSERT_ID();");
            resultSet = ps.executeQuery();
            resultSet.next();
            int childID = resultSet.getInt("LAST_INSERT_ID()");
            
            ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE id = ?;");
            ps.setInt(1, c.fatherID);
            resultSet = ps.executeQuery();
            boolean hasFather = resultSet.next();
            if(!hasFather) {
            	return true;
            }
            
//            String var1 = resultSet.getString("childrenID");
//            if(var1.equals("")) {
//            	var1 = Integer.toString(childID);
//            }else {
//            	var1 = var1 + ","+ childID;
//            }
//            ps = conn.prepareStatement("UPDATE "+ tblName+ " SET childrenID= \""+ var1 +"\" WHERE id ="+c.fatherID+";");
//            ps.execute();
//            resultSet.close();
            return true;
        } catch (Exception e) {
            throw new Exception("Failed to insert classification: " + e.getMessage());
        }
    }
	
//	public List<Classification> getAllClassifications() throws Exception{
//		List<Classification> allClassifications = new ArrayList<>();
//		try {
////			Statement statement = conn.createStatement();
////            String query = "SELECT * FROM " + tblName + ";";
////            ResultSet resultSet = statement.executeQuery(query);
////
////            while (resultSet.next()) {
////                Classification c = generateClassification(resultSet);
////                allClassifications.add(c);
////            }
////            resultSet.close();
////            statement.close();
//            return allClassifications;
//		}catch(Exception e) {
//			throw new Exception("Failed in getting classifications: " + e.getMessage());
//		}
//	}
	
	public Map<String, Classification> getClassificationsHierarchy() throws Exception{
		Map<String, Classification> map = new HashMap<>();
		try {
			Statement statement1 = conn.createStatement();
            String query = "SELECT * FROM " + tblName + " WHERE level = 0 ;";
            ResultSet resultSet1 = statement1.executeQuery(query);
            while(resultSet1.next()) {
            	Classification c = generateClassification(resultSet1);
            	buildTree(c);
            	String key = Integer.toString(c.id);
            	map.put(key,c);
            }
            resultSet1.close();
            statement1.close();
            return map;
		}catch(Exception e) {
			throw new Exception("Failed in getting classifications: " + e.getMessage());
		}
	}
	private void buildTree(Classification c) throws Exception{
		if(c.childrenID == null) {
			Statement statement3 =  conn.createStatement();
			String query3 = "SELECT * FROM Algorithms WHERE classification =" + c.id + ";";
			ResultSet resultSet3 = statement3.executeQuery(query3);
			while(resultSet3.next() ) {
//				Algorithm algo =  generateAlgorithm(resultSet3);
//				//-----------------add implementations
//				Statement statement5 =  conn.createStatement();
//				String query5 = "SELECT * FROM Implementations WHERE algorithm =" + algo.id + ";";
//				ResultSet resultSet5 = statement5.executeQuery(query5);
//				while(resultSet5.next()) {
//					Implementation implem =  generateImplementation(resultSet5);
//					algo.imples.add(implem);
//				}
//				statement5.close();
//				resultSet5.close();
//				//-----------------add implementations
//				c.algos.add(algo);
			}
			statement3.close();
			resultSet3.close();
			return;
		}else {
			for(int k = 0; k< c.childrenID.length ; k ++) {
           		Statement statement2 = conn.createStatement();
           		String query2 = "SELECT * FROM " + tblName + " WHERE id =" + c.childrenID[k] + ";";
           		ResultSet resultSet2 = statement2.executeQuery(query2);
           		while(resultSet2.next()) {
           			Classification c2 = generateClassification(resultSet2);
           			String key2 = Integer.toString(c2.id);
           			buildTree(c2);
           			c.childern.put(key2, c2);
           		}
           		resultSet2.close();
                statement2.close();
           	}
			Statement statement4 =  conn.createStatement();
			String query4 = "SELECT * FROM Algorithms WHERE classification =" + c.id + ";";
			ResultSet resultSet4 = statement4.executeQuery(query4);
			while(resultSet4.next() ) {
				Algorithm algo =  generateAlgorithm(resultSet4);
				//--------------add implementations
				Statement statement5 =  conn.createStatement();
				String query5 = "SELECT * FROM Implementations WHERE algorithm =" + algo.id + ";";
				ResultSet resultSet5 = statement5.executeQuery(query5);
				while(resultSet5.next()) {
					Implementation implem =  generateImplementation(resultSet5);
					algo.imples.add(implem);
				}
				statement5.close();
				resultSet5.close();
				//--------------add implementations
				c.algos.add(algo);
			}
			statement4.close();
			resultSet4.close();
			return;
		}
	}
	
	public boolean deleteClassification(int id) throws Exception{
		try {
			//update father classification's 
			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM "+ tblName +" WHERE id = ?;");
			ps1.setInt(1, id);
			ResultSet resultset1 = ps1.executeQuery();
//			while(resultset1.next()) {
//				int fatherId = resultset1.getInt("fatherID");
//				if(fatherId != -1 ) {
//					PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM " + tblName+ " WHERE id = ?;");
//					ps2.setInt(1, fatherId);
//					ResultSet resultset2 = ps2.executeQuery();
//					resultset2.next();
//					Classification classification = generateClassification(resultset2);
//					ps2.close();
//					resultset2.close();
//					int[] arrayChildren = classification.childrenID;
//					int[] newArrayChildren = new int[arrayChildren.length-1];
//					int j = 0;
//					for(int i = 0; i< arrayChildren.length ; i++) {
//						if(arrayChildren[i] != id) {
//							newArrayChildren[j] = arrayChildren[i];
//							j++;
//						}
//					}
//					String var  = "";
//					if(newArrayChildren.length == 1) {
//						var = var + newArrayChildren[0];
//					}else if(newArrayChildren.length>1) {
//						var = var + newArrayChildren[0];
//						for(int k = 1; k< newArrayChildren.length ; k++) {
//							var = var + "," + newArrayChildren[k];
//						}
//					}
//					PreparedStatement ps3 = conn.prepareStatement("UPDATE " + tblName+ " SET childrenID = ? WHERE id = ?;");
//					ps3.setString(1, var);
//					ps3.setInt(2, fatherId);
//					ps3.execute();
//					ps3.close();
//				}
//			}
			//delete classification
			subDelete(id);
			return true;
		}catch(Exception e) {
			throw new Exception("Failed in deleting classifications: " + e.getMessage());
		}
	}
	
	public void subDelete(int id) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE id = ?");
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
//				Classification classification = generateClassification(resultSet);
//				ps.close();
//				resultSet.close();
//				if(classification.childrenID == null) {
//					ps = conn.prepareStatement("SELECT * FROM Algorithms WHERE classification = ?");
//					ps.setInt(1, id);
//					resultSet = ps.executeQuery();
//					while(resultSet.next()) {
//						AlgorithmsDAO algoDao = new AlgorithmsDAO();
//						algoDao.deleteAlgorithm(resultSet.getInt("id"));
//					}
//					ps.close();
//					resultSet.close();
//					ps = conn.prepareStatement("DELETE FROM Classifications WHERE id = ?");
//					ps.setInt(1, id);
//					ps.execute();
//					ps.close();
//				}else {
//					for(int i = 0; i < classification.childrenID.length; i++) {
//						subDelete(classification.childrenID[i]);
//						ps = conn.prepareStatement("SELECT * FROM Algorithms WHERE classification = ?");
//						ps.setInt(1, id);
//						resultSet = ps.executeQuery();
//						while(resultSet.next()) {
//							AlgorithmsDAO algoDao = new AlgorithmsDAO();
//							algoDao.deleteAlgorithm(resultSet.getInt("id"));
//						}
//						ps.close();
//						resultSet.close();
//						ps = conn.prepareStatement("DELETE FROM Classifications WHERE id = ?");
//						ps.setInt(1, id);
//						ps.execute();
//						ps.close();
//					}
//				}
			}
		}catch(Exception e){
			throw new Exception("Failed in deleting classifications in subDelete: " + e.getMessage());
		}
	}
	
	public boolean mergeSiblingClassification(int class1, int class2) throws Exception{
		try {
			PreparedStatement ps3 = conn.prepareStatement("SELECT * FROM Classifications WHERE id = ?;");
			ps3.setInt(1, class1);
			ResultSet resultSet3 = ps3.executeQuery();
			String s = null;
			while(resultSet3.next()) {
				s  = resultSet3.getString("childrenID");
			}
			ps3.close();
			resultSet3.close();
			//1.改class2 的子类的father Id
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Classifications WHERE fatherID = ?;");
			ps.setInt(1, class2);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
//				int id = resultSet.getInt("id");
//				if(s.equals("")) {
//					s += id;
//				}else {
//					s += "," + id;
//				}
//				PreparedStatement ps2 = conn.prepareStatement("UPDATE Classifications SET fatherID = ? WHERE id = ?;");
//				ps2.setInt(1, class1);
//				ps2.setInt(2, id);
//				ps2.execute();
//				ps2.close();
			}
			ps.close();
			resultSet.close();
			//2.改class1 childrenId
			ps = conn.prepareStatement("UPDATE Classifications SET childrenID = ? WHERE id = ?");
			ps.setString(1,s);
			ps.setInt(2, class1);
			ps.execute();
			ps.close();
			//3.改algorithm的classification
			ps = conn.prepareStatement("SELECT * From Algorithms WHERE classification = ?");
			ps.setInt(1, class2);
			resultSet = ps.executeQuery();
			while(resultSet.next()) {
//				int algoId = resultSet.getInt("id");
//				PreparedStatement ps4 = conn.prepareStatement("UPDATE Algorithms SET classification = ? WHERE  id = ?;");
//				ps4.setInt(1, class1);
//				ps4.setInt(2, algoId);
//				ps4.execute();
//				ps4.close();
			}
			ps.close();
			resultSet.close();
			//4. 删除class2 fatherClassification 的 chilrenId
			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM "+ tblName +" WHERE id = ?;");
			ps1.setInt(1, class2);
			ResultSet resultset1 = ps1.executeQuery();
			resultset1.next();
			int fatherId = resultset1.getInt("fatherID");
			if(fatherId != -1 ) {
//				PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM " + tblName+ " WHERE id = ?;");
//				ps2.setInt(1, fatherId);
//				ResultSet resultset2 = ps2.executeQuery();
//				resultset2.next();
//				Classification classification = generateClassification(resultset2);
//				ps2.close();
//				resultset2.close();
//				int[] arrayChildren = classification.childrenID;
//				int[] newArrayChildren = new int[arrayChildren.length-1];
//				int j = 0;
//				for(int i = 0; i< arrayChildren.length ; i++) {
//					if(arrayChildren[i] != class2) {
//						newArrayChildren[j] = arrayChildren[i];
//						j++;
//					}
//				}
//				String var  = "";
//				if(newArrayChildren.length == 1) {
//					var = var + newArrayChildren[0];
//				}else if(newArrayChildren.length>1) {
//					var = var + newArrayChildren[0];
//					for(int k = 1; k< newArrayChildren.length ; k++) {
//						var = var + "," + newArrayChildren[k];
//					}
//				}
//				PreparedStatement ps4 = conn.prepareStatement("UPDATE " + tblName+ " SET childrenID = ? WHERE id = ?;");
//				ps4.setString(1, var);
//				ps4.setInt(2, fatherId);
//				ps4.execute();
//				ps4.close();
			}
			//5.delete classification
//			ps = conn.prepareStatement("DELETE FROM Classifications WHERE id = ?");
//			ps.setInt(1, class2);
//			ps.execute();
//			ps.close();
			return true;
		}catch(Exception e) {
			throw new Exception("Failed in merging classifications: " + e.getMessage());
		}
	}
	
	private Classification generateClassification(ResultSet resultSet) throws Exception {
        String name  = resultSet.getString("name");
        int id  = resultSet.getInt("id");
        String childrenID = resultSet.getString("childrenID");
        int level = resultSet.getInt("level");
        int fatherId = resultSet.getInt("fatherID");
        int[] arr2 = null;
        if(!childrenID.equals("")) {
        	String[] arr1 = childrenID.split(",");
            arr2 = new int[arr1.length];
            for(int i = 0; i< arr1.length ; i++) {
            	arr2[i] = Integer.parseInt(arr1[i]);
            }
        }
        return new Classification (name,id,arr2,level,fatherId);
    }
	private Algorithm generateAlgorithm(ResultSet resultSet) throws Exception {
        String name  = resultSet.getString("name");
        String description = resultSet.getString("description");
        int id = resultSet.getInt("id");
        int classification = resultSet.getInt("classification");
        
        return new Algorithm (name, classification, description,id);
    }
	private Implementation generateImplementation(ResultSet resultSet) throws Exception {
        String language  = resultSet.getString("language");
        String content = resultSet.getString("content");
        int id = resultSet.getInt("id");
        int algo = resultSet.getInt("algorithm");
        
        return new Implementation (language, content,id,algo);
    }
}
