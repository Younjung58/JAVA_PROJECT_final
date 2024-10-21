package dao;

import dto.healthDiseaseDTO;

public interface DBdao_disease {
	public healthDiseaseDTO selectOne(int no, String type, String name);
}
