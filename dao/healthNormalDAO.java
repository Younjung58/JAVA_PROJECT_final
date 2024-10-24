package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.healthNormalDTO;

public class healthNormalDAO extends oracleload implements DBdao_healthNormal{
	
	public static healthNormalDAO healthnormaldao = null;
	
	private healthNormalDAO(){
		load();
		// 객체가 생성될 때 오라클 드라이버 로드의 과정 진행(초기 1번)
	}
	public static healthNormalDAO getInstance() {
		if(healthnormaldao == null) {
			healthnormaldao = new healthNormalDAO();
		}
		return healthnormaldao; 
	}
	
	@Override
	public ArrayList<healthNormalDTO> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<healthNormalDTO> nhealthlist = new ArrayList<>();
		if(conn()) {
			try {
				String sql = "select * from healthNormal";
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					healthNormalDTO nhealthdto = new healthNormalDTO();
					nhealthdto.setType(rs.getString("type"));
					nhealthdto.setMin(rs.getFloat("min"));
					nhealthdto.setMax(rs.getFloat("max"));
					nhealthlist.add(nhealthdto);
				}
				return nhealthlist;
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

	@Override
	public healthNormalDTO selectOne(String type) {
		healthNormalDTO nhealthdto = new healthNormalDTO();
		if(conn()) {
			try {
				String sql = "select * from healthNormal where type = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1,type);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					nhealthdto.setType(rs.getString("type"));
					nhealthdto.setMin(rs.getFloat("min"));
					nhealthdto.setMax(rs.getFloat("max"));
				}
				return nhealthdto;
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
