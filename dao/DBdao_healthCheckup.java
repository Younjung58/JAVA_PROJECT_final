package dao;

import java.util.ArrayList;

import dto.healthDTO;

public interface DBdao_healthCheckup {
	public void add(healthDTO healthdto);
	public ArrayList<healthDTO> selectAll(String id);
//	public void update(String a, int b, String id);
	public void delete(String id, int no);
	public healthDTO selectOne(int no, String id);
}
