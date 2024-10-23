package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.HealthDTO;
 

public class healthCheckupDAO extends oracleload implements DBdao_healthCheckup{

	public static healthCheckupDAO healthdao = null;
	private int n,max = 0;
	
	private healthCheckupDAO(){
		load();
		// 객체가 생성될 때 오라클 드라이버 로드의 과정 진행(초기 1번)
	}
	public static healthCheckupDAO getInstance() {
		if(healthdao == null) {
			healthdao = new healthCheckupDAO();
		}
		return healthdao; 
	}
	private int checkId(String id) {
		ArrayList<HealthDTO> mhealth = new ArrayList<>();
		mhealth = this.selectAll(id);
		for (HealthDTO h : mhealth) {
			n = h.getNo();
			if(n>max) {
				max = n;
			}
		}
		if(mhealth == null) {
			return 0;
		}
		return max;
	}

	@Override
	public void add(HealthDTO healthdto) {	
		int no = this.checkId(healthdto.getId());		// try구문 안에 커넥션자원 두번 받아오기 불가능
		if(conn()) {
			try {
				String sql = "insert into healthCheckup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				System.out.println(1);
				System.out.println(max);
				psmt.setInt(1, (max+1));
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
				String sql = "delete from healthCheckup where id = ? and no = ?";
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
				String sql = "select * from healthCheckup where no = ? and id = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1,no);
				psmt.setString(2,id);
				
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

