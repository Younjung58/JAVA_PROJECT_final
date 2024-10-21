package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.healthDiseaseDTO;

public class diseaseDAO extends oracleload implements DBdao_disease{
	
	public static diseaseDAO diseasedao = null;
	
	private diseaseDAO(){
		load();
		// 객체가 생성될 때 오라클 드라이버 로드의 과정 진행(초기 1번)
	}
	public static diseaseDAO getInstance() {
		if(diseasedao == null) {
			diseasedao = new diseaseDAO();
		}
		return diseasedao; 
	}
	@Override
	public healthDiseaseDTO selectOne(int no, String type, String name) {
		// TODO Auto-generated method stub
		healthDiseaseDTO diseasedto = new healthDiseaseDTO();
		if(conn()) {
			try {
				String sql = "select * from healthDisease where no = ? and type = ? and name = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1,no);
				psmt.setString(2,type);
				psmt.setString(3,name);
				
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					diseasedto.setNo(rs.getInt("no"));
					diseasedto.setType(rs.getString("type"));
					diseasedto.setName(rs.getString("name"));
					diseasedto.setCause(rs.getString("cause"));
					diseasedto.setDisease(rs.getString("disease"));
					diseasedto.setTip1(rs.getString("tip1"));
					diseasedto.setTip2(rs.getString("tip2"));
					diseasedto.setTip3(rs.getString("tip3"));
				}
				return diseasedto;
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