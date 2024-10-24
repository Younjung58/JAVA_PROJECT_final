package dao;

import java.util.ArrayList;

import dto.healthNormalDTO;

public interface DBdao_healthNormal {
	public ArrayList<healthNormalDTO> selectAll();
	public healthNormalDTO selectOne(String type);
}
