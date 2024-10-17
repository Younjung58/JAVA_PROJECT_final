package dao;

import java.util.ArrayList;

import dto.HealthDTO;

public interface DBdao_healthCheckup {
	public void add(HealthDTO healthdto);
	public ArrayList<HealthDTO> selectAll();
	public void update(int a, String id);
	public void delete(String id);
}
