package 야압;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Check {
	
	private Connection conn() throws Exception {
		String url = "jdbc:oracle:thin:@192.168.0.12:1521:xe";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con =DriverManager.getConnection(url, "scott", "TIGER");
		return con;
	}
	private void connClose(ResultSet rs,PreparedStatement stmt,Connection con) {
		try {if(rs!=null) rs.close();} catch(SQLException e) {}
		try {if(stmt!=null) stmt.close();} catch(SQLException e) {} 
		try {if(con!=null) con.close();} catch(SQLException e) {} 
		
	}

	public int login(String id, String pw) {
		//DB에 접속 후 아디 패워가지고 확인해서 1-3값을 리턴
		
		String url = "jdbc:oracle:thin:@192.168.0.12:1521:xe";
		int flag=0; //지역변수 플레그 초기화
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection con =null;
		
		try {//jdbc사용법
			Class.forName("oracle.jdbc.driver.OracleDriver");//1.데이터베이스 드라이버를 로딩한다
			 con=DriverManager.getConnection(url, "scott", "TIGER");//2.연결해서 커넥션 객체 생성
		//	DriverManager.getConnection(" ", "유저명", "파일")	
//			System.out.println("**********");
//			System.out.println("*DB접속성공*"); //db접속여부 안 보이게 하고싶으면 여기 지우면 됨 
//			System.out.println("**********");
			
			stmt = con.prepareStatement("select * from tbl_user where id= ?"); //3 생성된 커넥션 개체를 가지고 스테이트먼트 객체 생성
			//4.객체를 가지고 sql작업을 한다
			
			//삽입 삭제 갱신시에는 .excuteUpate() 리턴이 정수 (정수가 의미하는 몇개가 처리되었는자ㅣ
			stmt.setString(1, id);
			
			
			rs = stmt.executeQuery(); //sql 문 select 실행하기
								//executeQuery는 리절트셋으로 반환                   홑따옴표 사이의 공백주의
			if(rs.next()) {//한 행이 있다면(아이디가 있다면) 
				//rs.next() 의 rs는 저장되어있는 첫번째 행을 next()을 가리킴 행이있으면 true 없으면 false를 리턴함
				//rs. 뒤에 가져올 데이터 종류를 선택 보통 자동완성 뜸
				String dbPw=rs.getString("pw"); 
				
				if(pw.equals(dbPw))
					flag=3; //return=3도 가능은 함
				else
					flag=2;
				
			}else 
				flag=1;
			
			
			
		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
			
		}finally {  //try /catch/finally세트 파이너리는 마지막에 무조건 실행되는것 입력..?
			connClose(rs,stmt,con);
		}
		//트라이 캐치를 합치는것도 가능
		//try {if(rs!=null) rs.close();} catch(SQLException e) {} 이런 형식으로 가능
		//try {if(stmt!=null) stmt.close();} catch(SQLException e) {} 
		//try {if(con!=null) con.close();} catch(SQLException e) {} 
		
		
		
		return flag;
		
}
	
	





	//여기는 회원가입쓰
	
	void signUp(String id, String pw) {
		//DB에 접속 후 아디 패워가지고 확인해서 1-3값을 리턴
		
				String url = "jdbc:oracle:thin:@192.168.0.12:1521:xe";
				int flag=0; //지역변수 플레그 초기화
				
				//프리페어스테이트먼트 2번//prepareStatement = null;
				Statement stmt = null;
				Connection con =null;
				
				try {//jdbc사용법
					Class.forName("oracle.jdbc.driver.OracleDriver");//1.데이터베이스 드라이버를 로딩한다
					 con=DriverManager.getConnection(url, "scott", "TIGER");//2.연결해서 커넥션 객체 생성
				//	DriverManager.getConnection(" ", "유저명", "파일")	
//					System.out.println("**********");
//					System.out.println("*DB접속성공*"); //db접속여부 안 보이게 하고싶으면 여기 지우면 됨 
//					System.out.println("**********");
					
				stmt = con.createStatement(); //3 생성된 커넥션 개체를 가지고 스테이트먼트 객체 생성
				//프리페어스테이트먼트 1번//stmt = con.preparedStatement("INSERT INTO TBL_USER (ID, PW) VALUES (?, ?)";
					//4.객체를 가지고 sql작업을 한다
					
					//삽입 삭제 갱신시에는 .excuteUpate() 리턴이 정수 (정수가 의미하는 몇개가 처리되었는자ㅣ
				
				//stmt.setString(1,id);	//프리페어스테이트먼트 3번
				//stmt.setString(2,pw);
				//stmt.executeUpdate();
				
				String sql=	"INSERT INTO TBL_USER (ID, PW) VALUES ('"+id+"', '"+pw+"')";
				//System.out.println(url +"얍");
				stmt.executeUpdate(sql);
					 		 //sql 문 select 실행하기
										//executeQuery는 리절트셋으로 반환                   홑따옴표 사이의 공백주의
				
				
				} catch (Exception e) {
					System.out.println("예외발생");
					e.printStackTrace();
					
			}finally {  //try /catch/finally세트 파이너리는 마지막에 무조건 실행되는것 입력..?
				{
					try {
						if(stmt!=null) stmt.close();
						if(con!=null) con.close();	
				
				
				
					} catch (SQLException e) {
					e.printStackTrace();
				}
				//트라이 캐치를 합치는것도 가능
				//try {if(rs!=null) rs.close();} catch(SQLException e) {} 이런 형식으로 가능
				//try {if(stmt!=null) stmt.close();} catch(SQLException e) {} 
				//try {if(con!=null) con.close();} catch(SQLException e) {} 
				
				
				
				
				}
				}
		
		
	}
//여기는 삭제파트
	void del(String id, String pw) {
		
				String url = "jdbc:oracle:thin:@192.168.0.12:1521:xe";
				int flag=0; //지역변수 플레그 초기화
				
				PreparedStatement stmt = null;
				Connection con =null;
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");//1.데이터베이스 드라이버를 로딩한다
					 con=DriverManager.getConnection(url, "scott", "TIGER");//2.연결해서 커넥션 객체 생성
					
				stmt = con.prepareStatement("delete TBL_USER where id=?"); //3 생성된 커넥션 개체를 가지고 스테이트먼트 객체 생성
				//String removeStr="DELETE FROM TBL_USER (ID, PW) VALUES ('"+id+"', '"+pw+"')";
				stmt.setString(1, id);
				stmt.executeUpdate();

				
				
				} catch (Exception e) {
					System.out.println("예외발생");
					e.printStackTrace();
					
			}finally { 
				{
					try {
						if(stmt!=null) stmt.close();
						if(con!=null) con.close();	
				
				
				
					} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				
				}
				}
		
		
	}
	
	
	
	
	
	
	
}