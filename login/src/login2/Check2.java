package login2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Check2 {
	

		  private Connection conn()
				    throws Exception
				  {
				    String url = "jdbc:oracle:thin:@192.168.0.12:1521:xe";
				    Class.forName("oracle.jdbc.driver.OracleDriver");
				    return DriverManager.getConnection(url, "scott", "TIGER");
				  }
				  private void connClose(ResultSet rs, PreparedStatement stmt, Connection con) {
				    try {
				      if (rs != null) rs.close();  } catch (Exception localException) {
				    }try { if (stmt != null) stmt.close();  } catch (Exception localException1) {
				    }try { if (con != null) con.close();  } catch (Exception localException2) {
				    }
				  }

				  	//db 아이디와 입력한 아이디 같게//
				  	int c2(String q) {
				  		ResultSet rs = null;
				  	    PreparedStatement stmt = null;
				  	    Connection con = null;
				  	    try {
				  	      con = conn();

				  	      stmt = con.prepareStatement("select * from diary where id = ?");
				  	      
				  	      stmt.setString(1, q);
				  	      rs = stmt.executeQuery();
				  		
				  		
				  	
				  	   
				  	}
				  	  catch (Exception e) {
							System.out.println("예외발생");
							e.printStackTrace();}
				  	  return 0;
				  	}
			
				  	
				  	
				  	
				  	
				  CheckType write(int q, String w, String e , String id) {
				    ResultSet rs = null;
				    PreparedStatement stmt = null;
				    Connection con = null;
				    try {
				      con = conn();

				      stmt = con.prepareStatement("select * from diary where id = ?");
				      
				      stmt.setString(1, id);
				      rs = stmt.executeQuery();
				      if (rs.next()) {
				        String dbpw = rs.getString("pw");

				        if (id.equals(dbpw)){
				        	
				        
				        	 return CheckType.PW_ERROR;
				          }
				        
				          return CheckType.SUCCESS;
				        }
				       
				 	 return CheckType.ID_ERROR;
				    }
				      
				    

				     
				    catch (Exception e) {
				      System.out.println("예외발생");
				      e.printStackTrace();
				    } finally {
				      connClose(rs, stmt, con);
				    }

				    return CheckType.DB_ERROR;
				  }

				  List<User> list()
				  {
				    ResultSet rs = null;
				    PreparedStatement stmt = null;
				    Connection con = null;
				    List list = new ArrayList();
				    try
				    {
				      con = conn();
				      stmt = con.prepareStatement("select * from diary");
				      rs = stmt.executeQuery();
				      while (rs.next()) {
				        User u = new User();
				        u.id = rs.getString(1);
				        u.pw = rs.getString(2);
				        list.add(u);
				      }
				    } catch (Exception ex) {
				      ex.printStackTrace();
				      System.out.println("설마 에러");
				    } finally {
				      connClose(rs, stmt, con);
				    }

				    return list;
				  }

				  void signUp(String id, String pw)
				  {
				    PreparedStatement stmt = null;
				    Connection con = null;
				    try {
				      con = conn();

				    //  stmt = con.prepareStatement("INSERT INTO TBL_USER (ID, PW) VALUES (?, ?)");
				      stmt = con.prepareStatement("INSERT INTO diary (dateno, title, diary, id) VALUES (?, ?,?,?)");
				      stmt.setString(1, id);
				      stmt.setString(2, pw);
				      stmt.executeUpdate();
				    }
				    catch (Exception e) {
				      System.out.println("예외발생");
				      e.printStackTrace();
				    } finally {
				      connClose(null, stmt, con);
				    }
				  }
				}
