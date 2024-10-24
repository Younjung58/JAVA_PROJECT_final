package dao;

import java.util.ArrayList;

import dto.healthDiseaseDTO;

public interface DBdao_disease {
	public healthDiseaseDTO selectOne(String type);
	public healthDiseaseDTO selectSBP_DBP(String type, String name);
	public ArrayList<healthDiseaseDTO> selectAll();
}
