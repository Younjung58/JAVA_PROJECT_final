package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.memberDTO;
import dto.preventDiseaseDTO;

public class preventDiseaseDAO extends oracleload implements DBdao_prevent {
	
	public static preventDiseaseDAO preventdao = null;
	
	private preventDiseaseDAO(){
		load();
		// 객체가 생성될 때 오라클 드라이버 로드의 과정 진행(초기 1번)
	}
	public static preventDiseaseDAO getInstance() {
		if(preventdao == null) {
			preventdao = new preventDiseaseDAO();
		}
		return preventdao; 
	}
	
	
	@Override
	public preventDiseaseDTO selectOne(String type) {
		preventDiseaseDTO prevent = new preventDiseaseDTO();
		if(conn()) {
			try {
				String sql = "select * from preventDisease where type = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1,type);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					prevent.setType(rs.getString("type"));
					prevent.setState(rs.getString("state"));
					prevent.setMemo(rs.getString("memo"));
				}
				return prevent;
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
