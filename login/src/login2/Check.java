package login2;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Check {
	private Connection conn() throws Exception {
		String url = "jdbc:oracle:thin:@192.168.0.12:1521:xe";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(url, "scott", "TIGER");
	}

	private void connClose(ResultSet rs, PreparedStatement stmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception localException) {
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception localException1) {
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception localException2) {
		}
	}

	// db 아이디와 입력한 아이디 같게//
	int aa(String id) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection con = null;
		try {
			con = conn();

			stmt = con.prepareStatement("select * from tbl_user where id=?");

			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return 1; // 중복
			} else {
				return 0; // 없다
			}

		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
		return 0;
	}

	// 여기에 회원삭제전용 선언
	void del(String id, String pw) {

		String url = "jdbc:oracle:thin:@192.168.0.12:1521:xe";
		int flag = 0; // 지역변수 플레그 초기화

		PreparedStatement stmt = null;
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 1.데이터베이스 드라이버를 로딩한다
			con = DriverManager.getConnection(url, "scott", "TIGER");// 2.연결해서 커넥션 객체 생성

			stmt = con.prepareStatement("delete TBL_USER where id=?"); // 3 생성된 커넥션 개체를 가지고 스테이트먼트 객체 생성
			// String removeStr="DELETE FROM TBL_USER (ID, PW) VALUES ('"+id+"', '"+pw+"')";
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();

		} finally {
			{
				try {
					if (stmt != null)
						stmt.close();
					if (con != null)
						con.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	}

	CheckType login(String id, String pw) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection con = null;
		try {
			con = conn();

			stmt = con.prepareStatement("select * from tbl_user where id=?");

			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				String dbpw = rs.getString("pw");

				if (id.equals(dbpw)) {

					return CheckType.SUCCESS;
				}

				return CheckType.PW_ERROR;
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

	List<User> list() {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection con = null;
		List list = new ArrayList();
		try {
			con = conn();
			stmt = con.prepareStatement("select * from tbl_user");
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

	void signUp(String id, String pw) {
		PreparedStatement stmt = null;
		Connection con = null;
		try {
			con = conn();

			stmt = con.prepareStatement("INSERT INTO TBL_USER (ID, PW) VALUES (?, ?)");

			stmt.setString(1, id);
			stmt.setString(2, pw);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		} finally {
			connClose(null, stmt, con);
		}
	}

//여기에 일기쓰기 저장용
	public CheckType write(int q, String w, String e, String id) {

		String url = "jdbc:oracle:thin:@192.168.0.12:1521:xe";
		int flag1 = 0; // 지역변수 플레그 초기화

		PreparedStatement stmt = null;
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 1.데이터베이스 드라이버를 로딩한다
			con = DriverManager.getConnection(url, "scott", "TIGER");// 2.연결해서 커넥션 객체 생성

			stmt = con.prepareStatement("insert into diary (dateno, title, diary,id) VALUES (?, ?,?,?)"); // 3 생성된 커넥션
																											// 개체를 가지고
																											// 스테이트먼트 객체
																											// 생성
			stmt.setString(4, id);
			stmt.executeUpdate();
		} catch (Exception ee) {
			System.out.println("예외발생");
			ee.printStackTrace();

		} finally {
			{
				try {
					if (stmt != null)
						stmt.close();
					if (con != null)
						con.close();

				} catch (SQLException ee) {
					ee.printStackTrace();
				}

			}
		}

		return null;

	}
}
