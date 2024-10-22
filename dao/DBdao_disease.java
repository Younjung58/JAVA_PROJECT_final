package dao;

import dto.healthDiseaseDTO;

public interface DBdao_disease {
	public healthDiseaseDTO selectOne(String type);
}
