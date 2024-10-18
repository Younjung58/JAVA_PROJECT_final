package dao;

import java.util.ArrayList;

import dto.HealthNormalDTO;

public interface DBdao_healthNormal {
	public ArrayList<HealthNormalDTO> selectAll();
	public HealthNormalDTO selectOne(String type);
}
