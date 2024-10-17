package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class oracleload {

	private String username = "system";
	private String password = "11111111";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String driverName = "oracle.jdbc.driver.OracleDriver";
	public Connection conn = null;		// 커넥션 자원 변수
	
	public void load() {
		init();		// 드라이버 로드 - 커넥션 가져오기
	}
	
	private void init() {		// 드라이버 로드
		try {
			Class.forName(driverName);
			System.out.println("오라클 드라이버 로드 성공");	// 빌드가 정확하게 됐을 때 이 문구가 출력될 것임.
			// 이 문구가 제대로 출력된다면, 오라클사에서 배포한 라이브러리를 사용할 준비가 완료된것을 의미함.
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean conn() {		// 커넥션 가져오는 공용 코드를 메서드로 정의
		try {
			conn = DriverManager.getConnection(url/*포트넘버 1521*/,username/*아이디*/,password /*비밀번호*/);
			System.out.println("커넥션 자원 획득 성공");
			return true;		// 커넥션 자원을 정상적으로 획득 할 경우
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	// 커넥션 자원을 획득하지 못한 경우
	}
}
