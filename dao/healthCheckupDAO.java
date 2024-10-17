package dao;

import java.util.ArrayList;

import dto.HealthDTO;

public class healthCheckupDAO extends oracleload implements DBdao_healthCheckup{
	public static healthCheckupDAO healthdao = null;
	
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
	@Override
	public void add(HealthDTO healthdto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<HealthDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(int a, String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}



}
