package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import dto.HealthDTO;
 

public class healthCheckupDAO implements DBdao_healthCheckup{

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
			System.out.println("커넥션 자원 획득 성공lll");
			return true;		// 커넥션 자원을 정상적으로 획득 할 경우
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	// 커넥션 자원을 획득하지 못한 경우
	}
//	public static healthCheckupDAO healthdao = null;
	
	public healthCheckupDAO(){
		load();
		// 객체가 생성될 때 오라클 드라이버 로드의 과정 진행(초기 1번)
	}
//	public static healthCheckupDAO getInstance() {
//		if(healthdao == null) {
//			healthdao = new healthCheckupDAO();
//		}
//		return healthdao; 
//	}
	private int checkId(String id) {
		ArrayList<HealthDTO> mhealth = new ArrayList<>();
		mhealth = this.selectAll(id);
		if(mhealth == null) {
			return 0;
		}
		return mhealth.size();
	}

	@Override
	public void add(HealthDTO healthdto) {	
		int no = this.checkId(healthdto.getId());		// try구문 안에 커넥션자원 두번 받아오기 불가능
		if(conn()) {
			try {
//				System.out.println(healthdto.getAC());
//				System.out.println(no);
				String sql = "insert into healthCheckup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//				String sql = "select *  from member";
				PreparedStatement psmt = conn.prepareStatement(sql);
				System.out.println(1);
				psmt.setInt(1, (no+1));
				psmt.setString(2, healthdto.getId());
				psmt.setString(3, healthdto.getGender());
				psmt.setInt(4, healthdto.getHeight());
				psmt.setInt(5, healthdto.getWeight());
				psmt.setInt(6, healthdto.getAC());
				psmt.setInt(7, healthdto.getSBP());
				psmt.setInt(8, healthdto.getDBP());
				psmt.setInt(9, healthdto.getFBG());
				psmt.setInt(10, healthdto.getTC());
				psmt.setInt(11, healthdto.getHDL());
				psmt.setInt(12, healthdto.getTG());
				psmt.setInt(13, healthdto.getLDL());
				psmt.setInt(14, healthdto.getAST());
				psmt.setInt(15, healthdto.getALT());
				psmt.setString(16, healthdto.getCf());

				int result = psmt.executeUpdate();
				
				if(result > 0) {
					conn.commit();
					System.out.println("결과 등록 완료");
				}else {
					conn.rollback();
					System.err.println("결과 등록에 실패하였습니다. --에러");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {		// conn() 메소드 실행결과 false를 리턴받았다면 == 커넥션 자원을 가져오지 못함을 의미
			System.err.println("데이터 커넥션을 실패하였습니다. 다시 시도하세요.");
		}
	}

	@Override
	public ArrayList<HealthDTO> selectAll(String id) {
		ArrayList<HealthDTO> healthlist = new ArrayList<>();
//		if(conn!=null) {
//			conn();
//		}
		if(conn()){
			try {
				String sql = "select * from healthCheckup where id = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, id);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					HealthDTO healthdto = new HealthDTO();
					healthdto.setNo(rs.getInt("no"));
					healthdto.setId(rs.getString("id"));
					healthdto.setGender(rs.getString("gender"));
					healthdto.setHeight(rs.getInt("height"));
					healthdto.setWeight(rs.getInt("weight"));
					healthdto.setAC(rs.getInt("AC"));
					healthdto.setSBP(rs.getInt("SBP"));
					healthdto.setDBP(rs.getInt("DBP"));
					healthdto.setFBG(rs.getInt("FBG"));
					healthdto.setTC(rs.getInt("TC"));
					healthdto.setHDL(rs.getInt("HDL"));
					healthdto.setTG(rs.getInt("TG"));
					healthdto.setLDL(rs.getInt("LDL"));
					healthdto.setAST(rs.getInt("AST"));
					healthdto.setALT(rs.getInt("ALT"));
					healthdto.setCf(rs.getString("cf"));
					healthlist.add(healthdto);
				}
				return healthlist;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {conn.commit();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.err.println("데이터 커넥션을 실패하였습니다. 다시 시도하세요.");
		}
		return null;
	}

	@Override
	public void delete(String id, int no) {
		// TODO Auto-generated method stub
		if(conn()) {
			try {
				String sql = "delete from healthCheckup where id = ?, no = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setInt(2, no);
				
				int result = psmt.executeUpdate();
				
				if(result > 0) {
					conn.commit();
					System.out.println("해당결과값이 삭제되었습니다.");
				}else {
					conn.rollback();
					System.err.println("삭제에 실패하였습니다. --에러");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {		// conn() 메소드 실행결과 false를 리턴받았다면 == 커넥션 자원을 가져오지 못함을 의미
			System.err.println("데이터 커넥션을 실패하였습니다. 다시 시도하세요.");
		}
	}
	@Override
	public HealthDTO selectOne(int no, String id) {
		HealthDTO healthdto = new HealthDTO();
		if(conn()) {
			try {
				String sql = "select * from healthCheckup where no = ?, id = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1,no);
				psmt.setString(1,id);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					healthdto.setNo(rs.getInt("no"));
					healthdto.setId(rs.getString("id"));
					healthdto.setGender(rs.getString("gender"));
					healthdto.setHeight(rs.getInt("height"));
					healthdto.setWeight(rs.getInt("weight"));
					healthdto.setAC(rs.getInt("AC"));
					healthdto.setSBP(rs.getInt("SBP"));
					healthdto.setDBP(rs.getInt("DBP"));
					healthdto.setFBG(rs.getInt("FBG"));
					healthdto.setTC(rs.getInt("TC"));
					healthdto.setHDL(rs.getInt("HDL"));
					healthdto.setTG(rs.getInt("TG"));
					healthdto.setLDL(rs.getInt("LDL"));
					healthdto.setAST(rs.getInt("AST"));
					healthdto.setALT(rs.getInt("ALT"));
					healthdto.setCf(rs.getString("cf"));
				}
				return healthdto;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.err.println("데이터 커넥션을 실패하였습니다. 다시 시도하세요.");
		}
		return null;
	}	
	
}

