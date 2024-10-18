package dao;

import java.util.ArrayList;

import dto.HealthDTO;

public interface DBdao_healthCheckup {
	public void add(HealthDTO healthdto);
	public ArrayList<HealthDTO> selectAll(String id);
//	public void update(String a, int b, String id);
	public void delete(String id, int no);
	public HealthDTO selectOne(int no, String id);
}
