package dao;

import java.sql.Connection;
import java.util.ArrayList;

import dto.HealthDTO;

public interface DBdao {
//	public static IdeaDAO ideadao =  null;
	public String username = "system";
	public String password = null;
	public String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	public String driverName = "oracle.jdbc.driver.OracleDriver";
	public Connection conn = null;		// 커넥션 자원 변수
}
